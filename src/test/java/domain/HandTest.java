package domain;

import static org.assertj.core.api.Assertions.*;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @DisplayName("점수 계산 - Ace가 없는 경우")
    @Test
    void ACE가_없는_경우_카드_합() {
        Hand hand = new Hand();

        hand.add(new Card(Suit.DIAMOND, Rank.EIGHT));
        hand.add(new Card(Suit.CLOVER, Rank.JACK));
        hand.add(new Card(Suit.SPADE, Rank.TWO));

        assertThat(hand.calculateScore()).isEqualTo(20);
    }

    @DisplayName("점수 계산 - Ace가 1로 사용되는 경우")
    @Test
    void ACE가_1로_사용되는_경우_카드_합() {
        Hand hand = new Hand();

        hand.add(new Card(Suit.DIAMOND, Rank.ACE));
        hand.add(new Card(Suit.CLOVER, Rank.JACK));
        hand.add(new Card(Suit.SPADE, Rank.TWO));

        assertThat(hand.calculateScore()).isEqualTo(13);
    }

    @DisplayName("점수 계산 - Ace가 11로 사용되는 경우")
    @Test
    void ACE가_11로_사용되는_경우_카드_합() {
        Hand hand = new Hand();

        hand.add(new Card(Suit.DIAMOND, Rank.ACE));
        hand.add(new Card(Suit.CLOVER, Rank.EIGHT));
        hand.add(new Card(Suit.SPADE, Rank.TWO));

        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @DisplayName("점수 계산 - Ace가 1개는 1로, 1개는 11로 사용되는 경우")
    @Test
    void ACE가_2개인_경우_카드_합() {
        Hand hand = new Hand();

        hand.add(new Card(Suit.DIAMOND, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.NINE));
        hand.add(new Card(Suit.CLOVER, Rank.ACE));

        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @DisplayName("카드들을 반환한다.")
    @Test
    void 카드들_반환() {
        Hand hand = new Hand();

        hand.add(new Card(Suit.DIAMOND, Rank.ACE));
        hand.add(new Card(Suit.SPADE, Rank.NINE));
        hand.add(new Card(Suit.CLOVER, Rank.ACE));

        assertThat(hand.getCards()).hasSize(3);
    }
}