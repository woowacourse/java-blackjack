package team.blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    /**
     * 점수 계산 테스트
     */
    @Test
    void 에이스와_킹_두_장은_블랙잭_21로_정상_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.HEARTS, Rank.ACE),
                Card.of(Suit.HEARTS, Rank.KING)
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
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.TEN)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(15);
    }

    @Test
    void ACE를_제외한_다른_카드들의_총합이_10인_이하인_경우_에이스를_11로_해석한다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(20);
    }

    @Test
    void ACE를_제외한_다른_카드들의_총합이_10인_경우_에이스를_11로_해석한다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.HEARTS, Rank.SIX),
                Card.of(Suit.HEARTS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(21);
    }

    @Test
    void ACE를_제외한_다른_카드들의_총합이_11_이상인_경우_에이스를_1로_해석한다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.HEARTS, Rank.ACE),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SEVEN)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(13);
    }

    @Test
    void ACE를_제외한_다른_카드들의_총합이_11인_경우_에이스를_1로_해석한다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SIX),
                Card.of(Suit.HEARTS, Rank.ACE)
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
                Card.of(Suit.HEARTS, Rank.ACE),
                Card.of(Suit.DIAMONDS, Rank.ACE)
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
                Card.of(Suit.HEARTS, Rank.NINE),
                Card.of(Suit.HEARTS, Rank.ACE),
                Card.of(Suit.DIAMONDS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(21);
    }

    @Test
    void 숫자10과_6이후에_ACE가_2개_오는_경우_각각_1로_계산된다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.CLUBS, Rank.KING),
                Card.of(Suit.HEARTS, Rank.SIX),
                Card.of(Suit.SPADES, Rank.ACE),
                Card.of(Suit.HEARTS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        assertThat(hand.getScore()).isEqualTo(18);
    }

    /**
     * 버스트 유무 체크
     */
    @Test
    void 각_핸드의_점수는_21점을_초과하는_경우_버스트이다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.SPADES, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.QUEEN),
                Card.of(Suit.DIAMONDS, Rank.KING)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        Assertions.assertEquals(true, hand.isBust());
    }

    @Test
    void 각_핸드의_점수는_21점을_초과하지_않는_경우_버스트가_아니다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.SPADES, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.QUEEN)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        Assertions.assertEquals(false, hand.isBust());
    }

    /**
     * 블랙잭 유무 테스트
     */
    @Test
    void 각_핸드의_카드_개수가_2개이고_점수가_21점인_경우_블랙잭이다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.SPADES, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        Assertions.assertEquals(true, hand.isBlackjack());
    }

    @Test
    void 각_핸드의_카드_개수가_3개이상이고_점수가_21점인_경우_블랙잭이_아니다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.SPADES, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.QUEEN),
                Card.of(Suit.HEARTS, Rank.ACE)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        Assertions.assertEquals(false, hand.isBlackjack());
    }

    @Test
    void 각_핸드의_점수가_21점이_아닌_경우_블랙잭이_아니다() {
        Hand hand = new Hand();
        List<Card> cards = List.of(
                Card.of(Suit.SPADES, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.QUEEN)
        );

        for (Card card : cards) {
            hand.addCard(card);
        }

        Assertions.assertEquals(false, hand.isBlackjack());
    }
}
