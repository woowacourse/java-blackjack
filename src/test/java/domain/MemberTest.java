package domain;

import domain.vo.RoundResult;
import domain.card.Card;
import domain.member.Dealer;
import domain.member.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("플레이어가 이기면 RoundResult를 반환한다.")
    @Test
    void winnerTest_isPlayerThenDecide_returnMember() {
        Dealer dealer = new Dealer();
        Player player = new Player("브리");

        dealer.receiveCard(new Card("2", "하트"));
        dealer.receiveCard(new Card("3","클로버"));
        player.receiveCard(new Card("4","다이아몬드"));
        player.receiveCard(new Card("2", "스페이드"));

        Assertions.assertEquals(RoundResult.WIN, RoundResult.judgeAgainst(dealer.handValue(), player.handValue()));
    }
}
