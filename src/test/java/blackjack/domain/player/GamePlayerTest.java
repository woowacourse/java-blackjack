package blackjack.domain.player;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import blackjack.domain.card.CardValue;
import blackjack.fixture.PlayerFixture;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePlayerTest {
    @Test
    @DisplayName("플레이어는 숫자의 합이 21이 넘지 않을 때까지 추가 카드를 받을 수 있다.")
    void can_receive_additional_card() {
        final var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.ACE));

        final var result = sut.isReceivable();

        assertTrue(result);
    }

    @Test
    @DisplayName("플레이어는 숫자의 합이 21이 넘어 가면 추가 카드를 받을 수 없다.")
    void can_not_receive_additional_card() {
        final var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.JACK, CardValue.KING));

        final var result = sut.isReceivable();

        assertFalse(result);
    }
}
