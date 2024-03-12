package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.FIVE;
import static blackjack.domain.card.CardScore.NINE;
import static blackjack.domain.card.CardScore.QUEEN;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.HEART;
import static blackjack.domain.card.CardSymbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("핸드")
public class HandTest {
    @Test
    @DisplayName("에서 뽑은 카드를 저장한다.")
    void cardsCreateTest() {
        // given & when
        Hand hand = new Hand();
        hand.addCard(new Card(SPADE, NINE));
        hand.addCard(new Card(CLUB, ACE));

        // then
        assertThat(hand.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("에서 뽑은 카드들의 합을 구한다.")
    void cardsSumTest() {
        // given & when
        Hand hand = new Hand();
        hand.addCard(new Card(CLUB, FIVE));
        hand.addCard(new Card(SPADE, NINE));

        // then
        assertThat(hand.totalScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("의 카드 점수 합 중 21과 가장 가까운 수를 반환한다.")
    void maxScoreTest() {
        // given
        Hand hand = new Hand();

        // when
        hand.addCard(new Card(SPADE, NINE));
        hand.addCard(new Card(CLUB, QUEEN));
        hand.addCard(new Card(CLUB, ACE));
        hand.addCard(new Card(HEART, ACE));

        // then
        assertThat(hand.totalScore()).isEqualTo(21);
    }
}
