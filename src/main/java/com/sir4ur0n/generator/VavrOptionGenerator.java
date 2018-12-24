package com.sir4ur0n.generator;

import static io.vavr.API.None;
import static io.vavr.API.Some;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.ComponentizedGenerator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.control.Option;

@AutoService(Generator.class)
public class VavrOptionGenerator extends ComponentizedGenerator<Option> {

  public VavrOptionGenerator() {
    super(Option.class);
  }

  @Override
  public Option<?> generate(SourceOfRandomness random, GenerationStatus status) {
    return random.nextBoolean()
        ? Some(componentGenerators().get(0).generate(random, status))
        : None();
  }

  @Override
  public int numberOfNeededComponents() {
    return 1;
  }
}
