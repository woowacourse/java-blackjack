package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BlackJackGameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<String> names = List.of("위브", "산초");
        Players players = new Players(names);
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        Dealer dealer = new Dealer(cardDeck);

        assertThatCode(() -> new BlackJackGame(players, dealer))
                .doesNotThrowAnyException();
    }

    @DisplayName("결과 계산 테스트")
    @Test
    void judgeResult() {
        String name = "산초";
        Players players = new Players(List.of(name));
        CardDeck cardDeck = CardDeck.createNotShuffledDeck();
        Dealer dealer = new Dealer(cardDeck);
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);

        blackJackGame.initHand(); // 산초 : A,K / 딜러 : Q, J
        Map<Player, Result> resultMap = blackJackGame.getGameResults();
        Result result1 = resultMap.get(new Player(name));

        assertThat(result1).isEqualTo(Result.PLAYER_WIN);
    }
}
