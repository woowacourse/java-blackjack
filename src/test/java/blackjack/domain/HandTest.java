package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    void 첫_카드는_비어있다() {
        Hand hand = Hand.init();
        assertThat(hand.countCards()).isEqualTo(0);
    }

    @Test
    void 카드를_받으면_카드가_늘어난다() {
        Hand hand = Hand.init();
        TrumpCard newCard = TrumpCard.of(Suit.SPADE, Rank.ACE);
        hand = hand.receive(newCard);
        assertThat(hand.countCards()).isEqualTo(1);
    }

    @Test
    void 현재까지_확보한_카드_총_점수를_계산한다() {
        Hand hand = Hand.init();
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        TrumpCard heartFive = TrumpCard.of(Suit.HEART, Rank.FIVE);

        hand = hand.receive(spadeKing);
        hand = hand.receive(heartFive);

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(15);
    }


    @Test
    void 카드_총합이_21을_넘으면_에이스_값이_1로_변환된다() {
        Hand hand = Hand.init();
        TrumpCard heartAce = TrumpCard.of(Suit.HEART, Rank.ACE);
        TrumpCard diamondNine = TrumpCard.of(Suit.DIAMOND, Rank.NINE);

        hand = hand.receive(heartAce);
        hand = hand.receive(heartAce);
        hand = hand.receive(diamondNine);

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(21);
    }
}