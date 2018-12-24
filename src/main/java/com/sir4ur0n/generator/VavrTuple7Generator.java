package com.sir4ur0n.generator;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.Tuple;
import io.vavr.Tuple7;

@AutoService(Generator.class)
public class VavrTuple7Generator extends ComponentizedGenerator<Tuple7> {

  public VavrTuple7Generator() {
    super(Tuple7.class);
  }

  @Override
  public Tuple7<?, ?, ?, ?, ?, ?, ?> generate(SourceOfRandomness random, GenerationStatus status) {
    return Tuple.of(
        componentGenerators().get(0).generate(random, status),
        componentGenerators().get(1).generate(random, status),
        componentGenerators().get(2).generate(random, status),
        componentGenerators().get(3).generate(random, status),
        componentGenerators().get(4).generate(random, status),
        componentGenerators().get(5).generate(random, status),
        componentGenerators().get(6).generate(random, status)
    );
  }

  @Override
  public int numberOfNeededComponents() {
    return 7;
  }
}
