package blackjack.domain;

import blackjack.view.OutputView;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {
    @Test
    @DisplayName("결과 출력 테스트")
    void scoreTest() {
        Players players = new Players("pobi,jason", new Dealer());
        players.giveCards(new Deck());
        OutputView.noticeMatchResult(players);
    }

    @Test
    @DisplayName("딜러가 승리하는 경우")
    void dealerWinTest() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        player.receiveCard(Card.valueOf(CardPattern.HEART, CardNumber.FIVE));
        dealer.receiveCard(Card.valueOf(CardPattern.SPADE, CardNumber.TEN));
        Players players = new Players(Collections.singletonList(player), dealer);
        OutputView.noticeMatchResult(players);
    }

    @Test
    @DisplayName("플레이어가 승리하는 경우")
    void playerWinTest() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        player.receiveCard(Card.valueOf(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(Card.valueOf(CardPattern.SPADE, CardNumber.FIVE));
        Players players = new Players(Collections.singletonList(player), dealer);
        OutputView.noticeMatchResult(players);
    }
}
