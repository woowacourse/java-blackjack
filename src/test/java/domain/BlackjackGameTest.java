package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BlackjackGameTest {
    private Dealer dealer;
    private Players players;
    private BlackjackGame game;
    private CardDeck cardDeck;

    @BeforeEach
    void set() {
        dealer = new Dealer();
        players = new Players("pobi,jason");
        CardGenerator cardGenerator = new CardGenerator();
        cardDeck = new CardDeck(cardGenerator.generate());
    }

    @Test
    @DisplayName("딜러에게 카드를 1장 나눠준다.")
    void distributeDealerInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        Map<String, List<String>> result = new HashMap<>();
        result.put("딜러", List.of("A스페이드"));

        blackjackGame.distributeDealer();

        Assertions.assertThat(dealer.getInfo()).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    @DisplayName("플레이어에게 카드를 1장 나눠준다.")
    void distributePlayersInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        Map<String, List<String>> result = new HashMap<>();
        result.put("pobi", List.of("A스페이드"));
        result.put("jason", List.of("2스페이드"));

        blackjackGame.distributePlayers();

        Assertions.assertThat(players.getInfo()).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    @DisplayName("플레이어의 최종 승패 결과를 가져온다.")
    void calculateWinOrLoseTest1() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        blackjackGame.distributeInitialCard();

        Map<String, Result> playerResult = new HashMap<>();
        playerResult.put("pobi", Result.LOSE);
        playerResult.put("jason", Result.LOSE);

        Assertions.assertThat(blackjackGame.getPlayersResult()).usingRecursiveComparison().isEqualTo(playerResult);
    }

    @Test
    @DisplayName("플레이어와 딜러의 승패 계산")
    void calculateWinOrLoseTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        Assertions.assertThat(blackjackGame.isPlayerWin(21, 10)).isEqualTo(Result.LOSE);
        Assertions.assertThat(blackjackGame.isPlayerWin(10, 21)).isEqualTo(Result.WIN);
        Assertions.assertThat(blackjackGame.isPlayerWin(25, 22)).isEqualTo(Result.LOSE);
        Assertions.assertThat(blackjackGame.isPlayerWin(21, 21)).isEqualTo(Result.DRAW);
    }
}
