package blackjack.domain.betting;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.HEART;
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
class BettingTest {

    @Nested
    class addBlackJackBonus_메서드는 {

        @Test
        void 플레이어가_존재하지_않으면_예외를_던진다() {
            final Player player = new Player("toney");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            final Betting betting = new Betting(expectedProfit);

            assertThatThrownBy(() -> betting.addBlackJackBonus(player)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 플레이어가_존재하면_블랙잭_보너스를_추가한다() {
            final Player player = new Player("toney");
            player.drawCard(new Card(QUEEN, CLOVER));
            player.drawCard(new Card(ACE, HEART));

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            expectedProfit.put(player, new Profit(10000));
            final Betting betting = new Betting(expectedProfit);
            betting.addBlackJackBonus(player);

            assertThat(betting.getPlayerProfit(player).getValue()).isEqualTo(15000);
        }
    }

    @Test
    void 플레이어가_베팅에_실패하면_수익을_잃는다() {
        final Player player = new Player("toney");
        player.drawCard(new Card(QUEEN, CLOVER));
        player.drawCard(new Card(ACE, HEART));

        final Map<Player, Profit> expectedProfit = new HashMap<>();
        expectedProfit.put(player, new Profit(10000));
        final Betting betting = new Betting(expectedProfit);
        betting.fail(player);

        assertThat(betting.getPlayerProfit(player).getValue()).isEqualTo(-10000);
    }

    @Nested
    class getPlayerProfit_메서드는 {

        @Test
        void 플레이어가_존재하지_않으면_예외를_던진다() {
            final Player player = new Player("toney");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            final Betting betting = new Betting(expectedProfit);

            assertThatThrownBy(() -> betting.getPlayerProfit(player)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 플레이어가_존재하면_수익을_확인한다() {
            final Player player = new Player("toney");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            expectedProfit.put(player, new Profit(10000));
            final Betting betting = new Betting(expectedProfit);

            assertThat(betting.getPlayerProfit(player).getValue()).isEqualTo(10000);
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
            final Betting betting = new Betting(expectedProfit);

            assertThat(betting.getDealerProfit().getValue()).isEqualTo(-10000);
        }

        @Test
        void 플레이어들의_수입보다_지출이_더_크면_수입이_발생한다() {
            final Player firstPlayer = new Player("toney");
            final Player secondPlayer = new Player("dazzle");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            expectedProfit.put(firstPlayer, new Profit(-20000));
            expectedProfit.put(secondPlayer, new Profit(10000));
            final Betting betting = new Betting(expectedProfit);

            assertThat(betting.getDealerProfit().getValue()).isEqualTo(10000);
        }
    }
}
