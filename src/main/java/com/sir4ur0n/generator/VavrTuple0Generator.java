package com.sir4ur0n.generator;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.Tuple;
import io.vavr.Tuple0;

@AutoService(Generator.class)
public class VavrTuple0Generator extends Generator<Tuple0> {

  public VavrTuple0Generator() {
    super(Tuple0.class);
  }

  @Override
  public Tuple0 generate(SourceOfRandomness random, GenerationStatus status) {
    return Tuple.empty();
  }

}
