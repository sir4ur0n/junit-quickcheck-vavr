package com.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple8;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple8GeneratorTest {

  @Property
  public void tuple8(Tuple8<String, Integer, Byte, Short, Boolean, Double, Float, Character> tuple8) {
    assertThat(tuple8._1).isNotNull();
    assertThat(tuple8._2).isNotNull();
    assertThat(tuple8._3).isNotNull();
    assertThat(tuple8._4).isNotNull();
    assertThat(tuple8._5).isNotNull();
    assertThat(tuple8._6).isNotNull();
    assertThat(tuple8._7).isNotNull();
    assertThat(tuple8._8).isNotNull();
  }
}
