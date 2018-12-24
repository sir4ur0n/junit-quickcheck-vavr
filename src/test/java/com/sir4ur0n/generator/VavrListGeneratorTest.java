package com.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Distinct;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.collection.List;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrListGeneratorTest {

  @Property
  public void anyList(List<String> strings) {
    strings.forEach(string -> assertThat(string).isNotNull());
    assertThat(strings.size()).isLessThanOrEqualTo(100);
  }

  @Property
  public void withSizeConstraint(@Size(min = 18, max = 42) List<String> strings) {
    strings.forEach(string -> assertThat(string).isNotNull());
    assertThat(strings.size()).isBetween(18, 42);
  }

  /**
   * Bytes go from -128 to +127 so we are guaranteed with 260 elements to have duplicates
   */
  @Property
  public void withDistinctConstraint(@Size(max = 260) @Distinct List<Byte> bytes) {
    bytes.forEach(string -> assertThat(string).isNotNull());
    assertThat(bytes.distinct().size()).isEqualTo(bytes.size());
  }
}
