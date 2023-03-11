package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BlackjackGameTest {
    private Dealer dealer;
    private Players players;
    private CardDeck cardDeck;
    private Queue<Card> initalCardDeck = new LinkedList<>();

    @BeforeEach
    void set() {
        dealer = new Dealer();
        players = new Players("pobi,jason");
        for (CardNumber cardNumber : CardNumber.values()) {
            initalCardDeck.add(new Card(cardNumber, CardPattern.SPADE));
        }
        cardDeck = new CardDeck(initalCardDeck);
    }

    @Test
    @DisplayName("딜러에게 카드를 1장을 나눠준다.")
    void distributeDealerInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        blackjackGame.distributeDealer();

        Card card = new Card(CardNumber.ACE, CardPattern.SPADE);
        Assertions.assertThat(dealer.getCards().get(0)).usingRecursiveComparison().isEqualTo(card);
    }

    @Test
    @DisplayName("플레이어들에게 카드를 1장을 나눠준다.")
    void distributePlayersInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        blackjackGame.distributePlayers();

        Map<String, List<Card>> result = new LinkedHashMap<>();
        result.put("pobi", List.of(new Card(CardNumber.ACE, CardPattern.SPADE)));
        result.put("jason", List.of(new Card(CardNumber.TWO, CardPattern.SPADE)));
        Assertions.assertThat(players.getInformation()).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    @DisplayName("플레이어의 최종 승패 결과를 가져온다.")
    void calculatePlayerWinOrLoseTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);
        blackjackGame.distributeInitialCard();

        Map<String, ResultType> playerResult = new LinkedHashMap<>();
        playerResult.put("pobi", ResultType.LOSE);
        playerResult.put("jason", ResultType.LOSE);
        Assertions.assertThat(blackjackGame.getGameResult().getPlayerResult()).isEqualTo(playerResult);
    }

}
