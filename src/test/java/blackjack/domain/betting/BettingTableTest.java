package blackjack.domain.betting;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.participant.Result.DRAW;
import static blackjack.domain.participant.Result.LOSE;
import static blackjack.domain.participant.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BettingTableTest {

    @Nested
    class addBonus_메서드는 {

        @Test
        void 플레이어가_존재하지_않으면_예외를_던진다() {
            final Player player = new Player("toney");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            final BettingTable bettingTable = new BettingTable(expectedProfit);

            assertThatThrownBy(() -> bettingTable.addBonus(player)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 플레이어가_존재하면_보너스를_추가한다() {
            final Player player = new Player("toney");
            player.drawCard(new Card(QUEEN, CLOVER));
            player.drawCard(new Card(ACE, HEART));

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            expectedProfit.put(player, new Profit(10000));
            final BettingTable bettingTable = new BettingTable(expectedProfit);
            bettingTable.addBonus(player);

            assertThat(bettingTable.getPlayerProfit(player).getValue()).isEqualTo(15000);
        }
    }

    @Nested
    class updateByResult_메서드는 {

        @Test
        void 플레이어가_존재하지_않으면_예외를_던진다() {
            final Player player = new Player("toney");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            final BettingTable bettingTable = new BettingTable(expectedProfit);

            assertThatThrownBy(() -> bettingTable.updateByResult(player, WIN)).isInstanceOf(IllegalArgumentException.class);
        }

        @Nested
        class 플레이어가_존재하면 {

            @Test
            void 결과가_WIN_일때_수익이_유지된다() {
                final Player player = new Player("toney");

                final Map<Player, Profit> expectedProfit = new HashMap<>();
                expectedProfit.put(player, new Profit(10000));
                final BettingTable bettingTable = new BettingTable(expectedProfit);
                bettingTable.updateByResult(player, WIN);

                assertThat(bettingTable.getPlayerProfit(player).getValue()).isEqualTo(10000);
            }

            @Test
            void 결과가_DRAW_일때_수익이_사라진다() {
                final Player player = new Player("toney");

                final Map<Player, Profit> expectedProfit = new HashMap<>();
                expectedProfit.put(player, new Profit(10000));
                final BettingTable bettingTable = new BettingTable(expectedProfit);
                bettingTable.updateByResult(player, DRAW);

                assertThat(bettingTable.getPlayerProfit(player).getValue()).isEqualTo(0);
            }

            @Test
            void 결과가_LOSE_일때_수익이_지출로_변한다() {
                final Player player = new Player("toney");

                final Map<Player, Profit> expectedProfit = new HashMap<>();
                expectedProfit.put(player, new Profit(10000));
                final BettingTable bettingTable = new BettingTable(expectedProfit);
                bettingTable.updateByResult(player, LOSE);

                assertThat(bettingTable.getPlayerProfit(player).getValue()).isEqualTo(-10000);
            }
        }
    }

    @Nested
    class getPlayerProfit_메서드는 {

        @Test
        void 플레이어가_존재하지_않으면_예외를_던진다() {
            final Player player = new Player("toney");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            final BettingTable bettingTable = new BettingTable(expectedProfit);

            assertThatThrownBy(() -> bettingTable.getPlayerProfit(player)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 플레이어가_존재하면_수익을_확인한다() {
            final Player player = new Player("toney");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            expectedProfit.put(player, new Profit(10000));
            final BettingTable bettingTable = new BettingTable(expectedProfit);

            assertThat(bettingTable.getPlayerProfit(player).getValue()).isEqualTo(10000);
        }
    }

    @Nested
    class getDealerProfit_메서드는 {

        @Test
        void 플레이어들의_지출보다_수입이_더_크면_지출이_발생한다() {
            final Player firstPlayer = new Player("toney");
            final Player secondPlayer = new Player("dazzle");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            expectedProfit.put(firstPlayer, new Profit(20000));
            expectedProfit.put(secondPlayer, new Profit(-10000));
            final BettingTable bettingTable = new BettingTable(expectedProfit);

            assertThat(bettingTable.getDealerProfit().getValue()).isEqualTo(-10000);
        }

        @Test
        void 플레이어들의_수입보다_지출이_더_크면_수입이_발생한다() {
            final Player firstPlayer = new Player("toney");
            final Player secondPlayer = new Player("dazzle");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            expectedProfit.put(firstPlayer, new Profit(-20000));
            expectedProfit.put(secondPlayer, new Profit(10000));
            final BettingTable bettingTable = new BettingTable(expectedProfit);

            assertThat(bettingTable.getDealerProfit().getValue()).isEqualTo(10000);
        }
    }
}
