package domain.blackjackgame;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.EIGHT;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.KING;
import static domain.card.Denomination.NINE;
import static domain.card.Denomination.QUEEN;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.THREE;
import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.participant.Dealer;
import domain.participant.Player;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProfitResultManagerTest {
    private final Dealer bustDealer = crateDealer(TEN, JACK, QUEEN);
    private final Dealer blackjackDealer = crateDealer(ACE, KING);
    private final Dealer stayDealer = crateDealer(NINE, TEN);

    Dealer crateDealer(Denomination... denominations) {
        Dealer dealer = new Dealer();
        for (Denomination denomination : denominations) {
            dealer.receiveAdditionalCard(카드(denomination));
        }
        return dealer;
    }

    @Nested
    class 딜러가_bust일_때 {
        @Test
        void 플레이어가_bust이면_배팅한_금액을_모두_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(bustDealer);
            Player player = new Player("prin", 1000);
            player.receiveInitialCards(카드(KING), 카드(KING));
            player.receiveAdditionalCard(카드(QUEEN));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.valueOf(-1000));
        }

        @Test
        void 플레이어가_blackjack이면_배팅한_금액의_150퍼센트를_수익으로_얻는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(bustDealer);
            Player player = new Player("prin", 8000);
            player.receiveInitialCards(카드(ACE), 카드(KING));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.valueOf(12000.0));
        }

        @Test
        void 플레이어가_stay이면_배팅한_금액을_수익으로_얻는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(bustDealer);
            Player player = new Player("prin", 5000);
            player.receiveInitialCards(카드(EIGHT), 카드(QUEEN));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.valueOf(5000));
        }
    }

    @Nested
    class 딜러가_blackjack일_때 {
        @Test
        void 플레이어가_bust이면_배팅한_금액을_모두_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(blackjackDealer);
            Player player = new Player("prin", 1000);
            player.receiveInitialCards(카드(KING), 카드(KING));
            player.receiveAdditionalCard(카드(QUEEN));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.valueOf(-1000));
        }

        @Test
        void 플레이어가_blackjack이면_수익이_없다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(blackjackDealer);
            Player player = new Player("prin", 1000);
            player.receiveInitialCards(카드(ACE), 카드(KING));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.ZERO);
        }

        @Test
        void 플레이어가_stay이면_배팅한_금액을_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(blackjackDealer);
            Player player = new Player("prin", 100);
            player.receiveInitialCards(카드(EIGHT), 카드(QUEEN));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.valueOf(-100));
        }

        @Test
        void 플레이어가_stay_21이면_배팅한_금액을_모두_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(blackjackDealer);
            Player player = new Player("prin", 1000);
            player.receiveInitialCards(카드(EIGHT), 카드(THREE));
            player.receiveAdditionalCard(카드(KING));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.valueOf(-1000));
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 딜러가_stay일_때 {
        @Test
        void 플레이어가_bust이면_배팅한_금액을_모두_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(stayDealer);
            Player player = new Player("prin", 2000);
            player.receiveInitialCards(카드(KING), 카드(KING));
            player.receiveAdditionalCard(카드(QUEEN));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.valueOf(-2000));
        }

        @Test
        void 플레이어가_blackjack이면_배팅한_금액의_150퍼센트를_수익으로_얻는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(stayDealer);
            Player player = new Player("prin", 10000);
            player.receiveInitialCards(카드(ACE), 카드(KING));

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(BigDecimal.valueOf(15000.0));
        }

        @ParameterizedTest
        @MethodSource("provideDoubleCardAndProfit")
        void 플레이어가_stay이면_점수에_따라_수익을_얻는다(Card first, Card second, BigDecimal expected) {
            Dealer dealer = crateDealer(NINE, TEN);
            ProfitResultManager profitResultManager = new ProfitResultManager(dealer);
            Player player = new Player("prin", 1000);
            player.receiveInitialCards(first, second);

            BigDecimal profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(expected);
        }

        private Stream<Arguments> provideDoubleCardAndProfit() {
            return Stream.of(
                    Arguments.of(카드(TEN), 카드(KING), BigDecimal.valueOf(1000)),
                    Arguments.of(카드(TEN), 카드(NINE), BigDecimal.ZERO),
                    Arguments.of(카드(TEN), 카드(EIGHT), BigDecimal.valueOf(-1000))
            );
        }
    }
}
