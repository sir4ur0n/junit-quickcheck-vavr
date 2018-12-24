package com.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple7;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple7GeneratorTest {

  @Property
  public void tuple7(Tuple7<String, Integer, Byte, Short, Boolean, Double, Float> tuple7) {
    assertThat(tuple7._1).isNotNull();
    assertThat(tuple7._2).isNotNull();
    assertThat(tuple7._3).isNotNull();
    assertThat(tuple7._4).isNotNull();
    assertThat(tuple7._5).isNotNull();
    assertThat(tuple7._6).isNotNull();
    assertThat(tuple7._7).isNotNull();
  }
}
