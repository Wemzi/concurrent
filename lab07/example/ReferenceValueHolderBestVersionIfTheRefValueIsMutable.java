package concurent.labs.example;

public class ReferenceValueHolderBestVersionIfTheRefValueIsMutable extends ReferenceValueHolder {

    @Override
    public void addReference(String key, ReferenceValue value){
        this.references.put(key, value);
    }

    @Override
    public ReferenceValue getReference(String key){
        return new ReferenceValue(this.references.get(key).getValue());
    }
}
