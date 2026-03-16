package domain.match;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.money.Bet;
import domain.money.Money;
import domain.participant.*;
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
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(
                new Player(new Name("pobi"), new Bet(10000)),
                new Player(new Name("james"), new Bet(10000))));

        GameResult gameResult = GameResult.of(players, dealer);

        // when
        Map<Player, Money> result = gameResult.calculatePlayersProfit();

        // then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("플레이어 수익의 총합에 따라 딜러의 수익을 계산한다.")
    void calculateDealerProfit_dealerLoseTest() {
        // given
        Player winWithBlackJack = new Player(new Name("pobi"), new Bet(10000));
        Player win = new Player(new Name("james"), new Bet(10000));
        Player draw = new Player(new Name("lala"), new Bet(10000));
        Player lose = new Player(new Name("jeje"), new Bet(10000));

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
        GameResult gameResult = GameResult.of(players, dealer);

        // then
        Assertions.assertEquals(gameResult.calculateDealerProfit(), Money.of(-15000));
    }
}
