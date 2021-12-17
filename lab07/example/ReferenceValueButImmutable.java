package concurent.labs.example;

public class ReferenceValueButImmutable {

    private final Integer wrappedValue;

    public ReferenceValueButImmutable(int value){
        this.wrappedValue = value;
    }

    public ReferenceValue add(int num) {
        return new ReferenceValue(this.wrappedValue + num);
    }

    public ReferenceValue subtract(int num) {
        return new ReferenceValue(this.wrappedValue - num);
    }

    public Integer getValue(){
        return this.wrappedValue;
    }
}
