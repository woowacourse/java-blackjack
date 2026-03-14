package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.state.BlackjackState;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private static final Name DEFAULT_NAME = new Name("name");
    private static final Bet DEFAULT_BET = new Bet(10000);

    private final BlackjackState loseState = BlackjackState.init()
            .hit(new Card(Rank.TWO, Suit.HEART));
    private final BlackjackState pushState = BlackjackState.init()
            .hit(new Card(Rank.THREE, Suit.HEART));
    private final BlackjackState winState = BlackjackState.init()
            .hit(new Card(Rank.TEN, Suit.HEART));

    @Test
    void 본인의_이름을_반환한다() {
        // given
        Name playerName = new Name("Player Name");

        // when
        Player player = new Player(playerName, DEFAULT_BET);

        // then
        assertThat(player.getName()).isEqualTo(playerName.get());
    }

    @Nested
    class 결과를_통해_수익을_계산한다 {
        @Test
        void 승리했다면_베팅금만큼_얻는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, winState);
            Dealer dealer = new Dealer(loseState);

            double expectedProfit = DEFAULT_BET.amount();

            // when
            double actualProfit = player.calculateProfit(dealer.getState());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 패배했다면_베팅금만큼_잃는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, loseState);
            Dealer dealer = new Dealer(winState);

            double expectedProfit = DEFAULT_BET.amount() * -1;

            // when
            double actualProfit = player.calculateProfit(dealer.getState());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 푸시했다면_얻지도_잃지도_않는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, pushState);
            Dealer dealer = new Dealer(pushState);

            double expectedProfit = 0;

            // when
            double actualProfit = player.calculateProfit(dealer.getState());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }
    }
}
