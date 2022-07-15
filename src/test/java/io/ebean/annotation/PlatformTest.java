package io.ebean.annotation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlatformTest {

  @Test
  void base() {
    assertThat(Platform.POSTGRES.base()).isSameAs(Platform.POSTGRES);
    assertThat(Platform.POSTGRES9.base()).isSameAs(Platform.POSTGRES);

    assertThat(Platform.MYSQL.base()).isSameAs(Platform.MYSQL);
    assertThat(Platform.MYSQL55.base()).isSameAs(Platform.MYSQL);

    assertThat(Platform.SQLSERVER.base()).isSameAs(Platform.SQLSERVER);
    assertThat(Platform.SQLSERVER16.base()).isSameAs(Platform.SQLSERVER);
    assertThat(Platform.SQLSERVER17.base()).isSameAs(Platform.SQLSERVER);

  }
}
