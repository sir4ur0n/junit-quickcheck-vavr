package com.sir4ur0n.generator;

import static com.pholser.junit.quickcheck.internal.Ranges.Type.INTEGRAL;
import static com.pholser.junit.quickcheck.internal.Ranges.checkRange;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.Distinct;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.collection.List;

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
}
