package domain;

import domain.card.Card;
import domain.member.Dealer;
import domain.member.Members;
import domain.member.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("딜러이면 true를 반환한다.")
    @Test
    void roleTest_isDealer_returnTrue() {
        Members members = new Members();
        Assertions.assertTrue(members.isDealer("딜러"));
    }

    @DisplayName("플레이어가 이기면 RoundResult를 반환한다.")
    @Test
    void winnerTest_isPlayerThenDecide_returnMember() {
        Dealer dealer = new Dealer("브라운");
        Player player = new Player("브리");

        dealer.receiveCard(new Card("2", "하트"));
        dealer.receiveCard(new Card("3","클로버"));
        player.receiveCard(new Card("4","다이아몬드"));
        player.receiveCard(new Card("2", "스페이드"));

        Assertions.assertEquals(player.judgeAgainst(dealer), RoundResult.WIN);
    }
}
