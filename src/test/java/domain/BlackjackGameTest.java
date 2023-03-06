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

        Map<String, List<Card>> result = new LinkedHashMap<>();
        result.put("딜러", List.of(new Card(CardNumber.ACE, CardPattern.SPADE)));

        blackjackGame.distributeDealer();

        Assertions.assertThat(dealer.getInfo()).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    @DisplayName("플레이어에게 카드를 1장 나눠준다.")
    void distributePlayersInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        Map<String, List<Card>> result = new LinkedHashMap<>();
        result.put("pobi", List.of(new Card(CardNumber.ACE, CardPattern.SPADE)));
        result.put("jason", List.of(new Card(CardNumber.TWO, CardPattern.SPADE)));

        blackjackGame.distributePlayers();

        Assertions.assertThat(players.getInfo()).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    @DisplayName("플레이어의 최종 승패 결과를 가져온다.")
    void calculatePlayerWinOrLoseTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        blackjackGame.distributeInitialCard();

        Map<String, Result> playerResult = new LinkedHashMap<>();
        playerResult.put("pobi", Result.LOSE);
        playerResult.put("jason", Result.LOSE);

        Assertions.assertThat(blackjackGame.getPlayersResult()).isEqualTo(playerResult);
    }

    @Test
    @DisplayName("딜러의 최종 승패 결과를 가져온다.")
    void calculateDealerWinOrLoseTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        blackjackGame.distributeInitialCard();

        Map<String, List<Result>> playerResult = new LinkedHashMap<>();

        playerResult.put(dealer.getName(), List.of(Result.LOSE, Result.LOSE));

        Assertions.assertThat(blackjackGame.getDealerResult()).isEqualTo(playerResult);
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
