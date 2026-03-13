package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.match.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class GameResultTest {

    @Test
    @DisplayName("플레이어의 승패 결과에 따라 수익을 계산한다.")
    void calculatePlayersProfitTest() {
        // given
        Player pobi = new Player("pobi");
        Player james = new Player("james");

        pobi.placeBet(10000);
        james.placeBet(10000);

        Dealer dealer = new Dealer();
        Players players = new Players(List.of(pobi, james));

        GameResult gameResult = new GameResult(players, dealer);

        // when
        Map<Player, Integer> result = gameResult.calculatePlayersProfit();

        // then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("플레이어 수익의 총합에 따라 딜러의 수익을 계산한다.")
    void calculateDealerProfit_dealerLoseTest() {
        // given
        Player winWithBlackJack = new Player("pobi");
        Player win = new Player("james");
        Player draw = new Player("lala");
        Player lose = new Player("jeje");

        winWithBlackJack.placeBet(10000);
        win.placeBet(10000);
        draw.placeBet(10000);
        lose.placeBet(10000);

        winWithBlackJack.receive(new Card(Rank.ACE, Suit.DIAMOND));
        winWithBlackJack.receive(new Card(Rank.JACK, Suit.HEART));

        win.receive(new Card(Rank.EIGHT, Suit.DIAMOND));
        win.receive(new Card(Rank.NINE, Suit.CLOVER));
        win.receive(new Card(Rank.FOUR, Suit.HEART));

        draw.receive(new Card(Rank.FOUR, Suit.CLOVER));
        draw.receive(new Card(Rank.NINE, Suit.DIAMOND));

        lose.receive(new Card(Rank.TWO, Suit.CLOVER));
        lose.receive(new Card(Rank.THREE, Suit.DIAMOND));

        Dealer dealer = new Dealer();

        dealer.receive(new Card(Rank.FOUR, Suit.SPADE));
        dealer.receive(new Card(Rank.NINE, Suit.SPADE));

        Players players = new Players(List.of(winWithBlackJack, win, draw, lose));

        // when
        GameResult gameResult = new GameResult(players, dealer);

        // then
        Assertions.assertEquals(gameResult.calculateDealerProfit(), -15000);
    }
}
