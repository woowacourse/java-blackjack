package blackjack.domain;

import blackjack.controller.GameResultController;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {
    @Test
    @DisplayName("결과 출력 테스트")
    void scoreTest() {
        Players players = new Players("pobi,jason", new Dealer());
        players.giveCards(new Deck());
        GameResultController.getPlayersCardsAndResult(players);
    }

    @Test
    @DisplayName("최종 결과 출력")
    void resultTest() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.FIVE));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));
        Players players = new Players(Collections.singletonList(player), dealer);
        GameResultController.getResult(players);
    }
}
