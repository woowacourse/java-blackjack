package team.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    @Test
    void 에이스와_킹_두_장은_블랙잭_21로_정상_계산된다() {
        Hand hand = new Hand();
        hand.addCard(Card.ACE_OF_HEARTS);
        hand.addCard(Card.KING_OF_HEARTS);

        assertThat(hand.getScore()).isEqualTo(21);
    }

    @Test
    void 숫자_카드만_있을_때_합이_정확히_계산된다() {
        Hand hand = new Hand();
        hand.addCard(Card.FIVE_OF_HEARTS);
        hand.addCard(Card.TEN_OF_HEARTS);

        assertThat(hand.getScore()).isEqualTo(15);
    }

    @Test
    void 다른_카드들의_총합이_10인_이하인_경우_에이스가_11로_계산된다() {
        Hand hand = new Hand();
        hand.addCard(Card.FIVE_OF_HEARTS);
        hand.addCard(Card.FOUR_OF_HEARTS);
        hand.addCard(Card.ACE_OF_HEARTS);

        assertThat(hand.getScore()).isEqualTo(20);
    }

    @Test
    void 다른_카드들의_총합이_10인인_경우_에이스가_11로_계산된다() {
        Hand hand = new Hand();
        hand.addCard(Card.SIX_OF_HEARTS);
        hand.addCard(Card.FOUR_OF_HEARTS);
        hand.addCard(Card.ACE_OF_HEARTS);

        assertThat(hand.getScore()).isEqualTo(21);
    }

    @Test
    void 다른_카드들의_총합이_11_이상인_경우_에이스가_1로_계산된다() {
        Hand hand = new Hand();
        hand.addCard(Card.FIVE_OF_HEARTS);
        hand.addCard(Card.SEVEN_OF_HEARTS);
        hand.addCard(Card.ACE_OF_HEARTS);

        assertThat(hand.getScore()).isEqualTo(13);
    }

    @Test
    void 다른_카드들의_총합이_11인인_경우_에이스가_1로_계산된다() {
        Hand hand = new Hand();
        hand.addCard(Card.FIVE_OF_HEARTS);
        hand.addCard(Card.SIX_OF_HEARTS);
        hand.addCard(Card.ACE_OF_HEARTS);

        assertThat(hand.getScore()).isEqualTo(12);
    }
}
