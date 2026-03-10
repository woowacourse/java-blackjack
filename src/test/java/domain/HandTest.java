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
    void 참가자의_카드가_21을_초과했는지_계산한다() {
        List<Card> cards = createCards(Rank.JACK, Rank.QUEEN, Rank.KING);
        Hand hand = new Hand(cards);

        boolean isBust = hand.isBust();

        assertThat(isBust).isTrue();
    }

    @Test
    void 카드들의_총_합을_계산한다() {
        List<Card> cards = createCards(Rank.TWO, Rank.QUEEN);
        Hand hand = new Hand(cards);

        int score = hand.score();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void 에이스가_존재하고_합계가_21이하인_경우_유리하게_계산한다() {
        List<Card> cards = createCards(Rank.ACE, Rank.QUEEN);
        Hand hand = new Hand(cards);

        int score = hand.score();

        assertThat(score).isEqualTo(21);
    }

    @Test
    void 에이스가_여러장_존재하고_합계가_21초과하는_경우_유리하게_계산한다() {
        List<Card> cards = createCards(Rank.ACE, Rank.ACE);
        Hand hand = new Hand(cards);

        int score = hand.score();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void 에이스가_한장_존재하고_합계가_21초과하는_경우_유리하게_계산한다() {
        List<Card> cards = createCards(Rank.TEN, Rank.ACE, Rank.TEN, Rank.FIVE);
        Hand hand = new Hand(cards);

        int score = hand.score();

        assertThat(score).isEqualTo(26);
    }
}
