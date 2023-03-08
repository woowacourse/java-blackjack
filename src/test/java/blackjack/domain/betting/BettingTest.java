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
    class earnBlackJackBonus_메서드는 {

        @Test
        void 플레이어가_존재하지_않으면_예외를_던진다() {
            final Player player = new Player("toney");

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            final Betting betting = new Betting(expectedProfit);

            assertThatThrownBy(() -> betting.getProfit(player)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 플레이어가_존재하면_블랙잭_보너스를_획득한다() {
            final Player player = new Player("toney");
            player.drawCard(new Card(QUEEN, CLOVER));
            player.drawCard(new Card(ACE, HEART));

            final Map<Player, Profit> expectedProfit = new HashMap<>();
            expectedProfit.put(player, new Profit(10000));
            final Betting betting = new Betting(expectedProfit);
            betting.earnBlackJackBonus(player);

            assertThat(betting.getProfit(player).getValue()).isEqualTo(15000);
        }
    }
}

class Betting {

    private final Map<Player, Profit> expectedProfit;

    public Betting(final Map<Player, Profit> expectedProfit) {
        this.expectedProfit = expectedProfit;
    }

    public void earnBlackJackBonus(final Player player) {
        validateExistPlayer(player);
        final Profit profit = expectedProfit.get(player);
        expectedProfit.put(player, profit.increaseFiftyPercent());
    }

    private void validateExistPlayer(final Player player) {
        if (!expectedProfit.containsKey(player)) {
            throw new IllegalArgumentException("베팅하지 않은 플레이어입니다.");
        }
    }

    public Profit getProfit(final Player player) {
        validateExistPlayer(player);
        return expectedProfit.get(player);
    }
}
