package concurent.labs.example;

public class ReferenceValueHolderButNotTheBest extends ReferenceValueHolder {

    @Override
    public void addReference(String key, ReferenceValue value){
        this.references.put(key, value);
    }

    @Override
    public ReferenceValue getReference(String key){
        return this.references.get(key);
    }

}
