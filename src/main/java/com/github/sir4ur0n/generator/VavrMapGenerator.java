package com.github.sir4ur0n.generator;

import static com.pholser.junit.quickcheck.internal.Ranges.Type.INTEGRAL;
import static com.pholser.junit.quickcheck.internal.Ranges.checkRange;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

@AutoService(Generator.class)
public class VavrMapGenerator extends ComponentizedGenerator<Map> {

  private Size sizeRange;

  public VavrMapGenerator() {
    super(Map.class);
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

  @Override
  public Map<?, ?> generate(SourceOfRandomness random, GenerationStatus status) {
    return HashMap.fill(size(random), () -> Tuple.of(
        componentGenerators().get(0).generate(random, status),
        componentGenerators().get(1).generate(random, status)
    ));
  }

  @Override
  public int numberOfNeededComponents() {
    return 2;
  }

  private int size(SourceOfRandomness random) {
    return sizeRange != null
        ? random.nextInt(sizeRange.min(), sizeRange.max())
        : random.nextInt(0, 100);
  }
}
