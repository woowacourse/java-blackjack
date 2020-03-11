package domain.user;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("Player 생성")
    void create() {
        assertThat(new Player("이름")).isInstanceOf(Player.class);
    }

    @Test
    @DisplayName("플레이어 승리 여부 확인 - 딜러와 동점일 경우 승리")
    void isWin() {
        Dealer dealer = new Dealer();
        Player player = new Player("player");

        dealer.addCard(Card.of("스페이드", "K"));
        player.addCard(Card.of("스페이드", "9"));
        assertThat(player.isWin(dealer)).isFalse();

        player.addCard(Card.of("스페이드", "10"));
        assertThat(player.isWin(dealer)).isTrue();

        dealer.addCard(Card.of("스페이드", "9"));
        assertThat(player.isWin(dealer)).isTrue();
    }
}
