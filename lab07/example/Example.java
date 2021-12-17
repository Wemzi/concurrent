package concurent.labs.example;

/**
 * A synchronous example for the importance of recognizing when and how to use immutable classes, objects
 *
 */
public class Example {

    public static void main(String[] args) {
        playScenarioOne();
        playScenarioTwo();
        playScenarioThree();
    }

    /**
     * Scenario 1:
     *
     * This is absolutely not optimal for us.
     * We want to take some numbers that should be constant and modify them to our own likings.
     * The catch is that we use a mutable class for those constants, so they won't really be constants...
     */
    public static void playScenarioOne(){

        // Setting up the ref value holder
        ReferenceValueHolder rvh = new ReferenceValueHolderButNotTheBest();
        addValues(rvh);

        // Let's say ClientA is using the reference value to calculate something
        // But he is not entirely pleased with how he gets these references and feels like
        // he can make some adjustments to it that will serve better for his purpose
        ReferenceValue clientACopy = rvh.getReference("Ref1");
        clientACopy.add(5);

        // ClientB wants the reference too, but doesn't want to change it, just use it as is
        ReferenceValue clientBCopy = rvh.getReference("Ref1");

        // In an ideal world ClientA would have 10 as a reference and ClientB would have 5, the original value
        if(clientACopy.getValue() == clientBCopy.getValue()){
            System.out.println("This is not good, Client A just messed up the whole stuff for Client B too");
        } else {
            System.out.println("Something that will never get printed");
        }
    }

    /**
     * Scenario 2:
     *
     * This is a bit better.
     * We want to take some numbers that should be constant and modify them to our own likings.
     * Now we have a collection that creates a copy of the reference value, not just returning the same object.
     *
     */
    public static void playScenarioTwo(){

        // Setting up the ref value holder
        // Only this time we actually do something about the references to be immutable
        ReferenceValueHolder rvh = new ReferenceValueHolderBestVersionIfTheRefValueIsMutable();
        addValues(rvh);

        // Let's say ClientA is using the reference value to calculate something
        // But he is not entirely pleased with how he gets these references and feels like
        // he can make some adjustments to it that will serve better for his purpose
        ReferenceValue clientACopy = rvh.getReference("Ref1");
        clientACopy.add(5);

        // ClientB wants the reference too, but doesn't want to change it, just use it as is
        ReferenceValue clientBCopy = rvh.getReference("Ref1");

        // In an ideal world ClientA would have 10 as a reference and ClientB would have 5, the original value
        if(clientACopy.getValue() == clientBCopy.getValue()){
            System.out.println("This is not good, Client A just messed up the whole stuff for Client B too");
        } else {
            System.out.println("This is seems to be working now, not like in scenario 1");
        }
    }

    /**
     * Scenario 3:
     *
     * This is good too.
     * We want to take some numbers that should be constant and modify them to our own likings.
     * Now the collection isn't handling the immutability of it's elements, but the elements do it for themselves
     *
     * Client A can still make changes to it because he didn't get the immutable one back.
     *
     */
    public static void playScenarioThree(){

        // Setting up the ref value holder
        // Only this time we actually do something about the references to be immutable
        ReferenceValueHolderButNowTheRefValueIsImmutable rvh = new ReferenceValueHolderButNowTheRefValueIsImmutable();
        rvh.addReference("Ref1", new ReferenceValueButImmutable(5));

        // Let's say ClientA is using the reference value to calculate something
        // But he is not entirely pleased with how he gets these references and feels like
        // he can make some adjustments to it that will serve better for his purpose
        ReferenceValue clientACopy = rvh.getReference("Ref1").add(5);

        // ClientB wants the reference too, but doesn't want to change it, just use it as is
        ReferenceValueButImmutable clientBCopy = rvh.getReference("Ref1");

        // In an ideal world ClientA would have 10 as a reference and ClientB would have 5, the original value
        if(clientACopy.getValue() == clientBCopy.getValue()){
            System.out.println("This is not good, Client A just messed up the whole stuff for Client B too");
        } else {
            System.out.println("This is is working as well, like scenario 2");
        }
    }

    /**
     * Adds values to that particular ReferenceValueHolder instance
     *
     * @param rvh
     */
    private static void addValues(ReferenceValueHolder rvh){
        rvh.addReference("Ref1", new ReferenceValue(5));
    }
}
