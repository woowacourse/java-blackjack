package blackjack;

import static blackjack.Rank.*;
import static blackjack.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {

    @Test
    @DisplayName("카드 발급 테스트")
    void playerCardTake() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(KING, SPADE));
        assertThat(gamer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("카드 발급 실패 테스트")
    void playerCardCantTake() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(ACE, SPADE));
        assertThat(gamer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("플레이어 첫 2장 카드 공개")
    void openCards() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(FIVE, HEART));
        assertThat(gamer.openCards()).hasSize(2);
        assertThat(gamer.openCards()).contains(new Card(QUEEN, CLOVER), new Card(FIVE, HEART));
    }

    // 게이머 점수(score)
    // 게이머 카드 발급
    // 게이머 카드 발급 실패(예외)
}