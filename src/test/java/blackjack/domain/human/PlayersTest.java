package blackjack.domain.human;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.Card;
import blackjack.domain.Name;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PlayersTest {

    @Test
    public void 생성() {
        Player player = Player.of(Name.of("test"));
        Players players = Players.of(List.of(player));
        assertThat(players.size()).isEqualTo(1);
    }
}