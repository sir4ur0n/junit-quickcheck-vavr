package com.github.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple0;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrTuple0GeneratorTest {

  @Property
  public void tuple0(Tuple0 tuple0) {
    assertThat(tuple0).isNotNull();
  }
}
