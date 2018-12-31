package com.github.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple5;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple5GeneratorTest {

  @Property
  public void tuple5(Tuple5<String, Integer, Byte, Short, Boolean> tuple5) {
    assertThat(tuple5._1).isNotNull();
    assertThat(tuple5._2).isNotNull();
    assertThat(tuple5._3).isNotNull();
    assertThat(tuple5._4).isNotNull();
    assertThat(tuple5._5).isNotNull();
  }
}
