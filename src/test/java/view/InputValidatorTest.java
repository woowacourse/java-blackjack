package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @DisplayName("플레이어가 0명이면 예외를 던진다.")
    @Test
    void validatePlayerCountTest() {
        assertThatThrownBy(() -> InputValidator.validatePlayerCount(Collections.EMPTY_LIST))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 수는 1명이상 이어야합니다.");
    }

    @DisplayName("플레이어 이름에 중복이 있으면 예외를 던진다.")
    @Test
    void validatePlayerNameDuplicatedTest() {
        assertThatThrownBy(() -> InputValidator.validatePlayerNameDuplicated(List.of("hardy", "hardy")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복된 이름을 입력하면 안됩니다.");
    }

    @DisplayName("플레이어 이름이 딜러이면 예외를 던진다.")
    @Test
    void validatePlayerNameCannotBeSameAsDealerNameTest() {
        assertThatThrownBy(() -> InputValidator.validatePlayerNameCannotBeSameAsDealerName(List.of("딜러", "hardy")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 딜러와 같은 이름일 수 없습니다.");
    }

    @DisplayName("더 카드를 받은 거냐는 질문에 입력이 y or n이 아니면 예외를 던진다.")
    @Test
    void validateAnswerYesOrNoTest() {
        assertThatThrownBy(() -> InputValidator.validateAnswerYesOrNo("yyy"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] y/n만 입력 가능합니다.");
    }

    @DisplayName("배팅금액은 숫자를 입력하지 않으면 예외를 던진다.")
    @Test
    void validateIsNumericTest() {
        assertThatThrownBy(() -> InputValidator.validateIsNumeric("h3232"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 숫자를 입력해주세요.");
    }
}
