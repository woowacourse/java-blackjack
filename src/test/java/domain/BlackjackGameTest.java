package domain;

import domain.card.Card;
import domain.member.BettingAmount;
import domain.member.Member;
import domain.member.Members;
import domain.member.Name;
import domain.member.PlayerRole;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private final BlackjackGame game;
    private final Member pobi;
    private final Member lisa;
    private final Members members;

    public BlackjackGameTest() {
        this.pobi = new Member(new Name("포비"), new PlayerRole(new BettingAmount(1000)));
        this.lisa = new Member(new Name("리사"), new PlayerRole(new BettingAmount(1000)));
        this.members = new Members(List.of(pobi, lisa));
        this.game = new BlackjackGame(members);
    }

    @DisplayName("딜러와 플레이어가 둘 다 블랙잭이면 둘 다 수익이 0이다.")
    @Test
    void blackjackTest_dealerAndPlayerBlackjack_return0() {
        Member dealer = game.getDealer();
        members.draw(dealer, new Card("10", "하트"));
        members.draw(dealer, new Card("A", "스페이드"));

        members.draw(pobi, new Card("10","클로버"));
        members.draw(pobi, new Card("A", "하트"));

        pobi.applyBlackjackBonus();
        Map<Member, Integer> playerProfits = game.getPlayerProfits();
        int playerAmount = playerProfits.get(pobi);

        Assertions.assertEquals(0, playerAmount);
    }

    @DisplayName("플레이어가 블랙잭이고, 딜러는 블랙잭이 아니면 플레이어의 수익은 1.5배다.")
    @Test
    void blackjackTest_playerIsBlackjack_1_5() {
        Member dealer = game.getDealer();
        members.draw(dealer, new Card("10", "하트"));
        members.draw(dealer, new Card("8", "스페이드"));

        members.draw(pobi, new Card("10","클로버"));
        members.draw(pobi, new Card("A", "하트"));

        pobi.applyBlackjackBonus();
        Map<Member, Integer> playerProfits = game.getPlayerProfits();
        int playerAmount = playerProfits.get(pobi);

        Assertions.assertEquals(1500, playerAmount);
    }

    @DisplayName("딜러가 블랙잭이고, 포비는 19, 리사는 21이면 포비는 딜러의 수익은 1000이다.")
    @Test
    void blackjackTest_dealerIsBlackjackAndPobi19Lisa21_return1000() {
        Member dealer = game.getDealer();
        members.draw(dealer, new Card("10","클로버"));
        members.draw(dealer, new Card("A", "하트"));

        members.draw(pobi, new Card("10","스페이드"));
        members.draw(pobi, new Card("9", "하트"));

        members.draw(lisa, new Card("10","하트"));
        members.draw(lisa, new Card("A", "클로버"));

        lisa.applyBlackjackBonus();
        int dealerProfit = game.getDealerProfit();
        Map<Member, Integer> playerProfits = game.getPlayerProfits();

        Assertions.assertEquals(-1000, playerProfits.get(pobi));
        Assertions.assertEquals(0, playerProfits.get(lisa));
        Assertions.assertEquals(1000, dealerProfit);
    }
}