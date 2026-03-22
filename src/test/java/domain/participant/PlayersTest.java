package domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import domain.betting.BettingAmount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {
    
    @Test
    void 플레이어가_0명이면_예외가_발생한다() {
        List<Player> players = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Players(players);
        });
    }

    @Test
    void 플레이어가_8명을_초과하면_예외가_발생한다() {
        List<Player> players = List.of(
                createPlayer("aa"), createPlayer("bb"), createPlayer("cc"),
                createPlayer("dd"), createPlayer("ee"), createPlayer("ff"),
                createPlayer("gg"), createPlayer("hh"), createPlayer("ii")
        );
        assertThrows(IllegalArgumentException.class, () -> {
            new Players(players);
        });
    }

    @Test
    void 플레이어_이름이_중복되면_예외가_발생한다() {
        List<Player> players = List.of(
                createPlayer("aa"), createPlayer("bb"), createPlayer("aa")
        );
        assertThrows(IllegalArgumentException.class, () -> {
            new Players(players);
        });
    }

    @Test
    void 정상적인_플레이어_목록이면_생성된다() {
        List<Player> players = List.of(
                createPlayer("aa"), createPlayer("bb"), createPlayer("cc")
        );
        assertDoesNotThrow(() -> {
            new Players(players);
        });
    }

    private Player createPlayer(String name) {
        return new Player(new Name(name), new BettingAmount(BigDecimal.valueOf(1000)));
    }
}
