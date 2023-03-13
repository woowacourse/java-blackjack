package domain.blackjack.gamestate;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjack.BlackjackScore;
import domain.card.Card;
import domain.card.Cards;
import domain.card.TrumpCardNumber;
import domain.card.TrumpCardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingTest {

    private static final Card SPADE_TEN = new Card(TrumpCardType.SPADE, TrumpCardNumber.TEN);
    private static final Card SPADE_ACE = new Card(TrumpCardType.SPADE, TrumpCardNumber.ACE);
    private static final Card SPADE_NINE = new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE);
    private static final Card HEART_NINE = new Card(TrumpCardType.HEART, TrumpCardNumber.NINE);

    @DisplayName("게임은 카드와 함께 시작한다.")
    @Test
    void createGameStateSuccessTest() {
        Cards cards = Cards.of(SPADE_TEN, SPADE_NINE);
        GameState gameState = Playing.from(cards);

        Cards stateCards = gameState.getCards();
        assertThat(stateCards.getCards()).containsExactly(
                SPADE_TEN, SPADE_NINE
        );
    }

    @DisplayName("게임은 2장의 카드로만 시작할 수 있다.")
    @Test
    void createGameStateFailTest() {
        Cards cards = Cards.of(SPADE_TEN, SPADE_NINE, SPADE_ACE);
        Assertions.assertThatThrownBy(() -> Playing.from(cards))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("게임 시작시 숫자의 합이 21이면 블랙잭 상태가 된다.")
    @Test
    void createBlackjackFromStartSuccessTest() {
        Cards cards = Cards.of(SPADE_TEN, SPADE_ACE);
        GameState gameState = Playing.from(cards);

        Cards stateCards = gameState.getCards();
        assertThat(BlackjackScore.from(stateCards)).isEqualTo(BlackjackScore.getMaxScore());
        assertThat(gameState).isInstanceOf(Blackjack.class);
        assertThat(gameState).isNotInstanceOf(Playing.class);
    }

    @DisplayName("게임 시작 후 카드를 추가로 받을 수 있다.")
    @Test
    void receiveSuccessTest() {
        Cards cards = Cards.of(SPADE_TEN, SPADE_NINE);
        GameState gameState = Playing.from(cards);

        gameState = gameState.receive(SPADE_ACE);
        Cards stateCards = gameState.getCards();
        assertThat(stateCards.getCards()).hasSize(3);
    }

    @DisplayName("게임 시작 후 추가로 받은 카드의 합이 21을 넘으면 버스타 상태가 된다.")
    @Test
    void createBustFromStartSuccessTest() {
        Cards cards = Cards.of(SPADE_TEN, SPADE_NINE);
        GameState gameState = Playing.from(cards);

        gameState = gameState.receive(HEART_NINE);
        BlackjackScore stateScore = BlackjackScore.from(gameState.getCards());
        assertThat(stateScore.isGreaterThan(BlackjackScore.getMaxScore())).isTrue();

        assertThat(gameState).isInstanceOf(Bust.class);
        assertThat(gameState).isNotInstanceOf(Playing.class);
    }
}
