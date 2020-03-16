package domain;

import domain.card.Cards;
import domain.player.Dealer;
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
        Cards cards = new Cards();
        Dealer dealer = new Dealer(cards.giveCard(), cards.giveCard());
        Players players = new Players(cards,playerName);

        WinningResult winningResult = new WinningResult(players,dealer);

        Assertions.assertThat(winningResult.generateWinningUserResult(players)).size().isEqualTo(3);
    }
}
