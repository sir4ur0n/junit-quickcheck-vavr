package com.sir4ur0n.generator;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.Tuple;
import io.vavr.Tuple1;

@AutoService(Generator.class)
public class VavrTuple1Generator extends ComponentizedGenerator<Tuple1> {

  public VavrTuple1Generator() {
    super(Tuple1.class);
  }

  @Override
  public Tuple1<?> generate(SourceOfRandomness random, GenerationStatus status) {
    return Tuple.of(
        componentGenerators().get(0).generate(random, status)
    );
  }

  @Override
  public int numberOfNeededComponents() {
    return 1;
  }
}
