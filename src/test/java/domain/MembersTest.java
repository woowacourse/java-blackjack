package domain;

import domain.card.Card;
import domain.member.BettingAmount;
import domain.member.Member;
import domain.member.Members;

import domain.member.Name;
import domain.member.PlayerRole;
import domain.vo.RoundResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MembersTest {

    private final Members members;
    private final Member pobi;

    public MembersTest() {
        this.pobi = new Member(new Name("포비"), new PlayerRole(new BettingAmount(1000)));
        this.members = new Members(List.of(pobi));
    }

    @DisplayName("카드의 총합이 21보다 크면 CurrentResult의 isBust는 true이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf22_returnTrue() {
        members.draw(pobi, new Card("10","클로버"));
        members.draw(pobi, new Card("5", "하트"));
        members.draw(pobi, new Card("7", "하트"));

        Assertions.assertTrue(pobi.hasBust());
    }

    @DisplayName("카드의 총합이 21보다 작으면 CurrentResult의 isBust는 false이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf20_returnFalse() {
        members.draw(pobi, new Card("10","클로버"));
        members.draw(pobi, new Card("5", "하트"));
        members.draw(pobi, new Card("5", "스페이드"));

        Assertions.assertFalse(pobi.hasBust());
    }

    @DisplayName("딜러의 카드 총합이 21을 넘으면 버스트(true)이다.")
    @Test
    void dealerTest_dealerTotalValueUpperThan21_returnTrue() {
        Member dealer = members.getDealer();

        members.draw(dealer, new Card("10", "하트"));
        members.draw(dealer, new Card("10", "스페이드"));
        members.draw(dealer, new Card("5", "클로버"));

        Assertions.assertTrue(dealer.hasBust());
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이 아니고, 딜러가 18, 플레이어가 16이면 플레이어가 진다.")
    @Test
    void blackjackTest_playerAndDealerIsNotBlackjack_1() {
        Member dealer = members.getDealer();
        members.draw(dealer, new Card("10", "하트"));
        members.draw(dealer, new Card("8", "스페이드"));

        members.draw(pobi, new Card("10","클로버"));
        members.draw(pobi, new Card("6", "하트"));

        Map<Member, RoundResult> gameResult = members.judgeGameResults();
        RoundResult roundResult = gameResult.get(pobi);

        Assertions.assertEquals(RoundResult.LOSE, roundResult);
    }
}