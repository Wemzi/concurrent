package concurent.labs.example;

import java.util.HashMap;
import java.util.Map;

public class ReferenceValueHolderButNowTheRefValueIsImmutable {

    protected final Map<String, ReferenceValueButImmutable> references = new HashMap<>();

    public void addReference(String key, ReferenceValueButImmutable value){
        this.references.put(key, value);
    }

    public ReferenceValueButImmutable getReference(String key){
        return this.references.get(key);
    }
}
