package domain;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.PlayerInputInformation;
import domain.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ProfitRuleResultTest {

    @DisplayName("객체 생성 테스트")
    @Test
    void getterTest() {
        Map<String,Double> playerName = new LinkedHashMap<>();
        playerName.put("pobi",1000d);
        playerName.put("subway",2000d);
        PlayerInputInformation playerInformation = new PlayerInputInformation(playerName);

        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.giveTwoCardStartGame());
        Players players = new Players(cardDeck,playerInformation.getPlayerInformation());

        ProfitResult profitResult = new ProfitResult(players,dealer);

        Assertions.assertThat(profitResult.getWinningUserResult()).size().isEqualTo(3);
    }
}
