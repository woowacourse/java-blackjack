package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.vo.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerCardsTest {

    @Test
    @DisplayName("카드를 받을 수 있다")
    void addCardTest() {
        PlayerCards playerCards = new PlayerCards();
        Card card = new Card(CardSuit.SPADE, CardNumber.ACE);

        playerCards.add(card);

        assertThat(playerCards.toList()).contains(card);
    }

    @Test
    @DisplayName("카드의 점수를 계산한다")
    void calculateScoreTest() {
        PlayerCards playerCards = new PlayerCards();
        Card one = new Card(CardSuit.SPADE, CardNumber.ACE);
        Card two = new Card(CardSuit.SPADE, CardNumber.THREE);

        playerCards.add(one);
        playerCards.add(two);

        assertThat(playerCards.getScore()).isEqualTo(Score.of(14));
    }

    @Test
    @DisplayName("에이스가 있을 때의 점수를 계산한다")
    void calculateAceScoreTest() {
        PlayerCards playerCards = new PlayerCards();
        Card one = new Card(CardSuit.SPADE, CardNumber.ACE);
        Card two = new Card(CardSuit.HEART, CardNumber.ACE);
        Card three = new Card(CardSuit.HEART, CardNumber.TEN);

        playerCards.add(one);
        playerCards.add(two);
        playerCards.add(three);

        assertThat(playerCards.getScore()).isEqualTo(Score.of(12));
    }
}
