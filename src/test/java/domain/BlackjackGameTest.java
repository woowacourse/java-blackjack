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
    private CardDeck cardDeck;

    @BeforeEach
    void set() {
        dealer = new Dealer();
        players = new Players("pobi,jason");
        CardGenerator cardGenerator = new CardGenerator();
        cardDeck = new CardDeck(cardGenerator.generate());
    }

    @Test
    @DisplayName("딜러에게 카드를 1장을 나눠준다.")
    void distributeDealerInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        blackjackGame.distributeDealer();

        Map<String, List<Card>> result = new LinkedHashMap<>();
        result.put("딜러", List.of(new Card(CardNumber.ACE, CardPattern.SPADE)));
        Assertions.assertThat(dealer.getInfo()).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    @DisplayName("플레이어들에게 카드를 1장을 나눠준다.")
    void distributePlayersInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);

        blackjackGame.distributePlayers();

        Map<String, List<Card>> result = new LinkedHashMap<>();
        result.put("pobi", List.of(new Card(CardNumber.ACE, CardPattern.SPADE)));
        result.put("jason", List.of(new Card(CardNumber.TWO, CardPattern.SPADE)));
        Assertions.assertThat(players.getInfo()).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    @DisplayName("플레이어가 추가 카드를 받는다.")
    void distributeByYesCommandTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);
        Player player = new Player("pobi");

        blackjackGame.selectByPlayer(player, Command.YES);

        Assertions.assertThat(player.getCardsSum()).isEqualTo(11);
    }

    @Test
    @DisplayName("플레이어가 추가 카드를 받지 않는다.")
    void distributeByNoCommandTest() {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players, cardDeck);
        Player player = new Player("pobi");

        blackjackGame.selectByPlayer(player, Command.No);

        Assertions.assertThat(player.getCardsSum()).isEqualTo(0);
    }

}
