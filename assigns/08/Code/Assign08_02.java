import MyLibrary.FnList.*;
import MyLibrary.LnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.FnTuple.*;
import MyLibrary.MyMap00.*;

import java.util.function.BiConsumer;

public class Assign08_02<V>
    implements MyMap00<String, V> {
    // HX-2026-04-01:
    // Please give an implementation of hash table
    // based on open addressing. The probing strategy
    // chosen for handling collisions is quadratic probing.
    private FnTupl2<String, V>[] table;
    private boolean[] deleted;
    private int size0;
    private static final int CAPACITY = 101;

    @SuppressWarnings("unchecked")
    public Assign08_02() {
        table = (FnTupl2<String, V>[]) new FnTupl2[CAPACITY];
        deleted = new boolean[CAPACITY];
        size0 = 0;
    }

    private int hashIndex(String key) {
        return Math.floorMod(key.hashCode(), CAPACITY);
    }

    public int size() {
        return size0;
    }

    public boolean isFull() {
        return size0 == CAPACITY;
    }

    public boolean isEmpty() {
        return size0 == 0;
    }

    public LnStrm<FnTupl2<String, V>> keyval_strmize() {
        return null;
    }

    public V search$old(String key) {
        return search$exn(key);
    }

    public V search$exn(String key) {
        V ans = search$opt(key);
        if (ans == null) {
            throw new RuntimeException("search$exn: key not found");
        }
        return ans;
    }

    public V search$opt(String key) {
        int h = hashIndex(key);
        for (int j = 0; j < CAPACITY; j++) {
            int i = Math.floorMod(h + j * j, CAPACITY);
            if (table[i] == null && !deleted[i]) break;
            if (table[i] != null && table[i].sub0.equals(key)) {
                return table[i].sub1;
            }
        }
        return null;
    }

    public V insert$opt(String key, V val) {
        int h = hashIndex(key);
        int firstDeleted = -1;
        for (int j = 0; j < CAPACITY; j++) {
            int i = Math.floorMod(h + j * j, CAPACITY);
            if (table[i] == null && !deleted[i]) {
                int ins = (firstDeleted >= 0) ? firstDeleted : i;
                table[ins] = new FnTupl2<>(key, val);
                deleted[ins] = false;
                size0++;
                return null;
            }
            if (table[i] == null && deleted[i]) {
                if (firstDeleted < 0) firstDeleted = i;
            }
            if (table[i] != null && table[i].sub0.equals(key)) {
                V old = table[i].sub1;
                table[i] = new FnTupl2<>(key, val);
                return old;
            }
        }
        if (firstDeleted >= 0) {
            table[firstDeleted] = new FnTupl2<>(key, val);
            deleted[firstDeleted] = false;
            size0++;
        }
        return null;
    }

    public void insert$new(String key, V val) {
        int h = hashIndex(key);
        for (int j = 0; j < CAPACITY; j++) {
            int i = Math.floorMod(h + j * j, CAPACITY);
            if (table[i] == null) {
                table[i] = new FnTupl2<>(key, val);
                deleted[i] = false;
                size0++;
                return;
            }
        }
    }

    public V remove$old(String key) {
        return remove$exn(key);
    }

    public V remove$exn(String key) {
        V ans = remove$opt(key);
        if (ans == null) {
            throw new RuntimeException("remove$exn: key not found");
        }
        return ans;
    }

    public V remove$opt(String key) {
        int h = hashIndex(key);
        for (int j = 0; j < CAPACITY; j++) {
            int i = Math.floorMod(h + j * j, CAPACITY);
            if (table[i] == null && !deleted[i]) break;
            if (table[i] != null && table[i].sub0.equals(key)) {
                V old = table[i].sub1;
                table[i] = null;
                deleted[i] = true;
                size0--;
                return old;
            }
        }
        return null;
    }

    public void foritm(BiConsumer<? super String, ? super V> work) {
        for (int i = 0; i < CAPACITY; i++) {
            if (table[i] != null) {
                work.accept(table[i].sub0, table[i].sub1);
            }
        }
    }
}