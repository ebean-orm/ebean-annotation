package io.ebean.annotation;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TxOptionTest {

    @Test
    public void asBoolean() {
        assertThat(TxOption.DEFAULT.asBoolean()).isNull();
        assertThat(TxOption.ON.asBoolean()).isTrue();
        assertThat(TxOption.OFF.asBoolean()).isFalse();
    }

    @Test
    public void test_toString() {
        assertThat(TxOption.DEFAULT.toString()).isEqualTo("DEFAULT");
        assertThat(TxOption.ON.toString()).isEqualTo("ON");
        assertThat(TxOption.OFF.toString()).isEqualTo("OFF");
    }
}