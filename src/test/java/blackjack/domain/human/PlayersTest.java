package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void 플레이어_카드_배분_테스트() {
        Player player1 = Player.of(Name.of("pobi"));
        Player player2 = Player.of(Name.of("jason"));
        Players players = Players.of(List.of(player1, player2));
        players.giveCard();
        players.giveCard();
        assertThat(player1.getCards().size()).isEqualTo(2);
        assertThat(player2.getCards().size()).isEqualTo(2);
    }
}