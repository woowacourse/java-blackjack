package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("참여자 이름은 한 글자 이상이 아니면 예외가 발생한다")
    void validatePlayerNameLengthTest() {
        // when & then
        assertThatThrownBy(() -> new Player("", () -> new Card(Suit.HEART, Denomination.TWO)))
                .isInstanceOf(IllegalArgumentException.class);
    }

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
        assertThat(player.getCards()).hasSize(3);
    }
}
