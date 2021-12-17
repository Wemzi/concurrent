package concurent.labs.task;

import java.util.*;
import java.util.function.Consumer;

public class TeamShirtFactory {

    private static final int SHIRT_PRICE_START_VALUE = 10_000;
    private static final int MAX_PRICE_CHANGE = 500;
    private static final int MIN_WAIT_TIME_MS = 1000;
    private static final int MAX_WAIT_TIME_MS = 2000;

    private final Random random = new Random();
    private final Map<String, TeamShirt> basePrices = new HashMap<>(); // TODO or not TODO
    private final List<Consumer<String>> subscribers = new LinkedList<>();

    /**
     * Sets up the inventory. Can't think of a reason to change it.
     */
    public void setupFactory(){
        Teams.listOfTeams.forEach(team -> {
            String teamName = team;
            basePrices.put(teamName, new TeamShirt(teamName, SHIRT_PRICE_START_VALUE));
        });
    }

    /**
     * This is going to run as long as you don't stop it manually.
     * Optional task is to implement some sort of stopping condition (like timing out after x sec).
     *
     * This method is responsible for invoking the price updates and status output.
     * Every 5th round it will print the current prices for the shirts to help you test the simulation.
     */
    public void start(){
        new Thread(() -> {
            int round = 0;
            while(true) {
                if(round != 5) {
                    updatePrice();
                    round++;
                    try {
                        Thread.sleep(random.nextInt(MAX_WAIT_TIME_MS - MIN_WAIT_TIME_MS + 1) + MAX_WAIT_TIME_MS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    printCurrentPrices();
                    round = 0;
                }
            }
        }).start();
    }

    /**
     * Returns a shirt from the factory.
     *
     * @param team The team's name
     * @return The TeamShirt object which has the same name as the argument
     */
    public TeamShirt getShirt(String team){
        return null; // TODO implement it
    }

    /**
     * Stores use this to subscribe to a price change.
     *
     * @param subscriber A runnable method which handles the store's reaction to the price change
     */
    public void subscribe(Consumer<String> subscriber){
        this.subscribers.add(subscriber);
    }

    /**
     * Print out the state of the world. No reason to change it.
     */
    private void printCurrentPrices(){
        System.out.println("**********************************");
        basePrices.forEach((k,v) -> System.out.println(k + " shirts: " + v.getPrice()));
        System.out.println("**********************************");
    }

    /**
     * Updates price for the shirts.
     */
    private void updatePrice(){
        for(String teamName : Teams.listOfTeams){
            new Thread(() -> {
                final int priceChange = random.nextInt(MAX_PRICE_CHANGE)
                        * (random.nextBoolean() ? 1 : -1);

                // TODO update the price
                System.out.println("Factory updated price for " + teamName + " shirts by " + priceChange);
                for(Consumer consumer : subscribers){
                    consumer.accept(teamName);
                }
            }).start();
        }
    }

}
