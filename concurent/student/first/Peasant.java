package concurent.student.first;

import java.util.concurrent.atomic.AtomicBoolean;

public class Peasant extends Unit {

    private static final int HARVEST_WAIT_TIME = 100;
    private static final int HARVEST_AMOUNT = 10;

    private AtomicBoolean isHarvesting = new AtomicBoolean(false);
    private AtomicBoolean isBuilding = new AtomicBoolean(false);

    private Peasant(Base owner) {
        super(owner, UnitType.PEASANT);
    }

    public static Peasant createPeasant(Base owner){
        return new Peasant(owner);
    }

    /**
     * Starts gathering gold.
     */
    public void startMining(){
        isHarvesting.set(true);
        Thread harvestingThread = new Thread(()->{ 
            tsSleepForMsec(HARVEST_WAIT_TIME);
            this.getOwner().getResources().addGold(HARVEST_AMOUNT);
        });
        harvestingThread.start();
        try {
            harvestingThread.join();
        } catch (Exception e) {
            //TODO: handle exception
        }
        stopHarvesting();
    }

    /**
     * Starts gathering wood.
     */
    public void startCuttingWood(){
        isHarvesting.set(true);
        Thread harvestingThread = new Thread(()->{
            tsSleepForMsec(HARVEST_WAIT_TIME);
            this.getOwner().getResources().addWood(HARVEST_AMOUNT);
        });
        harvestingThread.start();
        try {
            harvestingThread.join();
        } catch (Exception e) {
            //TODO: handle exception
        }
        stopHarvesting();
    }

    /**
     * Peasant should stop all harvesting once this is invoked
     */
    public void stopHarvesting(){
        this.isHarvesting.set(false);
    }

    /**
     * Tries to build a certain type of building.
     * Can only build if there are enough gold and wood for the building
     * to be built.
     *
     * @param buildingType Type of the building
     * @return true, if the building process has started
     *         false, if there are insufficient resources
     */
    public boolean tryBuilding(UnitType buildingType){
        if (this.getOwner().getResources().canBuild(buildingType.goldCost, buildingType.woodCost)) {
            startBuilding(buildingType);
        }
        return false;
    }

    /**
     * Start building a certain type of building.
     * Keep in mind that a peasant can only build one building at one time.
     *
     * @param buildingType Type of the building
     */
    private void startBuilding(UnitType buildingType){
        if(!isBuilding.get())
        {
            this.getOwner().getResources().removeCost(buildingType.goldCost, buildingType.woodCost);
                 Thread buildingThread = new Thread(()->{
                        isBuilding.set(true);
                        tsSleepForMsec(buildingType.buildTime);
                        isBuilding.set(false);
                    });
                 buildingThread.start();;
                 try {
                    buildingThread.join();
                 } catch (InterruptedException e) {
                     System.out.println(e);
                 }
                 this.getOwner().getBuildings().add(Building.createBuilding(buildingType, this.getOwner()));
           
        }
    }

    /**
     * Determines if a peasant is free or not.
     * This means that the peasant is neither harvesting, nor building.
     *
     * @return Whether he is free
     */
    public boolean isFree(){
        return !isHarvesting.get() && !isBuilding.get();
    }


}
