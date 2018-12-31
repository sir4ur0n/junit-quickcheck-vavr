package com.github.sir4ur0n.generator;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.control.Option;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class VavrOptionGeneratorTest {

  @Property
  public void nullsDoNotBelongHere(Option<String> maybeString) {
    // Generated option must never be null
    assertThat(maybeString).isNotNull();
    // If option is defined, then what's inside must also not be null
    maybeString.forEach(string -> assertThat(string).isNotNull());
  }
}
