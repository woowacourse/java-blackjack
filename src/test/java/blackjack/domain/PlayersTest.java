package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.dto.BlackjackResult;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerResult;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("플레이어들")
public class PlayersTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private Players players;
    private final String nameA = "choco";
    private final String nameB = "clover";
    private final String batting = "1000";

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
        players = Players.of(List.of(nameA, nameB), List.of(batting, batting), dealer);
    }

    @DisplayName("플레이어 이름이 중복되면 예외가 발생한다.")
    @Test
    void validateDuplicatedNames() {
        //given
        List<String> names = List.of("choco", "choco", "chip");
        List<String> battings = List.of("1000", "1000", "1000");

        //when & then
        assertThatThrownBy(() -> Players.of(names, battings, dealer))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자간의 승패를 계산한다.")
    @Test
    void decideResult() {
        //given
        BlackjackResult blackjackResult = players.createResult(dealer);

        //when & then
        assertAll(
                () -> assertThat(blackjackResult.findPlayerResultByName(nameA)).isEqualTo(GameResult.LOSE),
                () -> assertThat(blackjackResult.findPlayerResultByName(nameB)).isEqualTo(GameResult.LOSE),
                () -> assertThat(blackjackResult.findDealerResultByGameResult(GameResult.WIN)).isEqualTo(2),
                () -> assertThat(blackjackResult.findDealerResultByGameResult(GameResult.LOSE)).isEqualTo(0)
        );
    }

    @DisplayName("플레이어들의 승패를 토대로 수익률을 계산한다.")
    @Test
    void calculateProfits() {
        //given
        Player choco = players.getPlayers().get(0);
        Player clover = players.getPlayers().get(1);

        PlayerResult playerResult = new PlayerResult();
        playerResult.addResult(choco, GameResult.WIN);
        playerResult.addResult(clover, GameResult.LOSE);

        BlackjackResult blackjackResult = new BlackjackResult(DealerResult.of(0, 0, 0), playerResult);

        Map<Player, Integer> profitResult = new HashMap<>();
        profitResult.put(choco, 0);
        profitResult.put(clover, -2000);

        //when & then
        assertThat(players.calculateProfits(blackjackResult)).isEqualTo(profitResult);
    }
}
