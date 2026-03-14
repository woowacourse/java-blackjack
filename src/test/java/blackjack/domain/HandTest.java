package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HandTest {
    private Hand handWith(TrumpCard... cards) {
        Hand hand = Hand.init();
        for (TrumpCard card : cards) {
            hand.add(card);
        }
        return hand;
    }

    private TrumpCard card(Suit suit, Rank rank) {
        return TrumpCard.of(suit, rank);
    }

    @Test
    void 첫_카드는_비어있다() {
        Hand hand = Hand.init();
        assertThat(hand.countCards()).isEqualTo(0);
    }

    @Test
    void 카드를_받으면_카드가_늘어난다() {
        Hand hand = Hand.init();
        TrumpCard newCard = TrumpCard.of(Suit.SPADE, Rank.ACE);

        hand.add(newCard);

        assertThat(hand.countCards()).isEqualTo(1);
    }

    @Test
    void 현재까지_확보한_카드_총_점수를_계산한다() {
        Hand hand = handWith(
                card(Suit.SPADE, Rank.KING),
                card(Suit.HEART, Rank.FIVE)
        );

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(15);
    }

    @Test
    void 카드_총합이_21을_이하면_에이스는_11로_계산된다(){
        Hand hand = handWith(
                card(Suit.HEART, Rank.ACE),
                card(Suit.SPADE, Rank.NINE)
        );

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(20);
    }

    @Test
    void 카드_총합이_21을_넘으면_에이스_값이_1로_변환되어_계산된다() {
        Hand hand = handWith(
                card(Suit.HEART, Rank.ACE),
                card(Suit.SPADE, Rank.KING),
                card(Suit.DIAMOND, Rank.NINE)
        );

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(20);
    }

    @Test
    void 카드_총합이_21을_넘으면_에이스_값이_여러_개라도_각각의_에이스가_1로_변환되어_계산된다() {
        Hand hand = handWith(
                card(Suit.HEART, Rank.ACE),
                card(Suit.SPADE, Rank.ACE),
                card(Suit.DIAMOND, Rank.NINE),
                card(Suit.DIAMOND, Rank.KING)
        );

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @Test
    void 카드_총합이_21_이하면_버스트가_아니다(){
        Hand hand = handWith(
                card(Suit.SPADE, Rank.KING),
                card(Suit.HEART, Rank.FIVE)
        );

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void 카드_총합이_21을_초과하면_버스트이다(){
        Hand hand = handWith(
                card(Suit.SPADE, Rank.KING),
                card(Suit.HEART, Rank.KING),
                card(Suit.HEART, Rank.THREE)
        );

        assertThat(hand.isBust()).isTrue();
    }
}