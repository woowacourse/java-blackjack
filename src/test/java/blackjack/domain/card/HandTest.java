package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.Score;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("핸드에 카드를 추가한다")
    @Test
    void testAppend() {
        // given
        Card card = new Card(CardRank.THREE, CardSuit.DIAMOND);

        Hand hand = new Hand(new ArrayList<>());

        // when
        hand.append(card);

        // then
        assertThat(hand.getCards()).containsExactly(card);
    }

    @DisplayName("핸드에 Ace가 있을 때 합이 21을 초과하지 않으면 Ace는 11로 계산한다.")
    @Test
    void testCalculateScoreAcePresentAndNotBust() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.ACE, CardSuit.HEART));
        cards.add(new Card(CardRank.ACE, CardSuit.DIAMOND));
        cards.add(new Card(CardRank.NINE, CardSuit.HEART));
        Hand hand = new Hand(cards);

        // when
        Score score = hand.calculateHandScore();

        // then
        assertThat(score).extracting("value").isEqualTo(21);
    }

    @DisplayName("핸드에 Ace가 있을 때 합이 21을 초과하면 Ace는 11로 계산하지 않는다.")
    @Test
    void testCalculateScoreAcePresentAndBust() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.ACE, CardSuit.HEART));
        cards.add(new Card(CardRank.TWO, CardSuit.HEART));
        cards.add(new Card(CardRank.NINE, CardSuit.HEART));
        Hand hand = new Hand(cards);

        // when
        Score score = hand.calculateHandScore();

        // then
        assertThat(score).extracting("value").isEqualTo(12);
    }

    @DisplayName("핸드에 Ace가 없으면 합을 변경하지 않는다.")
    @Test
    void testCalculateScoreAceAbsent() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.TWO, CardSuit.HEART));
        cards.add(new Card(CardRank.NINE, CardSuit.HEART));
        Hand hand = new Hand(cards);

        // when
        Score score = hand.calculateHandScore();

        // then
        assertThat(score).extracting("value").isEqualTo(11);
    }

}
