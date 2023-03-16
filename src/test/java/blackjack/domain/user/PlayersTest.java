package blackjack.domain.user;

import blackjack.domain.user.player.Player;
import blackjack.domain.user.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayersTest {

    private List<Player> dummy;

    @BeforeEach
    void initDummyPlayer() {
        dummy = new ArrayList<>();

        for (int playerCount = 0; playerCount < 11; playerCount++) {
            dummy.add(new Player("dummy" + playerCount));
        }
    }

    @Test
    void 플레이어들은_10명_초과_시_예외() {
        //then
        Assertions.assertThatThrownBy(() -> new Players(dummy))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어들은_중복된_이름을_가질_수_없다() {
        // given
        Player dummy1 = new Player("dummy");
        Player dummy2 = new Player("dummy");
        List<Player> dummy = List.of(dummy1, dummy2);

        // then
        Assertions.assertThatThrownBy(() -> new Players(dummy))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
