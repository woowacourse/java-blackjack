package domain;

import domain.member.BettingAmount;
import domain.member.Dealer;
import domain.member.Member;
import domain.member.Name;
import domain.member.Player;
import domain.vo.RoundResult;
import domain.card.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("플레이어가 이기면 RoundResult를 반환한다.")
    @Test
    void winnerTest_isPlayerThenDecide_returnMember() {
        Dealer dealer = new Dealer(new Member(new Name("딜러")));
        Player player = new Player(new Member(new Name("포비")), new BettingAmount(1000));

        dealer.draw(new Card("2", "하트"));
        dealer.draw(new Card("3","클로버"));
        player.draw(new Card("4","다이아몬드"));
        player.draw(new Card("2", "스페이드"));

        Assertions.assertEquals(RoundResult.WIN, RoundResult.judgeAgainst(dealer, player));
    }
}
