package blackjack.controller;

import blackjack.domain.card.Deck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
class BlackjackControllerTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참가자 이름을 올바르게 작성되어야 한다.")
    public void checkParticipantNames(String value) {
        BlackjackController blackjackController = new BlackjackController();
        Assertions.assertThatThrownBy(() -> blackjackController.createParticipants(value, new Deck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참여자의 이름을 입력해주세요.");
    }
}