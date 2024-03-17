package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("21을 넘지 않을 경우 얼마든지 카드를 계속 뽑을 수 있다")
    void canHitTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Denomination.QUEEN),
                new Card(Suit.HEART, Denomination.QUEEN)
        );
        Player player = new Player("dora", new SequentialCardGenerator(cards));

        // when & then
        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("각 참여자들이 카드의 합을 맞추기 위해 카드를 더 받을 수 있다")
    void hitTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.HEART, Denomination.QUEEN),
                new Card(Suit.HEART, Denomination.QUEEN)
        );
        Player player = new Player("dora", new SequentialCardGenerator(cards));

        // when
        player.hit(() -> new Card(Suit.HEART, Denomination.TWO));

        // then
        assertThat(player.getCards().getSize()).isEqualTo(3);
    }
}
