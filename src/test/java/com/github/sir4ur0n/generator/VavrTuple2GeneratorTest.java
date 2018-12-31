package com.github.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple2;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple2GeneratorTest {

  @Property
  public void tuple2(Tuple2<String, Integer> tuple2) {
    assertThat(tuple2._1).isNotNull();
    assertThat(tuple2._2).isNotNull();
  }
}
