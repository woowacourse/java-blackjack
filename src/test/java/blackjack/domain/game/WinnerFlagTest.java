package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WinnerFlagTest {
    @Test
    @DisplayName("결과 매칭 테스트")
    void matchResult() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.NINE));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));
        WinnerFlag.calculateResult(dealer, player);
        assertEquals(player.getResult(), WinnerFlag.LOSE);
    }
}