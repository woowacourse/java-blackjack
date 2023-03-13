package domain.blackjack.gamestate;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjack.BlackjackScore;
import domain.card.Card;
import domain.card.Cards;
import domain.card.TrumpCardNumber;
import domain.card.TrumpCardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartTest {

    private static final Card SPADE_TEN = new Card(TrumpCardType.SPADE, TrumpCardNumber.TEN);
    private static final Card SPADE_ACE = new Card(TrumpCardType.SPADE, TrumpCardNumber.ACE);
    private static final Card SPADE_NINE = new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE);

    @DisplayName("게임 상태는 카드와 함께 시작한다.")
    @Test
    void createGameStateSuccessTest() {
        Cards cards = Cards.of(SPADE_TEN, SPADE_NINE);
        GameState gameState = Start.from(cards);

        Cards stateCards = gameState.getCards();
        assertThat(stateCards.getCards()).containsExactly(
                SPADE_TEN, SPADE_NINE
        );
    }

    @DisplayName("게임 시작시 숫자의 합이 21이면 블랙잭 상태가 된다.")
    @Test
    void name() {
        Cards cards = Cards.of(SPADE_TEN, SPADE_ACE);
        GameState gameState = Start.from(cards);

        Cards stateCards = gameState.getCards();
        assertThat(BlackjackScore.from(stateCards)).isEqualTo(BlackjackScore.from(21));
        assertThat(gameState).isInstanceOf(Blackjack.class);
    }
}
