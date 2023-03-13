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

class BlackjackTest {

    private GameState blackjackState;

    @BeforeEach
    void setUp() {
        Cards blackjack = Cards.of(new Card(TrumpCardType.SPADE, TrumpCardNumber.ACE),
                new Card(TrumpCardType.SPADE, TrumpCardNumber.TEN));
        blackjackState = Playing.from(blackjack);
    }

    @DisplayName("블랙잭 상태에서 카드 발급 요청 시 예외가 발생한다.")
    @Test
    void receiveExceptionTest() {
        assertThatThrownBy(() -> blackjackState.receive(new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isAbleToReceiveCard() {
        assertThat(blackjackState.isAbleToReceiveCard()).isFalse();
    }

    @Test
    void getEarningRate() {
    }
}
