package blackjack.domain;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Pattern.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public class ProfitResultTest {

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 1.5배의 수익 결과를 얻는다.")
    void profitPlayerWin() {
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
        ProfitResult profitResult = new ProfitResult(players, dealer);
        ParticipantProfit playerResult = profitResult.getPlayersResult().get(0);
        ParticipantProfit dealerResult = profitResult.getDealerResult();

        // then
        assertAll(
            () -> assertThat(playerResult.getProfit()).isEqualTo((int)(amount * 1.5)),
            () -> assertThat(dealerResult.getProfit()).isEqualTo((int)(amount * -1.5))
        );
    }

    @Test
    @DisplayName("비기는 경우 배팅 금액을 그대로 돌려받는다.")
    void profitPlayerDraw() {
        // given
        Card card1 = new Card(CLOVER, NINE);
        Card card2 = new Card(SPADE, NINE);
        Cards dealerCards = new Cards(List.of(card1, card2));
        Dealer dealer = new Dealer(dealerCards);
        dealer.hit(new Card(HEART, SIX));

        Card card3 = new Card(HEART, TEN);
        Card card4 = new Card(SPADE, EIGHT);
        Cards playerCards = new Cards(List.of(card3, card4));
        int amount = 1000;
        BetMoney betMoney = new BetMoney(amount);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);
        List<Player> players = List.of(player);

        // when
        ProfitResult profitResult = new ProfitResult(players, dealer);
        ParticipantProfit playerResult = profitResult.getPlayersResult().get(0);
        ParticipantProfit dealerResult = profitResult.getDealerResult();

        // then
        assertAll(
            () -> assertThat(playerResult.getProfit()).isEqualTo(amount),
            () -> assertThat(dealerResult.getProfit()).isEqualTo(amount * -1)
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
        ProfitResult profitResult = new ProfitResult(players, dealer);
        ParticipantProfit playerResult = profitResult.getPlayersResult().get(0);
        ParticipantProfit dealerResult = profitResult.getDealerResult();

        // then
        assertAll(
            () -> assertThat(playerResult.getProfit()).isEqualTo(amount * -1),
            () -> assertThat(dealerResult.getProfit()).isEqualTo(amount)
        );
    }
}
