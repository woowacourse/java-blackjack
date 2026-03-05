package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    @Test
    void 참가자의_카드가_21을_초과했는지_계산한다() {
        List<Card> cards = List.of(new Card(Rank.KING, Suit.HEART), new Card(Rank.QUEEN, Suit.HEART), new Card(Rank.JACK, Suit.HEART));
        Hand hand = new Hand(cards);

        boolean isBust = hand.isBust();

        assertThat(isBust).isTrue();
    }

    @Test
    void 카드들의_총_합을_계산한다() {
        List<Card> cards = List.of(new Card(Rank.TWO, Suit.HEART), new Card(Rank.QUEEN, Suit.HEART));
        Hand hand = new Hand(cards);

        int score = hand.score();
        assertThat(score).isEqualTo(12);
    }

}