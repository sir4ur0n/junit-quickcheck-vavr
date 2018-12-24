package com.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple4;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple4GeneratorTest {

  @Property
  public void tuple4(Tuple4<String, Integer, Byte, Short> tuple4) {
    assertThat(tuple4._1).isNotNull();
    assertThat(tuple4._2).isNotNull();
    assertThat(tuple4._3).isNotNull();
    assertThat(tuple4._4).isNotNull();
  }
}
