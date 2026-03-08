package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void 카드의_점수를_반환한다() {
        Card card = new Card(Rank.KING, Suit.SPADE);
        assertThat(card.getScore()).isEqualTo(10);
    }

    @Test
    void ACE의_점수는_1이다() {
        Card card = new Card(Rank.ACE, Suit.HEART);
        assertThat(card.getScore()).isEqualTo(1);
    }


}