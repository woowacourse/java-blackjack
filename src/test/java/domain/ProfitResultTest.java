package domain;

import domain.card.CardDeck;
import domain.gambler.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProfitResultTest {

    private static ProfitResult profitResult;

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
        profitResult = new ProfitResult(dealer, players);
    }

    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void validNotNull() {
        assertThatThrownBy(() -> new ProfitResult(new Dealer(), null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("null");
    }
}
