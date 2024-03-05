package player;

import card.Card;
import card.Deck;
import card.Number;
import card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 점수를 계산한다.")
    void calculateScoreTest() {
        // given
        Player player = new Player();
        Deck deck = new Deck(List.of(new Card(Symbol.SPADE, Number.KING), new Card(Symbol.HEART, Number.EIGHT)));
        // when
        player.drawCard(deck);
        player.drawCard(deck);
        // then
        assertThat(player.calculateScore()).isEqualTo(18);
    }
}
