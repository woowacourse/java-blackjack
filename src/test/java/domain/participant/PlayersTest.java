package domain.participant;

import static org.junit.jupiter.api.Assertions.*;

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
                new Player(new Name("aa")), new Player(new Name("bb")), new Player(new Name("cc")),
                new Player(new Name("dd")), new Player(new Name("ee")), new Player(new Name("ff")),
                new Player(new Name("gg")), new Player(new Name("hh")), new Player(new Name("ii"))
        );
        assertThrows(IllegalArgumentException.class, () -> {
            new Players(players);
        });
    }

    @Test
    void 플레이어_이름이_중복되면_예외가_발생한다() {
        List<Player> players = List.of(
                new Player(new Name("aa")), new Player(new Name("bb")), new Player(new Name("aa"))
        );
        assertThrows(IllegalArgumentException.class, () -> {
            new Players(players);
        });
    }

    @Test
    void 정상적인_플레이어_목록이면_생성된다() {
        List<Player> players = List.of(
                new Player(new Name("aa")), new Player(new Name("bb")), new Player(new Name("cc"))
        );
        assertDoesNotThrow(() -> {
            new Players(players);
        });
    }
}
