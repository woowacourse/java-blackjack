package blackjack.domain.cardholder;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Hand;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    void 카드를_추가한다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand(card1, card2);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);

        // when
        hand.takeCard(newCard);

        // then
        assertThat(hand.getAllCards()).containsExactly(card1, card2, newCard);
    }

    @Test
    void 가능한_카드의_모든_합을_반환한다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.ACE);
        Hand hand = new Hand(card1, card2);

        // when
        List<Integer> totalValues = hand.calculatePossibleSums();

        // then
        assertThat(totalValues).containsExactlyInAnyOrder(2, 12, 22);
    }

    @Test
    void 모든_카드를_반환한다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand(card1, card2);

        // when
        List<Card> cards = hand.getAllCards();

        // then
        assertThat(cards).containsExactly(card1, card2);
    }
}
