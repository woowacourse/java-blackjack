package blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    @Test
    void create() {
        assertThat(Player.of("포비")).isNotNull();
    }

    @Test
    void append() {
        Player player = Player.of("포비");
        player.append(new Card(Symbol.ACE, Type.CLUB));
        player.append(new Card(Symbol.EIGHT, Type.HEART));

        Assertions.assertThat(player.getCards())
                .isEqualTo(Arrays.asList(new Card(Symbol.ACE, Type.CLUB),
                        new Card(Symbol.EIGHT, Type.HEART)));
    }
}