package concurent.labs.task;

/**
 * Class representing a shirt sold by merchants.
 *
 * This can be made immutable, or be mutable. Depends on the implementation for other classes.
 * Feel free to write or remove anything in this class (obviously excluding the product name and price)
 *
 */
public class TeamShirt {

    // TODO
    private final String productName;
    private int price; // in HUF for example

    public TeamShirt(String productName, int price){
        this.productName = productName;
        this.price = price;
    }

    public void updatePrice(int modifier){
        this.price += price;
    }

    public String getProductName(){
        return this.productName;
    }

    public Integer getPrice(){
        return this.price;
    }

}
