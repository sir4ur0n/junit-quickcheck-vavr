package com.sir4ur0n.generator;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.Tuple;
import io.vavr.Tuple3;

@AutoService(Generator.class)
public class VavrTuple3Generator extends ComponentizedGenerator<Tuple3> {

  public VavrTuple3Generator() {
    super(Tuple3.class);
  }

  @Override
  public Tuple3<?, ?, ?> generate(SourceOfRandomness random, GenerationStatus status) {
    return Tuple.of(
        componentGenerators().get(0).generate(random, status),
        componentGenerators().get(1).generate(random, status),
        componentGenerators().get(2).generate(random, status)
    );
  }

  @Override
  public int numberOfNeededComponents() {
    return 3;
  }
}
