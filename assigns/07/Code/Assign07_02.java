import Library00.LnStrm.*;
import Library00.FnTuple.*;

public class Assign07_02 {

    public static LnStrm<Integer> intFrom(int n) {
        return new LnStrm<Integer>(() -> new LnStcn<Integer>(n, intFrom(n + 1)));
    }

    public static LnStrm<LnStrm<FnTupl2<Integer,Integer>>> genMatrix() {
        return LnStrmSUtil.map0(intFrom(1), (i) ->
            LnStrmSUtil.map0(intFrom(i), (j) -> new FnTupl2<Integer,Integer>(i, j))
        );
    }

    public static int cubeSum(FnTupl2<Integer,Integer> p) {
        return p.sub0 * p.sub0 * p.sub0 + p.sub1 * p.sub1 * p.sub1;
    }

    public static LnStrm<FnTupl2<Integer,Integer>> cubeSumOrderedIntegerPairs() {
        return cubeSumOrderedIntegerPairsFrom(genMatrix());
    }

    private static LnStrm<FnTupl2<Integer,Integer>>
    cubeSumOrderedIntegerPairsFrom(LnStrm<LnStrm<FnTupl2<Integer,Integer>>> fxss) {
        return new LnStrm<FnTupl2<Integer,Integer>>(
            () -> {
                LnStcn<LnStrm<FnTupl2<Integer,Integer>>> cxss = fxss.eval0();
                LnStrm<FnTupl2<Integer,Integer>> first = cxss.hd();
                LnStcn<FnTupl2<Integer,Integer>> crow = first.eval0();
                LnStrm<LnStrm<FnTupl2<Integer,Integer>>> rest = cxss.tl();

                return new LnStcn<FnTupl2<Integer,Integer>>(
                    crow.hd(),
                    LnStrmSUtil.m2erge0(
                        crow.tl(),
                        cubeSumOrderedIntegerPairsFrom(rest),
                        (x, y) -> Integer.compare(cubeSum(x), cubeSum(y))
                    )
                );
            }
        );
    }

    public static LnStrm<Integer> ramanujanNumbers() {
        return ramanujanFrom(cubeSumOrderedIntegerPairs());
    }

    private static LnStrm<Integer>
    ramanujanFrom(LnStrm<FnTupl2<Integer,Integer>> pairs) {
        return new LnStrm<Integer>(
            () -> {
                LnStcn<FnTupl2<Integer,Integer>> c1 = pairs.eval0();
                FnTupl2<Integer,Integer> p1 = c1.hd();

                LnStrm<FnTupl2<Integer,Integer>> rest1 = c1.tl();
                LnStcn<FnTupl2<Integer,Integer>> c2 = rest1.eval0();
                FnTupl2<Integer,Integer> p2 = c2.hd();

                int s1 = cubeSum(p1);
                int s2 = cubeSum(p2);

                LnStrm<FnTupl2<Integer,Integer>> nextPairs =
                    LnStrmSUtil.cons0(p2, c2.tl());

                if (s1 == s2) {
                    return new LnStcn<Integer>(s1, ramanujanFrom(nextPairs));
                } else {
                    return ramanujanFrom(nextPairs).eval0();
                }
            }
        );
    }

    public static void main(String[] args) {
        LnStrm<Integer> rams = ramanujanNumbers();
        LnStcn<Integer> c;

        c = rams.eval0();
        System.out.println(c.hd());
        rams = c.tl();

        c = rams.eval0();
        System.out.println(c.hd());
        rams = c.tl();

        c = rams.eval0();
        System.out.println(c.hd());
        rams = c.tl();

        c = rams.eval0();
        System.out.println(c.hd());
        rams = c.tl();

        c = rams.eval0();
        System.out.println(c.hd());
    }
}