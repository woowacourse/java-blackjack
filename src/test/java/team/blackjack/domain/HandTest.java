package team.blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import team.blackjack.domain.rule.DefaultBlackjackRule;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    @Test
    void 에이스와_킹_두_장은_블랙잭_21로_정상_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.HEARTS, Rank.KING)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(21);
    }

    @Test
    void 숫자_카드만_있을_때_합이_정확히_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.TEN)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(15);
    }

    @Test
    void 다른_카드들의_총합이_10인_이하인_경우_에이스가_11로_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(20);
    }

    @Test
    void 다른_카드들의_총합이_10인인_경우_에이스가_11로_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.SIX),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(21);
    }

    @Test
    void 다른_카드들의_총합이_11_이상인_경우_에이스가_1로_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.SEVEN)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(13);
    }

    @Test
    void 다른_카드들의_총합이_11인인_경우_에이스가_1로_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.SIX),
                new Card(Suit.HEARTS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(12);
    }

    @Test
    void ACE가_2장_들어오는_경우_ACE는_각각_1과_11로_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(12);
    }

    @Test
    void ACE가_2장과_숫자9가_들어오는_경우_ACE는_각각_1과_11로_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.NINE),
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(21);
    }
}
