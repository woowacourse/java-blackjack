package blackjack;

import static blackjack.Rank.*;
import static blackjack.Suit.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("카드 발급 테스트")
    void playerCardTake() {
        Player player = new Player(new Card(QUEEN, CLOVER), new Card(KING, SPADE));
        Assertions.assertThat(player.isHittable()).isTrue();
    }

    @Test
    @DisplayName("카드 발급 실패 테스트")
    void playerCardCantTake() {
        Player player = new Player(new Card(QUEEN, CLOVER), new Card(ACE, SPADE));
        Assertions.assertThat(player.isHittable()).isFalse();
    }
}