package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Command;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
    private BlackJackGame blackJackGame;

    @BeforeEach
    void init() {
        List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
        blackJackGame = new BlackJackGame(names);
    }

    @Test
    @DisplayName("게임 초기화 시 각 플레이어는 2장의 카드를 분배받는다.")
    void create() {
        boolean match = blackJackGame.findPlayers().stream()
                .mapToInt(player -> player.getCards().getValue().size())
                .anyMatch(cardSize -> cardSize != 2);
        assertThat(match).isFalse();
    }

    @Test
    @DisplayName("플레이어 카드 추가")
    void player_card_add() {
        Participant player = blackJackGame.findPlayers().get(0);
        blackJackGame.drawCardByCommand(player, Command.HIT);

        assertThat(blackJackGame.findPlayers().get(0).getCards().getValue().size())
                .isEqualTo(3);
    }

    @Test
    @DisplayName("딜러 가져오기")
    void find_dealer() {
        assertThat(blackJackGame.findDealer()).isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("플레이어들 가져오기")
    void find_players() {
        List<String> playerNames = blackJackGame.findPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.toUnmodifiableList());

        assertThat(playerNames).contains("pobi", "jason");
    }
}
