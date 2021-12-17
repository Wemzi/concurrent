package concurent.labs.task;

/**
 * Simulating an economy based on football shirts.
 * Every merchant/store buys the shirts they are selling from a factory and sells them
 * with a profit.
 * Not every merchant/store sells all of the shirts. In fact no one sells Tottenham Hotspur shirts
 * because no one wants to buy such things.
 *
 */
public class Simulation {

    public static void main(String[] args) {

        TeamShirtFactory factory = new TeamShirtFactory();
        factory.setupFactory();

        Store store1 = new Store("Official Store", 5500, factory, Teams.ARSENAL, Teams.CHELSEA,
                Teams.LIVERPOOL, Teams.MANCHESTER_CITY, Teams.MANCHESTER_UNITED);
        Store store2 = new Store("Some guy in the market", 1500, factory, Teams.ARSENAL, Teams.CHELSEA,
                Teams.LIVERPOOL, Teams.MANCHESTER_CITY, Teams.MANCHESTER_UNITED, Teams.ASTON_VILLA, Teams.EVERTON,
                Teams.NEWCASTLE_UNITED);
        Store store3 = new Store("Some store in London", 3500, factory, Teams.ARSENAL, Teams.CHELSEA);
        Store store4 = new Store("Some store in Manchester", 2500, factory, Teams.MANCHESTER_CITY, Teams.MANCHESTER_UNITED);
        Store store5 = new Store("Some store where only the coolest people buy shirts",
                1000, factory, Teams.ARSENAL);

        factory.start();

        // optional TODO have some timeout for the whole simulation
    }

}
