package domain;

import domain.deck.Card;
import domain.deck.CardDeck;
import domain.deck.CardNumber;
import domain.deck.CardPattern;
import domain.generator.CardGenerator;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;
import domain.strategy.NoShuffleCardsStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BlackjackGameTest {
    private Dealer dealer;
    private Players players;
    private CardDeck cardDeck;

    @BeforeEach
    void set() {
        players = new Players(List.of("pobi","jason"));
        dealer = players.findDealer();
        CardGenerator cardGenerator = new CardGenerator();
        cardDeck = new CardDeck(cardGenerator.generate(new NoShuffleCardsStrategy()));
    }

    @Test
    @DisplayName("딜러에게 카드를 1장 나눠준다.")
    void distributeDealerInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(players, cardDeck);

        Dealer dealer = players.findDealer();
        List<Card> playerCard = new ArrayList<>();
        playerCard.add(new Card(CardNumber.ACE, CardPattern.SPADE));

        blackjackGame.distributeDealer();

        Assertions.assertThat(dealer.getPlayerCards()).usingRecursiveComparison().isEqualTo(playerCard);
    }

    @Test
    @DisplayName("플레이어에게 카드를 1장 나눠준다.")
    void distributePlayersInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(players, cardDeck);

        Player playerPobi = players.getPlayersWithOutDealer().get(0);
        List<Card> playerCard = new ArrayList<>();
        playerCard.add(new Card(CardNumber.ACE, CardPattern.SPADE));

        blackjackGame.distributePlayers();

        Assertions.assertThat(playerPobi.getPlayerCards()).usingRecursiveComparison().isEqualTo(playerCard);
    }

    @Test
    @DisplayName("플레이어의 최종 승패 결과를 가져온다.")
    void calculatePlayerWinOrLoseTest() {
        BlackjackGame blackjackGame = new BlackjackGame(players, cardDeck);

        blackjackGame.distributeInitialCard();

        Player pobi = players.getPlayersWithOutDealer().get(0);

        Assertions.assertThat(blackjackGame.getPlayersResult(pobi)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러의 최종 승패 결과를 가져온다.")
    void calculateDealerWinOrLoseTest() {
        BlackjackGame blackjackGame = new BlackjackGame(players, cardDeck);

        blackjackGame.distributeInitialCard();

        Dealer dealer = players.findDealer();

        Assertions.assertThat(blackjackGame.getDealerResult(players)).isEqualTo(List.of(Result.WIN,Result.WIN));
    }


    @Test
    @DisplayName("플레이어와 딜러의 승패 계산")
    void calculateWinOrLoseTest() {
        BlackjackGame blackjackGame = new BlackjackGame(players, cardDeck);

        Assertions.assertThat(blackjackGame.isPlayerWin(21, 10)).isEqualTo(Result.LOSE);
        Assertions.assertThat(blackjackGame.isPlayerWin(10, 21)).isEqualTo(Result.WIN);
        Assertions.assertThat(blackjackGame.isPlayerWin(25, 22)).isEqualTo(Result.LOSE);
        Assertions.assertThat(blackjackGame.isPlayerWin(21, 21)).isEqualTo(Result.DRAW);
    }
}
