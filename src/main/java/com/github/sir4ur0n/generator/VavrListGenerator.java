package com.github.sir4ur0n.generator;

import static com.pholser.junit.quickcheck.internal.Ranges.Type.INTEGRAL;
import static com.pholser.junit.quickcheck.internal.Ranges.checkRange;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.List;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Cons;
import static io.vavr.Patterns.$Nil;
import static java.util.function.Function.identity;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.Distinct;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import java.math.BigDecimal;
import java.util.function.Function;

@AutoService(Generator.class)
public class VavrListGenerator extends ComponentizedGenerator<List> {

  private Size sizeRange;
  private boolean distinct = false;

  public VavrListGenerator() {
    super(List.class);
  }

  /**
   * <p>Tells this generator to add elements to the generated collection
   * a number of times within a specified minimum and/or maximum, inclusive,
   * chosen with uniform distribution.</p>
   *
   * <p>Note that some kinds of collections disallow duplicates, so the
   * number of elements added may not be equal to the collection's size
   *
   * @param size annotation that gives the size constraints
   */
  @SuppressWarnings("unused")
  public void configure(Size size) {
    this.sizeRange = size;
    checkRange(INTEGRAL, size.min(), size.max());
  }

  /**
   * Tells this generator to add elements which are distinct from each other.
   *
   * @param distinct Generated elements will be distinct if this param is
   * not null
   */
  @SuppressWarnings("unused")
  public void configure(Distinct distinct) {
    this.distinct = distinct != null;
  }

  @Override
  public List<?> generate(SourceOfRandomness random, GenerationStatus status) {
    List<?> list = List.fill(size(random), () -> componentGenerators().get(0).generate(random, status));
    return distinct ? list.distinct() : list;
  }

  @Override
  public int numberOfNeededComponents() {
    return 1;
  }

  private int size(SourceOfRandomness random) {
    return sizeRange != null
        ? random.nextInt(sizeRange.min(), sizeRange.max())
        : random.nextInt(0, 100);
  }

  @Override
  public boolean canShrink(Object larger) {
    if (types().get(0).isInstance(larger)) {
      List<?> list = (List) larger;
      return list.nonEmpty();
    }
    return false;
  }

  /**
   * Shrinking is based on <a href="http://hackage.haskell.org/package/QuickCheck-2.12.6.1/docs/src/Test.QuickCheck.Arbitrary.html#shrinkList">QuickCheck list implementation</a>.
   */
  @SuppressWarnings("unchecked")
  @Override
  public java.util.List<List> doShrink(SourceOfRandomness random, List xs) {
    int n = xs.length();
    // Shrinker of list elements
    Function shr = o -> ((Generator) componentGenerators().get(0)).doShrink(random, o);

    // takeWhile (>0) (iterate (`div`2) n)
    List<Integer> numberOfElementsToRemove = Stream.iterate(n, integer -> integer / 2)
        .takeWhile(i -> i > 0)
        .toList();

    // [ removes k n xs | k <- takeWhile (>0) (iterate (`div`2) n) ]
    return numberOfElementsToRemove.map(k -> removes(k, n, xs))
        // concat [ removes k n xs | k <- takeWhile (>0) (iterate (`div`2) n) ]
        .flatMap(identity())
        // ++ shrinkOne xs
        .appendAll(shrinkOne(xs, shr))
        .toJavaList();
  }

  @SuppressWarnings("unchecked")
  private List<List> shrinkOne(List input, Function shr) {
    return Match(input).of(
        Case($Nil(), List::empty),
        Case($Cons($(), $()), (x, xs) -> {
          List shrunkX = List.ofAll((java.util.List) shr.apply(x));
          List shrunkHead = shrunkX.map(o -> List(o).appendAll(xs));

          List<List<Object>> shrunkXs = shrinkOne(xs, shr).map(shrunkTail -> List(x).appendAll(shrunkTail));

          return shrunkHead.appendAll(shrunkXs);
        })
    );
  }

  @SuppressWarnings("unchecked")
  private List<List> removes(int k, int n, List xs) {
    // k > n     = []
    if (k > n) {
      return List();
    }

    List xs1 = xs.take(k);
    List xs2 = xs.drop(k);

    // null xs2  = [[]]
    if (xs2.isEmpty()) {
      return List(List());
    }

    // otherwise = xs2 : map (xs1 ++) (removes k (n-k) xs2)
    return List(xs2).appendAll(removes(k, (n - k), xs2).map(list -> list.prependAll(xs1)));
  }

  @Override
  public BigDecimal magnitude(Object value) {
    return BigDecimal.valueOf(((List) value).size());
  }

}
