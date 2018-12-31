package com.github.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.collection.Map;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrMapGeneratorTest {

  @Property
  public void anyMap(Map<String, Integer> map) {
    map.forEach(tuple -> {
      assertThat(tuple._1).isNotNull();
      assertThat(tuple._2).isNotNull();
    });
    assertThat(map.size()).isLessThanOrEqualTo(100);
  }

  @Property
  public void withSizeConstraint(@Size(min = 18, max = 42) Map<String, Integer> map) {
    map.forEach(tuple -> {
      assertThat(tuple._1).isNotNull();
      assertThat(tuple._2).isNotNull();
    });
    assertThat(map.size()).isLessThanOrEqualTo(42);
  }
}
