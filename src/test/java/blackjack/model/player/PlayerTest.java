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

class PlayerTest {
    @Test
    @DisplayName("플레이어 이름은 한 글자 이상이 아니면 예외가 발생한다")
    void validatePlayerNameLengthTest() {
        // when & then
        assertThatThrownBy(() -> new Player(new PlayerName("")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드 딜링을 하면 플레이어가 카드를 2장 받는다")
    void dealTest() {
        // when
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        // then
        assertThat(player.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어는 카드 합을 21에 가깝게 맞추기 위해 카드를 더 받을 수 있다")
    void drawTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when
        player.drawCard(() -> new Card(Suit.HEART, Denomination.ACE));

        // then
        assertThat(player.getCards()).hasSize(3);
    }

    @Test
    @DisplayName("플레이어는 카드 합이 21을 넘지 않을 경우 얼마든지 카드를 계속 뽑을 수 있다")
    void canDrawTest() {
        // given
        Player player = new Player(new PlayerName("dora"));
        player.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING)
        )));

        // when & then
        assertThat(player.canDrawCard()).isTrue();
    }
}
