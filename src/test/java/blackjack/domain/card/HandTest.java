package blackjack.domain.card;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.K;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class HandTest {

    private final List<Card> cards = List.of(
            new Card(ACE, HEART),
            new Card(TWO, DIAMOND));
    private Hand hand;

    @BeforeEach
    void setUp() {
        this.hand = new Hand(cards);
    }

    @Test
    @DisplayName("ACE, TWO 카드의 총점을 계산한다.")
    void totalScoreTest_AceTwo() {
        assertThat(hand.totalScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("TWO, THREE 카드의 총점을 계산한다.")
    void totalScoreTest_TwoThree() {
        hand = new Hand(List.of(
                new Card(TWO, HEART),
                new Card(THREE, HEART)
        ));

        assertThat(hand.totalScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("ACE, K 카드의 총점을 계산한다.")
    void totalScoreTest_AceK() {
        hand = new Hand(List.of(
                new Card(ACE, HEART),
                new Card(K, HEART)
        ));

        assertThat(hand.totalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE, ACE 카드의 총점을 계산한다.")
    void totalScoreTest_AceAce() {
        hand = new Hand(List.of(
                new Card(ACE, HEART),
                new Card(ACE, DIAMOND)
        ));

        assertThat(hand.totalScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("ACE, Ace, Ace 카드의 총점을 계산한다.")
    void totalScoreTest_AceAceAce() {
        hand = new Hand(List.of(
                new Card(ACE, HEART),
                new Card(ACE, DIAMOND),
                new Card(ACE, CLOVER)
        ));

        assertThat(hand.totalScore()).isEqualTo(13);
    }
}
