package com.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple1;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple1GeneratorTest {

  @Property
  public void tuple1(Tuple1<String> tuple1) {
    assertThat(tuple1._1).isNotNull();
  }
}
