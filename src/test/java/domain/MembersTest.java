package domain;

import domain.card.Card;
import domain.member.BettingAmount;
import domain.member.Dealer;
import domain.member.Member;
import domain.member.Player;
import domain.member.Players;

import domain.member.Name;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MembersTest {

    private final Players players;
    private final Dealer dealer;
    private final Player pobi;

    public MembersTest() {
        this.pobi = new Player(new Member(new Name("포비")), new BettingAmount(1000));
        this.players = new Players(List.of(pobi));
        this.dealer = new Dealer();
    }

    @DisplayName("카드의 총합이 21보다 크면 CurrentResult의 isBust는 true이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf22_returnTrue() {
        players.draw(pobi, new Card("10","클로버"));
        players.draw(pobi, new Card("5", "하트"));
        players.draw(pobi, new Card("7", "하트"));

        Assertions.assertTrue(pobi.hasBust());
    }

    @DisplayName("카드의 총합이 21보다 작으면 CurrentResult의 isBust는 false이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf20_returnFalse() {
        players.draw(pobi, new Card("10","클로버"));
        players.draw(pobi, new Card("5", "하트"));
        players.draw(pobi, new Card("5", "스페이드"));

        Assertions.assertFalse(pobi.hasBust());
    }

    @DisplayName("딜러의 카드 총합이 21을 넘으면 버스트(true)이다.")
    @Test
    void dealerTest_dealerTotalValueUpperThan21_returnTrue() {
        dealer.draw(new Card("10", "하트"));
        dealer.draw(new Card("10", "스페이드"));
        dealer.draw(new Card("5", "클로버"));

        Assertions.assertTrue(dealer.hasBust());
    }
}