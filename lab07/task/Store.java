package concurent.labs.task;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent a store that sells shirts.
 * Each store will put their profit onto the original price and sell it for that amount.
 *
 */
public class Store {

    private final String name;
    private final int profit;
    private final TeamShirtFactory factory;
    private final Map<String, TeamShirt> inventory = new HashMap<>(); // TODO or not TODO

    public Store(String name, int profit, TeamShirtFactory factory, String... interestedShirts){
        this.name = name;
        this.profit = profit;
        this.factory = factory;

        for(String team : interestedShirts){
            // TODO add the shirts from the factory
        }

        factory.subscribe(this::reactToMarketChange);

    }

    /**
     * Runs every time a price change happens in the factory.
     * EVERY. TIME.
     * So make sure to only do anything when that change affects any of the sold shirts.
     *
     * @param teamName
     */
    public void reactToMarketChange(String teamName){
        // TODO check if we have that team's shirt, do nothing if not
        // TODO get the new shirt price and apply profit to it
        // TODO update our own inventory

        // TODO note that the store invokes these on different threads

        // TODO print this out
        //    System.out.println(name + " is now selling " + <here comes the team>
        //            + " shirts for " + <here comes the price>);
    }

}
