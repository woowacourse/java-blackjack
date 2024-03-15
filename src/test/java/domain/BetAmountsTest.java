package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import exception.BetAmountRangeException;
import exception.BetAmountUnitException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountsTest {
    static Player pobi, jason, neo, solar;
    static BetAmounts betAmounts;

    @BeforeAll
    static void init() {
        Name name1 = new Name("pobi");
        Name name2 = new Name("jason");
        Name name3 = new Name("neo");
        Name name4 = new Name("solar");

        pobi = new Player(name1);
        jason = new Player(name2);
        neo = new Player(name3);
        solar = new Player(name4);

        betAmounts = new BetAmounts();
    }

    @DisplayName("배팅 금액 범위 테스트")
    @Nested
    class BetAmountRangeTest {

        @DisplayName("배팅은 금액이 1,000원 미만이거나 1,000,000원을 초과할 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {900, 9999900})
        void createFailWithAmountRange(int amount) {

            assertThatThrownBy(() -> betAmounts.addBetAmount(pobi, amount))
                    .isInstanceOf(BetAmountRangeException.class)
                    .hasMessage(BetAmountRangeException.INVALID_AMOUNT_RANGE);
        }

        @DisplayName("배팅은 금액이 1,000원 이상이거나 1,000,000원 이하일 경우 정상적으로 생성된다.")
        @ParameterizedTest
        @ValueSource(ints = {1000, 1000000})
        void createSuccessWithAmountRange(int amount) {

            assertThatCode(() -> betAmounts.addBetAmount(pobi, amount))
                    .doesNotThrowAnyException();
        }
    }

    @DisplayName("배팅 금액 단위 테스트")
    @Nested
    class BetAmountUnitTest {

        @DisplayName("배팅은 금액의 단위가 100단위가 아닐 경우 예외가 발생한다.")
        @Test
        void createFailWithAmountUnit() {

            assertThatThrownBy(() -> betAmounts.addBetAmount(pobi, 1010))
                    .isInstanceOf(BetAmountUnitException.class)
                    .hasMessage(BetAmountUnitException.INVALID_BET_AMOUNT_UNIT);
        }

        @DisplayName("배팅은 금액이 100으로 나뉘어 진다면 정상적으로 생성된다.")
        @Test
        void createSuccessWithAmountUnit() {

            assertThatCode(() -> betAmounts.addBetAmount(pobi, 1100))
                    .doesNotThrowAnyException();
        }
    }

    @DisplayName("배팅 결과 수익을 구한다.")
    @Nested
    class BetProfitTest {
        static Dealer dealer;
        @BeforeAll
        static void set() {
            dealer = new Dealer();

            betAmounts.addBetAmount(pobi, 10000);
            betAmounts.addBetAmount(jason, 10000);
            betAmounts.addBetAmount(neo, 10000);
            betAmounts.addBetAmount(solar, 10000);

            dealer.hit(new Card(Symbol.HEART, Rank.KING));

            pobi.hit(new Card(Symbol.HEART, Rank.ACE));
            jason.hit(new Card(Symbol.HEART, Rank.TWO));
            neo.hit(new Card(Symbol.HEART, Rank.KING));
            solar.hit(new Card(Symbol.HEART, Rank.ACE));
            solar.hit(new Card(Symbol.HEART, Rank.KING));
        }

        @DisplayName("플레이어의 결과가 승이면 1배, 지면 -1배, 무승부면 0배, 블랙잭이면 1.5배의 수익을 얻는다.")
        @Test
        void calculatePlayerBetProfitTest() {
            // when
            int pobiProfit = betAmounts.calculatePlayerBetProfit(pobi, dealer);
            int jasonProfit = betAmounts.calculatePlayerBetProfit(jason, dealer);
            int neoProfit = betAmounts.calculatePlayerBetProfit(neo, dealer);
            int solarProfit = betAmounts.calculatePlayerBetProfit(solar, dealer);

            // then
            assertAll(
                    () -> assertThat(pobiProfit).isEqualTo(10000),
                    () -> assertThat(jasonProfit).isEqualTo(-10000),
                    () -> assertThat(neoProfit).isEqualTo(0),
                    () -> assertThat(solarProfit).isEqualTo(15000)
            );
        }

        @DisplayName("딜러의 수익을 구한다")
        @Test
        void calculateDealerBetProfitTest() {
            // when
            int dealerProfitOfPobi = betAmounts.calculateDealerBetProfit(pobi, dealer);
            int dealerProfitOfJason = betAmounts.calculateDealerBetProfit(jason, dealer);
            int dealerProfitOfNeo = betAmounts.calculateDealerBetProfit(neo, dealer);
            int dealerProfitOfSolaer = betAmounts.calculateDealerBetProfit(solar, dealer);

            // then
            assertAll(
                    () -> assertThat(dealerProfitOfPobi).isEqualTo(-10000),
                    () -> assertThat(dealerProfitOfJason).isEqualTo(10000),
                    () -> assertThat(dealerProfitOfNeo).isEqualTo(0),
                    () -> assertThat(dealerProfitOfSolaer).isEqualTo(-15000)
            );
        }
    }
}
