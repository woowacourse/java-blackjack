package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GamerHandTest {
    @Test
    void 카드뽑기_테스트() {
        GamerHand gamerHand = new GamerHand();
        gamerHand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));

        assertThat(gamerHand.getCards().contains(Card.of(Rank.ACE, Suit.CLOVER))).isTrue();
    }

    @Test
    void 단순_합_테스트() {
        GamerHand gamerHand = new GamerHand();
        gamerHand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));
        assertThat(gamerHand.calculateDefaultSum()).isEqualTo(Score.of(1));

        gamerHand.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        assertThat(gamerHand.calculateDefaultSum()).isEqualTo(Score.of(11));

        gamerHand.drawCard(() -> Card.of(Rank.THREE, Suit.CLOVER));
        assertThat(gamerHand.calculateDefaultSum()).isEqualTo(Score.of(14));
    }

    @Test
    void 에이스_소지_테스트() {
        GamerHand gamerHand = new GamerHand();
        gamerHand.drawCard(() -> Card.of(Rank.THREE, Suit.CLOVER));
        assertThat(gamerHand.hasAce()).isFalse();

        gamerHand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));
        assertThat(gamerHand.hasAce()).isTrue();
    }
}
