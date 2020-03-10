package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerNameTest {
    @Test
    @DisplayName("입력으로 빈값이 들어올 때")
    void input_Empty() {
        assertThatThrownBy(() -> {
            new PlayerName(" ");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력으로 빈값이 들어올 때")
    void input_Null() {
        assertThatThrownBy(() -> {
            new PlayerName(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("쉼표만 있을 때")
    void split_test(){
        assertThatThrownBy(()->{
            new PlayerName(", ,");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"pobi,crong,honux:pobi", "crong:crong", "DD,Tiger:Tiger"}, delimiter = ':')
    void namesList_test(String input, String expected) {
        PlayerName names = new PlayerName(input);
        assertThat(names.getNames().contains(expected)).isTrue();
    }
}

