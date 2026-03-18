package team.blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.Card;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Player;
import team.blackjack.domain.Result;

class BlackjackJudgeTest {

    private final BlackjackJudge blackjackJudge = new BlackjackJudge();

    @Test
    void 플레이어_버스트_시_패() {
        Player player = participantWithCards(Card.KING_OF_HEARTS, Card.SEVEN_OF_HEARTS, Card.FIVE_OF_HEARTS);
        Dealer dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.TEN_OF_CLUBS);

        assertThat(blackjackJudge.judge(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    void 딜러_버스트_시_플레이어_승() {
        Player player = participantWithCards(Card.KING_OF_HEARTS, Card.TEN_OF_HEARTS);
        Dealer dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.SEVEN_OF_CLUBS, Card.SIX_OF_CLUBS);

        assertThat(blackjackJudge.judge(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어_점수_높으면_승() {
        Player player = participantWithCards(Card.KING_OF_HEARTS, Card.QUEEN_OF_HEARTS);
        Dealer dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.EIGHT_OF_CLUBS);

        assertThat(blackjackJudge.judge(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어_점수_낮으면_패() {
        Player player = participantWithCards(Card.KING_OF_HEARTS, Card.EIGHT_OF_HEARTS);
        Dealer dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.QUEEN_OF_CLUBS);

        assertThat(blackjackJudge.judge(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    void 동점이면_무() {
        Player player = participantWithCards(Card.KING_OF_HEARTS, Card.SEVEN_OF_HEARTS);
        Dealer dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.SEVEN_OF_CLUBS);

        assertThat(blackjackJudge.judge(player, dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    void 플레이어_블랙잭_딜러_아니면_블랙잭_승() {
        Player player = participantWithCards(Card.ACE_OF_HEARTS, Card.KING_OF_HEARTS);
        Dealer dealer = dealerWithCards(Card.KING_OF_CLUBS, Card.NINE_OF_CLUBS);

        assertThat(blackjackJudge.judge(player, dealer)).isEqualTo(Result.BLACKJACK);
    }

    @Test
    void 승_시_배팅금_그대로_수익() {
        Player player = playerWithBatMoney("pobi", 1000);

        double revenue = blackjackJudge.calculatePlayerRevenue(player, Result.WIN);

        assertThat(revenue).isEqualTo(1000.0);
    }

    @Test
    void 패_시_배팅금_음수_수익() {
        Player player = playerWithBatMoney("pobi", 1000);

        double revenue = blackjackJudge.calculatePlayerRevenue(player, Result.LOSE);

        assertThat(revenue).isEqualTo(-1000.0);
    }

    @Test
    void 무_시_수익_0() {
        Player player = playerWithBatMoney("pobi", 1000);

        double revenue = blackjackJudge.calculatePlayerRevenue(player, Result.DRAW);

        assertThat(revenue).isEqualTo(0.0);
    }

    @Test
    void 블랙잭_승_시_1점5배_수익() {
        Player player = playerWithBatMoney("pobi", 1000);

        double revenue = blackjackJudge.calculatePlayerRevenue(player, Result.BLACKJACK);

        assertThat(revenue).isEqualTo(1500.0);
    }

    @Test
    void 플레이어_한명_승_시_딜러_수익은_플레이어_수익의_음수() {
        Player pobi = playerWithBatMoney("pobi", 1000);
        Map<Player, Result> judgeResults = Map.of(pobi, Result.WIN);

        double dealerRevenue = blackjackJudge.calculateDealerRevenue(judgeResults);

        assertThat(dealerRevenue).isEqualTo(-1000.0);
    }

    @Test
    void 플레이어_여러명_딜러_수익은_플레이어_수익_합의_음수() {
        Player pobi = playerWithBatMoney("pobi", 1000);
        Player jason = playerWithBatMoney("jason", 500);
        Map<Player, Result> judgeResults = Map.of(
                pobi, Result.WIN,
                jason, Result.LOSE
        );

        double dealerRevenue = blackjackJudge.calculateDealerRevenue(judgeResults);

        assertThat(dealerRevenue).isEqualTo(-500.0);
    }

    private static Player participantWithCards(Card... cards) {
        Player player = new Player("test");
        for (Card card : cards) {
            player.hit(card);
        }
        return player;
    }

    private static Dealer dealerWithCards(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.hit(card);
        }
        return dealer;
    }

    private static Player playerWithBatMoney(String name, double batMoney) {
        Player player = new Player(name);
        player.bat(batMoney);
        return player;
    }
}
