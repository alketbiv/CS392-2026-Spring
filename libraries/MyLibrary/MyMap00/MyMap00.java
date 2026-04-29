package MyLibrary.MyMap00;

import MyLibrary.LnStrm.*;
import MyLibrary.FnTuple.*;

import java.util.function.BiConsumer;

public interface MyMap00<K,V> {
    int size();
    boolean isFull();
    boolean isEmpty();

    LnStrm<FnTupl2<K,V>> keyval_strmize();

    V search$old(K key);
    V search$exn(K key);
    V search$opt(K key);

    V insert$opt(K key, V val);
    void insert$new(K key, V val);

    V remove$old(K key);
    V remove$exn(K key);
    V remove$opt(K key);

    void foritm(BiConsumer<? super K, ? super V> work);
}