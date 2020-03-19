package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {
    @Test
    void 카드뽑기_테스트() {
        Hand hand = new Hand();
        hand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));

        assertThat(hand.getCards().contains(Card.of(Rank.ACE, Suit.CLOVER))).isTrue();
    }

    @Test
    void 단순_합_테스트() {
        Hand hand = new Hand();
        hand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));
        assertThat(hand.calculateDefaultSum()).isEqualTo(Score.of(1));

        hand.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        assertThat(hand.calculateDefaultSum()).isEqualTo(Score.of(11));

        hand.drawCard(() -> Card.of(Rank.THREE, Suit.CLOVER));
        assertThat(hand.calculateDefaultSum()).isEqualTo(Score.of(14));
    }

    @Test
    void 에이스_소지_테스트() {
        Hand hand = new Hand();
        hand.drawCard(() -> Card.of(Rank.THREE, Suit.CLOVER));
        assertThat(hand.hasAce()).isFalse();

        hand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));
        assertThat(hand.hasAce()).isTrue();
    }
}
