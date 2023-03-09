package domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Card;
import domain.model.Cards;
import domain.model.Dealer;
import domain.model.Player;
import domain.model.Profit;
import domain.type.Letter;
import domain.type.Suit;
import domain.vo.Batting;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {

    private static final double NUMBER_TO_MULTIPLE_WHEN_DEFEAT = -1D;
    private static final double NUMBER_TO_MULTIPLE_WHEN_BLACKJACK_WIN = 1.5D;
    private static final double NUMBER_TO_MULTIPLE_WHEN_NORMAL_WIN = 1D;
    private final ProfitCalculator profitCalculator = new ProfitCalculator();

    @Test
    @DisplayName("플레이어가 버스트일 경우 테스트")
    public void testJudgeWhenIsBust() {
        //given
        final double value = 100D;
        final Batting batting = Batting.of(value);
        final Cards bustedCards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN),
            new Card(Suit.SPADE, Letter.TWO)
        ));
        final Player player = new Player(bustedCards, "test");
        final Dealer dealer = new Dealer(Cards.makeEmpty());

        //when
        final Profit profit = profitCalculator.calculate(batting, player, dealer);

        //then
        assertThat(profit.getValue()).isEqualTo(NUMBER_TO_MULTIPLE_WHEN_DEFEAT * value);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 승리할 경우 테스트")
    public void testJudgeWhenIsBlackJack() {
        //given
        final double value = 100D;
        final Batting batting = Batting.of(value);
        final Cards blackJackCards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.ACE)
        ));
        final Player player = new Player(blackJackCards, "test");
        final Dealer dealer = new Dealer(Cards.makeEmpty());

        //when
        final Profit profit = profitCalculator.calculate(batting, player, dealer);

        //then
        assertThat(profit.getValue()).isEqualTo(NUMBER_TO_MULTIPLE_WHEN_BLACKJACK_WIN * value);
    }

    @Test
    @DisplayName("플레이어가 그냥 승리 했을 경우 테스트")
    public void testJudgeWhenIsNormalWin() {
        //given
        final double value = 100D;
        final Batting batting = Batting.of(value);
        final Cards playerCards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN)
        ));
        final Cards dealerCards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.NINE)
        ));
        final Player player = new Player(playerCards, "test");
        final Dealer dealer = new Dealer(dealerCards);

        //when
        final Profit profit = profitCalculator.calculate(batting, player, dealer);

        //then
        assertThat(profit.getValue()).isEqualTo(NUMBER_TO_MULTIPLE_WHEN_NORMAL_WIN * value);
    }

    @Test
    @DisplayName("플레이어가 그냥 패배 했을 경우 테스트")
    public void testJudgeWhenIsNormalDefeat() {
        //given
        final double value = 100D;
        final Batting batting = Batting.of(value);
        final Cards playerCards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.NINE)
        ));
        final Cards dealerCards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN)
        ));
        final Player player = new Player(playerCards, "test");
        final Dealer dealer = new Dealer(dealerCards);

        //when
        final Profit profit = profitCalculator.calculate(batting, player, dealer);

        //then
        assertThat(profit.getValue()).isEqualTo(NUMBER_TO_MULTIPLE_WHEN_DEFEAT * value);
    }
}
