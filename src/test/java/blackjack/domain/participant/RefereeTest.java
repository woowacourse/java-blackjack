package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Pattern.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class RefereeTest {

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 1.5배의 수익 결과를 얻는다.")
    void profitPlayerBlackjack() {
        // given
        Card cloverTen = new Card(CLOVER, TEN);
        Card spadeTen = new Card(SPADE, TEN);
        Cards dealerCards = new Cards(List.of(cloverTen, spadeTen));
        Dealer dealer = new Dealer(dealerCards);
        dealer.hit(new Card(HEART, SIX));

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, ACE);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        int amount = 1000;
        BetMoney betMoney = new BetMoney(amount);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);
        List<Player> players = List.of(player);

        // when
        Map<Player, Profit> playerProfits = Referee.calculatePlayersProfit(players, dealer);
        Profit dealerProfit = Referee.calculateDealerProfit(new ArrayList<>(playerProfits.values()));

        // then
        assertAll(
            () -> assertThat(playerProfits.get(player).getAmount()).isEqualTo((int)(amount * 1.5)),
            () -> assertThat(dealerProfit.getAmount()).isEqualTo((int)(amount * -1.5))
        );
    }

    @Test
    @DisplayName("비기는 경우 수익은 0원이다.")
    void profitPlayerDraw() {
        // given
        Card card1 = new Card(CLOVER, NINE);
        Card card2 = new Card(SPADE, NINE);
        Cards dealerCards = new Cards(List.of(card1, card2));
        Dealer dealer = new Dealer(dealerCards);

        Card card3 = new Card(HEART, TEN);
        Card card4 = new Card(SPADE, EIGHT);
        Cards playerCards = new Cards(List.of(card3, card4));
        int amount = 1000;
        BetMoney betMoney = new BetMoney(amount);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);
        List<Player> players = List.of(player);

        // when
        Map<Player, Profit> playerProfits = Referee.calculatePlayersProfit(players, dealer);
        Profit dealerProfit = Referee.calculateDealerProfit(new ArrayList<>(playerProfits.values()));

        // then
        assertAll(
            () -> assertThat(playerProfits.get(player).getAmount()).isEqualTo(0),
            () -> assertThat(dealerProfit.getAmount()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("이기는 경우 수익은 1배이다.")
    void profitPlayerWin() {
        // given
        Card card1 = new Card(CLOVER, EIGHT);
        Card card2 = new Card(SPADE, EIGHT);
        Cards dealerCards = new Cards(List.of(card1, card2));
        Dealer dealer = new Dealer(dealerCards);

        Card card3 = new Card(HEART, NINE);
        Card card4 = new Card(SPADE, NINE);
        Cards playerCards = new Cards(List.of(card3, card4));
        int amount = 1000;
        BetMoney betMoney = new BetMoney(amount);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);
        List<Player> players = List.of(player);

        // when
        Map<Player, Profit> playerProfits = Referee.calculatePlayersProfit(players, dealer);
        Profit dealerProfit = Referee.calculateDealerProfit(new ArrayList<>(playerProfits.values()));

        // then
        assertAll(
            () -> assertThat(playerProfits.get(player).getAmount()).isEqualTo(amount),
            () -> assertThat(dealerProfit.getAmount()).isEqualTo(amount * -1)
        );
    }

    @Test
    @DisplayName("지는 경우 배팅 금액을 잃는다.")
    void profitPlayerLose() {
        // given
        Card card1 = new Card(CLOVER, NINE);
        Card card2 = new Card(SPADE, NINE);
        Cards dealerCards = new Cards(List.of(card1, card2));
        Dealer dealer = new Dealer(dealerCards);

        Card card3 = new Card(HEART, TEN);
        Card card4 = new Card(SPADE, SEVEN);
        Cards playerCards = new Cards(List.of(card3, card4));
        int amount = 1000;
        BetMoney betMoney = new BetMoney(amount);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);
        List<Player> players = List.of(player);

        // when
        Map<Player, Profit> playerProfits = Referee.calculatePlayersProfit(players, dealer);
        Profit dealerProfit = Referee.calculateDealerProfit(new ArrayList<>(playerProfits.values()));

        // then
        assertAll(
            () -> assertThat(playerProfits.get(player).getAmount()).isEqualTo((amount * -1)),
            () -> assertThat(dealerProfit.getAmount()).isEqualTo((amount))
        );
    }
}
