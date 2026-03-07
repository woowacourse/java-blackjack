package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("딜러이면 true를 반환한다.")
    @Test
    void roleTest_isDealer_returnTrue() {
        Member member = new Member("포비", Role.DEALER);
        Assertions.assertTrue(member.isDealer());
    }

    @DisplayName("플레이어가 이기면 플레이어를 반환한다.")
    @Test
    void winnerTest_isPlayerThenDecide_returnMember() {
        Member dealer = new Member("브라운", Role.DEALER);
        Member player = new Member("브리", Role.PLAYER);

        dealer.receiveCard(new Card("2", "하트"));
        dealer.receiveCard(new Card("3","클로버"));
        player.receiveCard(new Card("4","다이아몬드"));
        player.receiveCard(new Card("2", "스페이드"));

        Assertions.assertEquals(player.isWinner(dealer), player);
    }
}
