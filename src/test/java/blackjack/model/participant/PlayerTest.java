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

    Hand lowerScoreHand;
    Hand defaultHand;
    Hand higherScoreHand;
    Hand bustScoreHand;

    @BeforeEach
    void initHands() {
        lowerScoreHand = new UninitializedHand();
        lowerScoreHand.hit(new Card(Rank.TWO, Suit.HEART));

        defaultHand = new UninitializedHand();
        defaultHand.hit(new Card(Rank.THREE, Suit.HEART));

        higherScoreHand = new UninitializedHand();
        higherScoreHand.hit(new Card(Rank.TEN, Suit.HEART));

        bustScoreHand = new UninitializedHand();
        bustScoreHand.hit(new Card(Rank.JACK, Suit.HEART));
        bustScoreHand.hit(new Card(Rank.QUEEN, Suit.HEART));
        bustScoreHand.hit(new Card(Rank.KING, Suit.HEART));
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
    class 딜러_손패와_비교하여_수익을_계산한다 {
        @Test
        void 둘_다_버스트가_아니면서_본인의_점수가_더_높다면_베팅금만큼_얻는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, higherScoreHand);
            Dealer dealer = new Dealer(lowerScoreHand);

            double expectedProfit = DEFAULT_BET.amount();

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 둘_다_버스트가_아니면서_본인의_점수가_더_낮다면_베팅금만큼_잃는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, lowerScoreHand);
            Dealer dealer = new Dealer(higherScoreHand);

            double expectedProfit = DEFAULT_BET.amount() * -1;

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 둘_다_버스트가_아니면서_점수가_같다면_얻지도_잃지도_않는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, defaultHand);
            Dealer dealer = new Dealer(defaultHand);

            double expectedProfit = 0;

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 본인이_버스트라면_베팅금만큼_잃는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, bustScoreHand);
            Dealer dealer = new Dealer(defaultHand);

            double expectedProfit = DEFAULT_BET.amount() * -1;

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 딜러만_버스트라면_베팅금만큼_얻는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, defaultHand);
            Dealer dealer = new Dealer(bustScoreHand);

            double expectedProfit = DEFAULT_BET.amount();

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        void 둘_다_버스트라면_베팅금만큼_잃는다() {
            // given
            Player player = new Player(DEFAULT_NAME, DEFAULT_BET, bustScoreHand);
            Dealer dealer = new Dealer(bustScoreHand);

            double expectedProfit = DEFAULT_BET.amount() * -1;

            // when
            double actualProfit = player.calculateProfit(dealer.getHand());

            // then
            assertThat(actualProfit).isEqualTo(expectedProfit);
        }
    }
}
