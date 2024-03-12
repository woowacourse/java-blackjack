package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingsTest {

    @DisplayName("베팅 정보를 등록할 수 있다.")
    @Test
    void placeBet() {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("pobi");
        Money bettingMoney = new Money(1000);

        // when
        bettings.placeBet(player, bettingMoney);

        // then
        assertThat(bettings.getBettingMoney(player)).isEqualTo(bettingMoney);
    }

    @DisplayName("베팅 정보가 없는 경우 예외를 발생한다.")
    @Test
    void getBettingMoney() {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("pobi");

        // when & then
        assertThatCode(() -> bettings.getBettingMoney(player))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("베팅 금액이 범위를 벗어나면 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 10001})
    void validateBettingMoneyRange(int money) {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("pobi");
        Money bettingMoney = new Money(money);

        // when & then
        assertThatCode(() -> bettings.placeBet(player, bettingMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액은 1000원 단위여야 한다.")
    @Test
    void validateBettingMoneyUnit() {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("pobi");
        Money bettingMoney = new Money(1500);

        // when & then
        assertThatCode(() -> bettings.placeBet(player, bettingMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어들의 수익을 계산할 수 있다.")
    @Test
    void calculatePlayerProfits() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("pobi");
        Player player2 = new Player("crong");

        Bettings bettings = new Bettings();
        bettings.placeBet(player1, new Money(1000));
        bettings.placeBet(player2, new Money(3000));

        dealer.hit(Card.of(CardRank.SEVEN, CardShape.CLOVER));
        dealer.hit(Card.of(CardRank.TEN, CardShape.HEART));

        player1.hit(Card.of(CardRank.EIGHT, CardShape.SPADE));
        player1.hit(Card.of(CardRank.TEN, CardShape.DIAMOND));

        player2.hit(Card.of(CardRank.ACE, CardShape.SPADE));
        player2.hit(Card.of(CardRank.JACK, CardShape.DIAMOND));

        // when
        Map<Player, Integer> profits = bettings.calculatePlayerProfits(dealer);

        // then
        assertThat(profits).containsEntry(player1, 1000)
                .containsEntry(player2, 4500);
    }

    @DisplayName("딜러의 수익을 계산할 수 있다.")
    @Test
    void calculateDealerProfit() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("pobi");
        Player player2 = new Player("crong");

        Bettings bettings = new Bettings();
        bettings.placeBet(player1, new Money(2000));
        bettings.placeBet(player2, new Money(3000));

        dealer.hit(Card.of(CardRank.QUEEN, CardShape.CLOVER));
        dealer.hit(Card.of(CardRank.JACK, CardShape.HEART));

        player1.hit(Card.of(CardRank.EIGHT, CardShape.SPADE));
        player1.hit(Card.of(CardRank.TEN, CardShape.DIAMOND));

        player2.hit(Card.of(CardRank.QUEEN, CardShape.SPADE));
        player2.hit(Card.of(CardRank.JACK, CardShape.DIAMOND));

        // when
        Map<Player, Integer> profits = bettings.calculatePlayerProfits(dealer);
        int dealerProfit = bettings.calculateDealerProfit(profits);

        // then
        assertThat(dealerProfit).isEqualTo(2000);
    }
}
