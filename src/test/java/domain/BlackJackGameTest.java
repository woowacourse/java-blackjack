package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.FixtureCardDeck.NOT_SHUFFLED_CARD_DECK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BlackJackGameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<String> names = List.of("위브", "산초");
        Players players = new Players(names);
        Dealer dealer = new Dealer(NOT_SHUFFLED_CARD_DECK);

        assertThatCode(() -> new BlackJackGame(players, dealer))
                .doesNotThrowAnyException();
    }

    @DisplayName("결과 계산 테스트")
    @Test
    void judgeResult() {
        String name = "산초";
        Players players = new Players(List.of(name));
        Dealer dealer = new Dealer(NOT_SHUFFLED_CARD_DECK);
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);

        blackJackGame.initHand(); // 딜러 : 2하트, 3하트 / 산초 : 4하트, 5하트
        Map<Player, PlayerGameResult> resultMap = blackJackGame.getPlayerGameResult();

        PlayerGameResult playerGameResult = resultMap.get(new Player(name));

        assertThat(playerGameResult).isEqualTo(PlayerGameResult.WIN);
    }
}
