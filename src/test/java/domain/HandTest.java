package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Hand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    private List<Card> createCards(Rank... ranks) {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : ranks) {
            Card card = new Card(rank, Suit.HEART);
            cards.add(card);
        }

        return cards;
    }

    @Test
    void 플레이어의_카드의_합이_21을_초과하는지_반환한다() {
        List<Card> cards = createCards(Rank.JACK, Rank.QUEEN, Rank.KING);
        Hand hand = new Hand(cards);

        boolean isBust = hand.isBust();

        assertThat(isBust).isTrue();
    }

    @Test
    void 카드의_합을_계산한다() {
        List<Card> cards = createCards(Rank.TWO, Rank.QUEEN);
        Hand hand = new Hand(cards);

        int score = hand.score();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void 카드_중_에이스_카드가_하나_존재하고_카드의_합이_21_이하인_경우_합이_21에_근접하되_21을_초과하지_않도록_계산한다() {
        List<Card> cards = createCards(Rank.ACE, Rank.QUEEN);
        Hand hand = new Hand(cards);

        int score = hand.score();

        assertThat(score).isEqualTo(21);
    }

    @Test
    void 카드_중_에이스_카드가_여러장_존재하고_카드의_합이_21을_초과하는_경우_합이_21에_근접하되_21을_초과하지_않도록_계산한다() {
        List<Card> cards = createCards(Rank.ACE, Rank.ACE, Rank.QUEEN);
        Hand hand = new Hand(cards);

        int score = hand.score();

        assertThat(score).isEqualTo(12);
    }
}
