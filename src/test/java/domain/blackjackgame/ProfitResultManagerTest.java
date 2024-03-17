package domain.blackjackgame;

import static fixture.CardFixture.카드;
import static fixture.ParticipantFixture.딜러;
import static fixture.ParticipantFixture.버스트_딜러;
import static fixture.ParticipantFixture.블랙잭_딜러;
import static fixture.ParticipantFixture.스테이_딜러;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Denomination;
import domain.participant.BetAmount;
import domain.participant.Player;
import domain.participant.name.Name;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProfitResultManagerTest {
    @Nested
    class 딜러가_bust일_때 {
        @Test
        void 플레이어가_bust이면_배팅한_금액을_모두_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(버스트_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.KING), 카드(Denomination.KING));
            player.receiveAdditionalCard(카드(Denomination.QUEEN));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(-1000);
        }

        @Test
        void 플레이어가_blackjack이면_배팅한_금액의_150퍼센트를_수익으로_얻는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(버스트_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.ACE), 카드(Denomination.KING));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(1500);
        }

        @Test
        void 플레이어가_stay이면_배팅한_금액을_수익으로_얻는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(버스트_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.EIGHT), 카드(Denomination.QUEEN));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(1000);
        }
    }

    @Nested
    class 딜러가_blackjack일_때 {
        @Test
        void 플레이어가_bust이면_배팅한_금액을_모두_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(블랙잭_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.KING), 카드(Denomination.KING));
            player.receiveAdditionalCard(카드(Denomination.QUEEN));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(-1000);
        }

        @Test
        void 플레이어가_blackjack이면_수익이_없다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(블랙잭_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.ACE), 카드(Denomination.KING));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(0);
        }

        @Test
        void 플레이어가_stay이면_배팅한_금액을_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(블랙잭_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.EIGHT), 카드(Denomination.QUEEN));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(-1000);
        }

        @Test
        void 플레이어가_stay_21이면_배팅한_금액을_모두_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(블랙잭_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.EIGHT), 카드(Denomination.THREE));
            player.receiveAdditionalCard(카드(Denomination.KING));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(-1000);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 딜러가_stay일_때 {
        @Test
        void 플레이어가_bust이면_배팅한_금액을_모두_잃는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(스테이_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.KING), 카드(Denomination.KING));
            player.receiveAdditionalCard(카드(Denomination.QUEEN));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(-1000);
        }

        @Test
        void 플레이어가_blackjack이면_배팅한_금액의_150퍼센트를_수익으로_얻는다() {
            ProfitResultManager profitResultManager = new ProfitResultManager(스테이_딜러());
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(Denomination.ACE), 카드(Denomination.KING));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(1500);
        }

        @ParameterizedTest
        @MethodSource("provideDenominationsAndProfit")
        void 플레이어가_stay이면_점수에_따라_수익을_얻는다(Denomination first, Denomination second, int expected) {
            ProfitResultManager profitResultManager = new ProfitResultManager(딜러(Denomination.NINE, Denomination.TEN));
            Player player = new Player(new Name("prin"), new BetAmount(1000));
            player.receiveInitialCards(카드(first), 카드(second));

            int profit = profitResultManager.calculateProfit(player);

            assertThat(profit).isEqualTo(expected);
        }

        private Stream<Arguments> provideDenominationsAndProfit() {
            return Stream.of(
                    Arguments.of(Denomination.TEN, Denomination.KING, 1000),
                    Arguments.of(Denomination.TEN, Denomination.NINE, 0),
                    Arguments.of(Denomination.TEN, Denomination.EIGHT, -1000)
            );
        }
    }
}
