package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카드들")
public class DeckTest {
    @Test
    @DisplayName("뽑은 카드를 저장한다.")
    void cardsCreateTest() {
        // given & when
        Deck deck = new Deck();
        deck.addCard(Card.SPADE_NINE);
        deck.addCard(Card.CLUB_ACE);

        // then
        assertThat(deck.get().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("뽑은 카드들의 합을 구한다.")
    void cardsSumTest() {
        // given
        int expectedScore = 9 + 5;

        // when
        Deck deck = new Deck();
        deck.addCard(Card.CLUB_FIVE);
        deck.addCard(Card.SPADE_NINE);

        // then
        assertThat(deck.totalScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 점수의 합 중 21과 가장 가까운 수를 반환한다.")
    void maxScoreTest() {
        // given
        Deck deck = new Deck();

        // when
        deck.addCard(Card.SPADE_NINE);
        deck.addCard(Card.CLUB_QUEEN);
        deck.addCard(Card.CLUB_ACE);
        deck.addCard(Card.HEART_ACE);

        // then
        assertThat(deck.totalScore()).isEqualTo(21);
    }

}
