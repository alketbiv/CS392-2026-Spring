import MyLibrary.FnList.*;
import MyLibrary.LnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.FnTuple.*;
import MyLibrary.MyMap00.*;

import java.util.function.BiConsumer;

public class Assign08_01<V> implements MyMap00<String, V> {

    private LnList<FnTupl2<String, V>>[] table;
    private int size0;

    @SuppressWarnings("unchecked")
    public Assign08_01() {
        table = (LnList<FnTupl2<String, V>>[]) new LnList[101];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LnList<FnTupl2<String, V>>();
        }
        size0 = 0;
    }

    private int hashIndex(String key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    public int size() {
        return size0;
    }

    public boolean isFull() {
        return false;
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
        int i = hashIndex(key);
        LnList<FnTupl2<String, V>> xs = table[i];

        while (xs.consq1()) {
            FnTupl2<String, V> kv = xs.hd1();
            if (kv.sub0.equals(key)) {
                return kv.sub1;
            }
            xs = xs.tl1();
        }

        return null;
    }

    public V insert$opt(String key, V val) {
        int i = hashIndex(key);
        LnList<FnTupl2<String, V>> xs = table[i];
        LnList<FnTupl2<String, V>> rev = new LnList<>();
        V old = null;
        boolean found = false;

        while (xs.consq1()) {
            FnTupl2<String, V> kv = xs.hd1();
            if (kv.sub0.equals(key)) {
                rev = new LnList<>(new FnTupl2<>(key, val), rev);
                old = kv.sub1;
                found = true;
            } else {
                rev = new LnList<>(kv, rev);
            }
            xs = xs.tl1();
        }

        if (!found) {
            rev = new LnList<>(new FnTupl2<>(key, val), rev);
            size0++;
        }

        table[i] = rev.reverse0();
        return old;
    }

    public void insert$new(String key, V val) {
        int i = hashIndex(key);
        table[i] = new LnList<>(
            new FnTupl2<>(key, val),
            table[i]
        );
        size0++;
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
        int i = hashIndex(key);
        LnList<FnTupl2<String, V>> xs = table[i];
        LnList<FnTupl2<String, V>> rev = new LnList<>();
        V old = null;
        boolean found = false;

        while (xs.consq1()) {
            FnTupl2<String, V> kv = xs.hd1();
            if (!found && kv.sub0.equals(key)) {
                old = kv.sub1;
                found = true;
            } else {
                rev = new LnList<>(kv, rev);
            }
            xs = xs.tl1();
        }

        if (found) {
            size0--;
        }

        table[i] = rev.reverse0();
        return old;
    }

    public void foritm(BiConsumer<? super String, ? super V> work) {
        for (int i = 0; i < table.length; i++) {
            LnList<FnTupl2<String, V>> xs = table[i];
            while (xs.consq1()) {
                FnTupl2<String, V> kv = xs.hd1();
                work.accept(kv.sub0, kv.sub1);
                xs = xs.tl1();
            }
        }
    }
}