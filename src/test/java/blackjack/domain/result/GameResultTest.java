package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.Names;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private static GameResult gameResult;
    private static Gamblers gamblers;

    @BeforeAll
    static void resetVariable() {
        Map<Name, BettingMoney> playerInfo = new LinkedHashMap<>();
        for (Name name : Names.of("jamie1,jamie2").getNames()) {
            playerInfo.put(name, BettingMoney.of("500"));
        }
        gamblers = new Gamblers(playerInfo);
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        dealer.drawCard(new CardDeck(), 2);
        for (Gambler gambler : gamblers.getGamblers()) {
            gambler.drawCard(cardDeck, 2);
        }
        gameResult = new GameResult(dealer, gamblers);
    }

    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void validNotNull() {
        assertThatThrownBy(() -> new GameResult(new Dealer(), null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");

        assertThatThrownBy(() -> new GameResult(null, gamblers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");

        assertThatThrownBy(() -> new GameResult(null, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");

    }
}
