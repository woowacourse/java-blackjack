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

class BustTest {
    private GameState bustState;

    @BeforeEach
    void setUp() {
        GameState state = Playing.from(Cards.of(new Card(TrumpCardType.SPADE, TrumpCardNumber.QUEEN),
                new Card(TrumpCardType.SPADE, TrumpCardNumber.TEN)));

        bustState = state.receive(new Card(TrumpCardType.HEART, TrumpCardNumber.KING));
    }

    @DisplayName("버스트 된 상태에서 카드 발급 요청 시 예외가 발생한다.")
    @Test
    void receiveExceptionTest() {
        assertThatThrownBy(() -> bustState.receive(new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("버스트 된 상태에서 카드 발급 요청 시 예외가 발생한다.")

    @Test
    void isAbleToReceiveExceptionCard() {
        assertThat(bustState.isAbleToReceiveCard()).isFalse();
    }

    @DisplayName("버스트 상태와 다른 상태들과 비교했을 때 항상 진다.")
    @Test
    void competeToOtherStateLose() {
        GameState blackjackState = Playing.from(Cards.of(
                new Card(TrumpCardType.SPADE, TrumpCardNumber.ACE),
                new Card(TrumpCardType.SPADE, TrumpCardNumber.TEN))
        );
        assertThat(blackjackState).isInstanceOf(Blackjack.class);
        assertThat(bustState.competeToOtherState(blackjackState)).isEqualTo(Result.LOSE);

        GameState playingState = Playing.from(Cards.of(
                new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE),
                new Card(TrumpCardType.SPADE, TrumpCardNumber.TEN))
        );

        assertThat(playingState).isInstanceOf(Playing.class);
        assertThat(bustState.competeToOtherState(playingState)).isEqualTo(Result.LOSE);
    }
}
