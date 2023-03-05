package blackjack.game;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackJackGameTest {

    @Test
    @DisplayName("blackJackGame()는 생성자를 생성할 시 플레이어에게 두장의 카드를 준다.")
    void init_test() {
        // given & when
        BlackJackGame blackJackGame = new BlackJackGame(List.of(new Name("채채")),new Dealer());
        int playerCards = blackJackGame.getPlayers().getPlayers().get(0).getPlayerCards().size();

        // then
        Assertions.assertThat(playerCards).isEqualTo(2);
    }
}
