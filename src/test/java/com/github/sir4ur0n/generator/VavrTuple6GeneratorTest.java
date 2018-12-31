package com.github.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple6;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple6GeneratorTest {

  @Property
  public void tuple6(Tuple6<String, Integer, Byte, Short, Boolean, Double> tuple6) {
    assertThat(tuple6._1).isNotNull();
    assertThat(tuple6._2).isNotNull();
    assertThat(tuple6._3).isNotNull();
    assertThat(tuple6._4).isNotNull();
    assertThat(tuple6._5).isNotNull();
    assertThat(tuple6._6).isNotNull();
  }
}
