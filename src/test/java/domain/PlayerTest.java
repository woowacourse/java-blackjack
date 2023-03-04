package domain;

import domain.user.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("참가자가 받은 카드를 조회한다.")
    void dealCardToPlayerTest() {
        Player player = new Player("echo");
        player.dealt(new Card(Denomination.ACE, Suit.SPADE));
        List<Card> playerHand = player.showHand();
        Assertions.assertThat(playerHand).hasSize(1);
        Assertions.assertThat(playerHand).containsExactly(new Card(Denomination.ACE, Suit.SPADE));
    }

    @Test
    @DisplayName("카드가 없는 플레이어의 카드를 조회할 경우 오류를 던진다.")
    void getReadyCardsTestFailed() {
        Player player = new Player("echo");
        Assertions.assertThatThrownBy(player::faceUpInitialHand)
            .isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("현재의 점수를 계산하여 반환한다.")
    void calculateScore() {
        Player player = new Player("echo");
        player.dealt(new Card(Denomination.ACE, Suit.SPADE));
        player.dealt(new Card(Denomination.THREE, Suit.HEART));
        Assertions.assertThat(player.calculatePoint()).isEqualTo(4);
    }
}
