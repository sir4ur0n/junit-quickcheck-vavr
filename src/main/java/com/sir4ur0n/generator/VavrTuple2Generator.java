package com.sir4ur0n.generator;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.Tuple;
import io.vavr.Tuple2;

@AutoService(Generator.class)
public class VavrTuple2Generator extends ComponentizedGenerator<Tuple2> {

  public VavrTuple2Generator() {
    super(Tuple2.class);
  }

  @Override
  public Tuple2<?, ?> generate(SourceOfRandomness random, GenerationStatus status) {
    return Tuple.of(
        componentGenerators().get(0).generate(random, status),
        componentGenerators().get(1).generate(random, status)
    );
  }

  @Override
  public int numberOfNeededComponents() {
    return 2;
  }
}
