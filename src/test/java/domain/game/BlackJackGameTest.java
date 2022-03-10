package domain.game;

import domain.participant.Name;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {
    @Test
    @DisplayName("게임 초기화 시 각 플레이어는 2장의 카드를 분배받는다.")
    void create() {
        List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
        BlackJackGame blackJackGame = new BlackJackGame(names);
        boolean match = blackJackGame.getPlayers().stream()
                .mapToInt(player -> player.getCards().getValue().size())
                .anyMatch(cardSize -> cardSize != 2);
        assertThat(match).isFalse();
    }

    @Test
    @DisplayName("플레이어 카드 추가")
    void player_card_add() {
        List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
        BlackJackGame blackJackGame = new BlackJackGame(names);
        Player player = blackJackGame.getPlayers().get(0);
        blackJackGame.drawPlayerCard(player);

        assertThat(blackJackGame.getPlayers().get(0).getCards().getValue().size())
                .isEqualTo(3);
    }
}
