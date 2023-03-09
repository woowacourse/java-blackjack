package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InputValidatorTest {

    @DisplayName("플레이어가 0명이면 예외를 던진다.")
    @Test
    void validatePlayerCountTest() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validatePlayerCount(Collections.EMPTY_LIST))
                .getMessage().equals("[ERROR] 플레이어의 수는 1명이상 이어야합니다.");
    }

    @DisplayName("플레이어 이름에 중복이 있으면 예외를 던진다.")
    @Test
    void validatePlayerNameDuplicatedTest() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validatePlayerNameDuplicated(List.of("hardy", "hardy")))
                .getMessage().equals("[ERROR] 중복된 이름을 입력하면 안됩니다.");
    }

    @DisplayName("플레이어 이름이 딜러이면 예외를 던진다.")
    @Test
    void validatePlayerNameCannotBeSameAsDealerNameTest() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validatePlayerNameCannotBeSameAsDealerName(List.of("딜러", "hardy")))
                .getMessage().equals("[ERROR] 딜러와 같은 이름일 수 없습니다.");
    }

    @DisplayName("더 카드를 받은 거냐는 질문에 입력이 y or n이 아니면 예외를 던진다.")
    @Test
    void validateAnswerYesOrNoTest() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validateAnswerYesOrNo("yyy"))
                .getMessage().equals("[ERROR] y/n만 입력 가능합니다.");
    }

    @DisplayName("배팅금액은 숫자를 입력하지 않으면 예외를 던진다.")
    @Test
    void validateIsNumericTest() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validateIsNumeric("h343"))
                .getMessage().equals("[ERROR] 배팅 금액은 숫자를 입력해주세요.");
    }
}
