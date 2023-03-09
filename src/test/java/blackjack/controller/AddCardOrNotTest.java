package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.player.AddCardOrNot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AddCardOrNotTest {
    @DisplayName("대문자 Y일 경우 YES 를 반환한다.")
    @Test
    void returnYesWhenCapitalY() {
        AddCardOrNot addCardOrNot = AddCardOrNot.of("Y");
        assertThat(addCardOrNot).isEqualTo(AddCardOrNot.YES);
    }

    @DisplayName("대문자 N일 경우 NO 를 반환한다.")
    @Test
    void returnYesWhenCapitalN() {
        AddCardOrNot addCardOrNot = AddCardOrNot.of("N");
        assertThat(addCardOrNot).isEqualTo(AddCardOrNot.NO);
    }

    @DisplayName("올바른 명령어가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "hi", "nn", "yy", "1", "2", "웅", "!"})
    void throwExceptionForInvalidArgument(String command) {
        assertThatThrownBy(() -> AddCardOrNot.of(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 카드 추가 명령어가 아닙니다.");
    }
}