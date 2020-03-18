package domain;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.PlayerInputInformation;
import domain.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningResultTest {

    @DisplayName("객체 생성 테스트")
    @Test
    void getterTest() {
        List<String> playerName = new ArrayList<>(Arrays.asList("pobi", "subway"));
        PlayerInputInformation playerInformation = new PlayerInputInformation(playerName,Arrays.asList(1000d,2000d));
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.giveTwoCardStartGame());
        Players players = new Players(cardDeck,playerInformation.getPlayerInformation());

        WinningResult winningResult = new WinningResult(players,dealer);

        Assertions.assertThat(winningResult.getWinningUserResult()).size().isEqualTo(3);
    }
}
