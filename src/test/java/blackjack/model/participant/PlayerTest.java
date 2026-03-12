package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.hand.Hand;
import blackjack.model.hand.UninitializedHand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    static final Name DEFAULT_NAME = new Name("name");
    static final Bet DEFAULT_BET = new Bet(10000);

    Hand loseHand;
    Hand pushHand;
    Hand winHand;

    @BeforeEach
    void initHands() {
        loseHand = new UninitializedHand();
        loseHand = loseHand.hit(new Card(Rank.TWO, Suit.HEART));

        pushHand = new UninitializedHand();
        pushHand = pushHand.hit(new Card(Rank.THREE, Suit.HEART));

        winHand = new UninitializedHand();
        winHand = winHand.hit(new Card(Rank.TEN, Suit.HEART));
    }

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
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, winHand);
            Dealer dealer = new Dealer(loseHand);

            double expectedProfit = DEFAULT_BET.amount();

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 패배했다면_베팅금만큼_잃는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, loseHand);
            Dealer dealer = new Dealer(winHand);

            double expectedProfit = DEFAULT_BET.amount() * -1;

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 푸시했다면_얻지도_잃지도_않는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, pushHand);
            Dealer dealer = new Dealer(pushHand);

            double expectedProfit = 0;

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }
    }
}
