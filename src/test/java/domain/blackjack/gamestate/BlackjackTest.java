package domain.blackjack.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.blackjack.Result;
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

    @DisplayName("블랙잭 상태에서는 카드를 추가로 받을 수 없다.")
    @Test
    void isAbleToReceiveCardTest() {
        assertThat(blackjackState.isAbleToReceiveCard()).isFalse();
    }

    @DisplayName("블랙잭 상태에서 카드 발급 요청 시 예외가 발생한다.")
    @Test
    void receiveExceptionTest() {
        assertThatThrownBy(() -> blackjackState.receive(new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("블랙잭 상태는 다른 상태들과 비교했을 때 항상 이긴다.")
    @Test
    void competeToOtherState() {
        GameState competeState = Playing.from(Cards.of(
                new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE),
                new Card(TrumpCardType.SPADE, TrumpCardNumber.TEN))
        );
        assertThat(competeState).isInstanceOf(Playing.class);
        assertThat(blackjackState.competeToOtherState(competeState)).isEqualTo(Result.WIN);

        competeState = competeState.receive(new Card(TrumpCardType.SPADE, TrumpCardNumber.EIGHT));

        assertThat(competeState).isInstanceOf(Bust.class);
        assertThat(blackjackState.competeToOtherState(competeState)).isEqualTo(Result.WIN);
    }

    @Test
    void getEarningRate() {
    }
}
