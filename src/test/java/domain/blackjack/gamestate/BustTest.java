package domain.blackjack.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Cards;
import domain.card.TrumpCardNumber;
import domain.card.TrumpCardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {
    private GameState bustState;

    @BeforeEach
    void setUp() {
        GameState startState = Start.from(Cards.of(new Card(TrumpCardType.SPADE, TrumpCardNumber.QUEEN),
                new Card(TrumpCardType.SPADE, TrumpCardNumber.TEN)));

        bustState = startState.receive(new Card(TrumpCardType.HEART, TrumpCardNumber.KING));
    }

    @DisplayName("버스트 된 상태에서 카드 발급 요청 시 예외가 발생한다.")
    @Test
    void receiveExceptionTest() {
        assertThatThrownBy(() -> bustState.receive(new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isAbleToReceiveCard() {
        assertThat(bustState.isAbleToReceiveCard()).isFalse();
    }
}
