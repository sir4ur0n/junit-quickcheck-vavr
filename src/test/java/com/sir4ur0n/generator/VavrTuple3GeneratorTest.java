package com.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple3;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple3GeneratorTest {

  @Property
  public void tuple3(Tuple3<String, Integer, Byte> tuple3) {
    assertThat(tuple3._1).isNotNull();
    assertThat(tuple3._2).isNotNull();
    assertThat(tuple3._3).isNotNull();
  }
}
