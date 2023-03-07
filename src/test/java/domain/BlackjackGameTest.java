package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

}
