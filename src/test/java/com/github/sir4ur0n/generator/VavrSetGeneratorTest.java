package com.github.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.collection.Set;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrSetGeneratorTest {

  @Property
  public void anySet(Set<String> strings) {
    strings.forEach(string -> assertThat(string).isNotNull());
    assertThat(strings.size()).isLessThanOrEqualTo(100);
  }

  @Property
  public void withSizeConstraint(@Size(min = 18, max = 42) Set<String> strings) {
    strings.forEach(string -> assertThat(string).isNotNull());
    assertThat(strings.size()).isBetween(18, 42);
  }

}
