package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 점수가 21점을 넘지 않으면 카드를 뽑을 수 있다.")
    void canReceiveCard() {
        // given
        Player player = new Player("luke");
        // when& then
        assertThat(player.canReceive()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점을 넘으면 카드를 뽑을 수 없다.")
    void cantReceiveCard() {
        // given
        Player player = new Player("luke");
        player.receiveCard(new Card(Figure.CLOVER, Number.ACE));
        player.receiveCard(new Card(Figure.CLOVER, Number.KING));

        // when& then
        assertThat(player.canReceive()).isFalse();
    }
}
