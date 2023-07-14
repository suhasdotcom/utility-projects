package sks.utilities.commons.base.dsa;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class PermissionedMap<K, V> implements Map<K, V> {
    private final Map<K, V> theInternalMap;
    private final Class<?>[] theAuthorisedClasses;

    public PermissionedMap(Supplier<Map<K, V>> theMapCreatorFunction, Class<?> ... authorisedClasses) {
        this.theInternalMap = theMapCreatorFunction.get();
        this.theAuthorisedClasses = authorisedClasses;
    }

    @Override
    public int size() {
        return theInternalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return theInternalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        requireCallingClassAuthorisation();
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        requireCallingClassAuthorisation();
        return null;
    }

    @Override
    public V remove(Object key) {
        requireCallingClassAuthorisation();
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private Class<?> getCallingClass() {
        return null;
    }

    private boolean isAuthorisedClass(final Class<?> theCallingClass) {
        return true;
    }

    private void requireCallingClassAuthorisation() {

    }
}
