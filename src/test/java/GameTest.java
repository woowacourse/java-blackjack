import domain.Game;
import domain.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void startBlackJack() {
        Player dealer = new Player();
        Player player = new Player();

        Game game = new Game(dealer, player);
        game.start();

        Assertions.assertThat(dealer.getTotalSize()).isEqualTo(2);
        Assertions.assertThat(player.getTotalSize()).isEqualTo(2);
    }
}
