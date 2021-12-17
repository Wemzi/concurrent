package concurent.labs.example;

public class ReferenceValue {

    private Integer wrappedValue;

    public ReferenceValue(int value){
        this.wrappedValue = value;
    }

    public void add(int num) {
        this.wrappedValue += num;
    }

    public void subtract(int num) {
        this.wrappedValue -= num;
    }

    public Integer getValue(){
        return this.wrappedValue;
    }

}
