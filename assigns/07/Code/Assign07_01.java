import Library00.LnStrm.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign07_01 {
//
    public static<T>
    LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
        return new LnStrm<T>(
            () -> {
                LnStcn<LnStrm<T>> cxss = fxss.eval0();
                LnStrm<T> fxs1 = cxss.hd();
                LnStcn<T> cxs1 = fxs1.eval0();
                LnStrm<LnStrm<T>> fxss2 = cxss.tl();
                return new LnStcn<T>(
                    cxs1.hd(),
                    LnStrmSUtil.m2erge0(cxs1.tl(), mergeLnStrm(fxss2, cmpr), cmpr)
                );
            }
        );
    }
//
} // end of [public class Assign07_01{...}]

