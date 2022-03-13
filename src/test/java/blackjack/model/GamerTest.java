package blackjack.model;

import static blackjack.model.Rank.*;
import static blackjack.model.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.player.Gamer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamerTest {

    @Test
    @DisplayName("게이머 첫 2장 카드 공개")
    void gamerOpenCards() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(FIVE, HEART));
        assertThat(gamer.openCards()).hasSize(2);
        assertThat(gamer.openCards()).contains(new Card(QUEEN, CLOVER), new Card(FIVE, HEART));
    }

    @Test
    @DisplayName("게이머 20이하일 경우 카드 발급 가능")
    void gamerCardTake() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(KING, SPADE));
        assertThat(gamer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("게이머 21이상일 경우 카드 발급 불가능")
    void gamerCardCantTake() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(ACE, SPADE));
        assertThat(gamer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("게이머 카드 발급")
    void gamerTakeCards() {
        Gamer dealer = new Gamer("gamer", new Card(JACK, DIAMOND), new Card(QUEEN, CLOVER));
        dealer.take(new Card(ACE, HEART));
        assertThat(dealer.score()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("게이머 카드 발급 실패")
    void gamerTakeInvalidCard() {
        Gamer dealer = new Gamer("gamer", new Card(JACK, DIAMOND), new Card(ACE, HEART));
        assertThatThrownBy(() -> dealer.take(new Card(FOUR, HEART)))
                .isInstanceOf(IllegalStateException.class);
    }
}
