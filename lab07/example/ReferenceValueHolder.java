package concurent.labs.example;

import java.util.HashMap;
import java.util.Map;

public abstract class ReferenceValueHolder {

    protected final Map<String, ReferenceValue> references = new HashMap<>();

    public abstract void addReference(String key, ReferenceValue value);

    public abstract ReferenceValue getReference(String key);

}
