package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.Names;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.gambler.Players;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private static GameResult gameResult;

    @BeforeAll
    static void resetVariable() {
        Map<Name, Money> playerInfo = new LinkedHashMap<>();
        for (Name name : new Names("jamie1,jamie2").getNames()) {
            playerInfo.put(name, Money.fromPositive("500"));
        }
        Players players = new Players(playerInfo);
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        dealer.drawCard(new CardDeck(), 2);
        for (Player player : players.getPlayers()) {
            player.drawCard(cardDeck, 2);
        }
        gameResult = new GameResult(dealer, players);
    }

    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void validNotNull() {
        assertThatThrownBy(() -> new GameResult(new Dealer(), null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
    }
}
