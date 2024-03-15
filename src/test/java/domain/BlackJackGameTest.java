package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.FixtureCard.TWO_HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BlackJackGameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<String> names = List.of("위브", "산초");
        Players players = new Players(names);
        Dealer dealer = new Dealer(List.of(TWO_HEART));

        assertThatCode(() -> new BlackJackGame(players, dealer))
                .doesNotThrowAnyException();
    }

    @DisplayName("결과 계산 테스트")
    @Test
    void judgeResult() {
        String name = "산초";
        Players players = new Players(List.of(name));
        Dealer dealer = new Dealer(List.of(TWO_HEART));
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);

        Map<Player, PlayerGameResult> resultMap = blackJackGame.getPlayerGameResult();

        PlayerGameResult playerGameResult = resultMap.get(new Player(name));

        assertThat(playerGameResult).isEqualTo(PlayerGameResult.LOSE);
    }
}
