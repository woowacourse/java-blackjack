package domain;

import domain.card.Cards;
import domain.game.ProfitCalculator;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static domain.game.BlackjackRule.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {
    Player pobi;
    Players players;
    Dealer dealer;
    ProfitCalculator profitCalculator;

    @BeforeEach
    void setUp() {
        pobi = new Player("pobi");
        players = new Players(List.of(pobi));

        pobi.bet(BigDecimal.valueOf(1000));

        dealer = new Dealer(DEALER_NAME);

        profitCalculator = new ProfitCalculator();
    }

    @Test
    void 플레이어가_처음_받은_카드_두_장의_합이_21이고_딜러는_아닐_경우_플레이어의_수익은_베팅한_금액의_1_5배이다() {
        Cards playerCards = CardFixture.blackjackCards();
        pobi.receiveInitialCards(playerCards);

        Cards dealerCards = CardFixture.seventeenCards();
        dealer.receiveInitialCards(dealerCards);

        assertThat(profitCalculator.calculatePlayerProfit(pobi, dealer)).isEqualByComparingTo(BigDecimal.valueOf(1500));
    }

    @Test
    void 플레이어가_승리할_경우_플레이어의_수익은_자신이_베팅한_금액이다() {
        Cards playerCards = CardFixture.twentyCards();
        pobi.receiveInitialCards(playerCards);

        Cards dealerCards = CardFixture.seventeenCards();
        dealer.receiveInitialCards(dealerCards);

        assertThat(profitCalculator.calculatePlayerProfit(pobi, dealer)).isEqualByComparingTo(BigDecimal.valueOf(1000));
    }

    @Test
    void 딜러가_승리할_경우_플레이어는_자신이_베팅한_금액만큼_돈을_잃는다() {
        Cards playerCards = CardFixture.seventeenCards();
        pobi.receiveInitialCards(playerCards);

        Cards dealerCards = CardFixture.twentyCards();
        dealer.receiveInitialCards(dealerCards);

        assertThat(profitCalculator.calculatePlayerProfit(pobi, dealer)).isEqualByComparingTo(BigDecimal.valueOf(-1000));
    }

    @Test
    void 무승부일_경우_플레이어의_수익은_0원이다() {
        Cards playerCards = CardFixture.seventeenCards();
        pobi.receiveInitialCards(playerCards);

        Cards dealerCards = CardFixture.seventeenCards();
        dealer.receiveInitialCards(dealerCards);

        assertThat(profitCalculator.calculatePlayerProfit(pobi, dealer)).isEqualByComparingTo(BigDecimal.valueOf(0));
    }
}
