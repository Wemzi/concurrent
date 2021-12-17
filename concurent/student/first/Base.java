package concurent.student.first;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class Base {

    private static final int STARTER_PEASANT_NUMBER = 5;
    private static final int PEASANT_NUMBER_GOAL = 10;

    // lock to ensure only one unit can be trained at one time
    private final ReentrantLock trainingLock = new ReentrantLock();

    private final String name;
    private final Resources resources = new Resources();
    private final List<Peasant> peasants = Collections.synchronizedList(new LinkedList<>());
    private final List<Building> buildings = Collections.synchronizedList(new LinkedList<>());

    public Base(String name){
        this.name = name;
        for(int idx=0; idx<STARTER_PEASANT_NUMBER;idx++)
        {
            Peasant guy = createPeasant();
            if(idx<3)
            {
                guy.startMining();
            }
            else if(idx<4)
            {
                guy.startCuttingWood();
                
            }
            else{
                // do nothing
            }
        }
        // TODO 1 of them should cut tree
        // TODO 1 should do nothing
        // TODO Use the createPeasant() method
    }

    public void startPreparation(){
        
        Thread trainer = new Thread (()-> { 
            while(peasants.size() < PEASANT_NUMBER_GOAL)
            {
                if(peasants.size() < PEASANT_NUMBER_GOAL &&  resources.canTrain(UnitType.PEASANT.goldCost, UnitType.PEASANT.woodCost, UnitType.PEASANT.foodCost))
                {
                    Peasant shittie = createPeasant();
                    Random random = new Random();
                    boolean mineordie = random.nextInt() % 2 == 0;
                    if(mineordie)
                    {
                        synchronized(peasants)
                        {
                            shittie.startMining();
                        }

                    }
                    else
                    {
                        synchronized(peasants)
                        {
                            shittie.startCuttingWood();
                        }

                    }
                }
            }
        });
        trainer.start();
        Thread builder = new Thread (()->{ 
            while(!hasEnoughBuilding(UnitType.FARM, 3))
            {
                Peasant worker = getFreePeasant();
                if(worker != null)
                {
                    boolean isbuilt = worker.tryBuilding(UnitType.FARM);
                    if(!isbuilt)
                    {
                        Random random = new Random();
                        boolean mineordie = random.nextInt() % 2 == 0;
                        if(mineordie)
                        {
                            worker.startMining();
                        }
                        else
                        {
                            worker.startCuttingWood();
                        }
                    }
                }
                else
                {
                    System.out.println("nincs szabad munkas h farmot epitsek");
                    sleepForMsec(500);
                }
            }
            while(!hasEnoughBuilding(UnitType.LUMBERMILL, 1))
            {
                Peasant worker = getFreePeasant();
                if(worker != null)
                {
                    boolean isbuilt = worker.tryBuilding(UnitType.LUMBERMILL);
                    if(!isbuilt)
                    {
                        Random random = new Random();
                        boolean mineordie = random.nextInt() % 2 == 0;
                        if(mineordie)
                        {
                            worker.startMining();
                        }
                        else
                        {
                            worker.startCuttingWood();
                        }
                    }
                }
                else
                {
                System.out.println("nincs szabad munkas h farmot epitsek");
                sleepForMsec(500);
                }

            }
            while(!hasEnoughBuilding(UnitType.BLACKSMITH, 1))
            {
                Peasant worker = getFreePeasant();
                if(worker != null)
                {
                    boolean isbuilt = worker.tryBuilding(UnitType.BLACKSMITH);
                    if(!isbuilt)
                    {
                        Random random = new Random();
                        boolean mineordie = random.nextInt() % 2 == 0;
                        if(mineordie)
                        {
                            worker.startMining();
                        }
                        else
                        {
                            worker.startCuttingWood();
                        }
                    }
                }
            }
        });
        builder.start();
        try{
            builder.join();
            trainer.join();
        }catch(InterruptedException e)
        {
            System.out.println(e);
        }

        if(peasants.size() == 10)
        {
            for(int idx=0; idx<7;idx++)
            {
                if(idx<5)
                {
                    peasants.get(idx).startMining();
                }
                else if(idx<7)
                {
                    peasants.get(idx).startCuttingWood();
                }

            }
        }

        for(Peasant peasant:peasants)
        {
            peasant.stopHarvesting();
        }

        // TODO Start the building and training preparations on separate threads
        // TODO Tip: use the hasEnoughBuilding method

        // TODO Build 3 farms - use getFreePeasant() method to see if there is a peasant without any work

        // TODO Create remaining 5 peasants - Use the PEASANT_NUMBER_GOAL constant
        // TODO 5 of them should mine gold
        // TODO 2 of them should cut tree
        // TODO 3 of them should do nothing
        // TODO Use the createPeasant() method

        // TODO Build a lumbermill - use getFreePeasant() method to see if there is a peasant without any work

        // TODO Build a blacksmith - use getFreePeasant() method to see if there is a peasant without any work

        // TODO Wait for all the necessary preparations to finish

        // TODO Stop harvesting with the peasants once everything is ready
        System.out.println(this.name + " finished creating a base");
        System.out.println(this.name + " peasants: " + this.peasants.size());
        for(Building b : buildings){
            System.out.println(this.name + " has a  " + b.getUnitType().toString());
        }

    }


    /**
     * Returns a peasants that is currently free.
     * Being free means that the peasant currently isn't harvesting or building.
     *
     * @return Peasant object, if found one, null if there isn't one
     */
    private Peasant getFreePeasant(){
       for(Peasant p: peasants)
       {
           if(p.isFree())
           {
               return p;
           }
       }
        return null;
    }

    /**
     * Creates a peasant.
     * A peasant could only be trained if there are sufficient
     * gold, wood and food for him to train.
     *
     * At one time only one Peasant can be trained.
     *
     * @return The newly created peasant if it could be trained, null otherwise
     */
    private Peasant createPeasant(){
        Peasant result;
        while(trainingLock.isLocked())
        {
            Base.sleepForMsec(100);
        }
        if(resources.canTrain(UnitType.PEASANT.goldCost, UnitType.PEASANT.woodCost, UnitType.PEASANT.foodCost)){
            trainingLock.lock();
            this.resources.removeCost(UnitType.PEASANT.goldCost, UnitType.PEASANT.woodCost);
            this.resources.updateCapacity(UnitType.PEASANT.foodCost);
            result = Peasant.createPeasant(this);
            peasants.add(result);
            sleepForMsec(UnitType.PEASANT.buildTime);
            // TODO 1: Sleep as long as it takes to create a peasant - use sleepForMsec() method
            // TODO 2: Remove costs
            // TODO 3: Update capacity
            // TODO 4: Use the Peasant class' createPeasant method to create the new Peasant
            // TODO Remember that at one time only one peasant can be trained
            trainingLock.unlock();
            return result;
        }
        return null;
    }

    public Resources getResources(){
        return this.resources;
    }

    public List<Building> getBuildings(){
        return this.buildings;
    }

    public String getName(){
        return this.name;
    }

    /**
     * Helper method to determine if a base has the required number of a certain building.
     *
     * @param unitType Type of the building
     * @param required Number of required amount
     * @return true, if required amount is reached (or surpassed), false otherwise
     */
    private boolean hasEnoughBuilding(UnitType unitType, int required){
        int num = 0;
        
        for(Building b : buildings)
        {
            if(b.getUnitType() == unitType)
            {
                num++;
                
            }
        }
        return num >= required;
    }

    private static void sleepForMsec(int sleepTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
        }
    }

}
