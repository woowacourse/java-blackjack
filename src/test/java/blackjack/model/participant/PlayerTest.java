package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.game.BlackjackResult;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    static final Name DEFAULT_NAME = new Name("name");

    Hand lowerScoreHand;
    Hand defaultHand;
    Hand higherScoreHand;
    Hand bustScoreHand;

    @BeforeEach
    void initHands() {
        lowerScoreHand = new Hand();
        lowerScoreHand.addCard(new Card(Rank.TWO, Suit.HEART));

        defaultHand = new Hand();
        defaultHand.addCard(new Card(Rank.THREE, Suit.HEART));

        higherScoreHand = new Hand();
        higherScoreHand.addCard(new Card(Rank.TEN, Suit.HEART));

        bustScoreHand = new Hand();
        bustScoreHand.addCards(List.of(
                new Card(Rank.JACK, Suit.HEART),
                new Card(Rank.QUEEN, Suit.HEART),
                new Card(Rank.KING, Suit.HEART)
        ));
    }

    @Test
    void 본인의_이름을_반환한다() {
        // given
        Name playerName = new Name("Player Name");

        // when
        Player player = new Player(playerName);

        // then
        assertThat(player.getName()).isEqualTo(playerName.get());
    }

    @Nested
    class 딜러_손패와_비교하여_결과를_판정한다 {
        @Test
        void 둘_다_버스트가_아니면서_본인의_점수가_더_높다면_승리한다() {
            // given
            Player player = new Player(DEFAULT_NAME, higherScoreHand);
            Dealer dealer = new Dealer(lowerScoreHand);

            // when
            BlackjackResult result = player.calculateResult(dealer.getHand());

            // then
            assertThat(result).isEqualTo(BlackjackResult.WIN);
        }

        @Test
        void 둘_다_버스트가_아니면서_본인의_점수가_더_낮다면_패배한다() {
            // given
            Player player = new Player(DEFAULT_NAME, lowerScoreHand);
            Dealer dealer = new Dealer(higherScoreHand);

            // when
            BlackjackResult result = player.calculateResult(dealer.getHand());

            // then
            assertThat(result).isEqualTo(BlackjackResult.LOSE);
        }

        @Test
        void 둘_다_버스트가_아니면서_점수가_같다면_무승부한다() {
            // given
            Player player = new Player(DEFAULT_NAME, defaultHand);
            Dealer dealer = new Dealer(defaultHand);

            // when
            BlackjackResult result = player.calculateResult(dealer.getHand());

            // then
            assertThat(result).isEqualTo(BlackjackResult.PUSH);
        }

        @Test
        void 본인이_버스트라면_패배한다() {
            // given
            Player player = new Player(DEFAULT_NAME, bustScoreHand);
            Dealer dealer = new Dealer(defaultHand);

            // when
            BlackjackResult result = player.calculateResult(dealer.getHand());

            // then
            assertThat(result).isEqualTo(BlackjackResult.LOSE);
        }

        @Test
        void 딜러만_버스트라면_승리한다() {
            // given
            Player player = new Player(DEFAULT_NAME, defaultHand);
            Dealer dealer = new Dealer(bustScoreHand);

            // when
            BlackjackResult result = player.calculateResult(dealer.getHand());

            // then
            assertThat(result).isEqualTo(BlackjackResult.WIN);
        }

        @Test
        void 둘_다_버스트라면_패배한다() {
            // given
            Player player = new Player(DEFAULT_NAME, bustScoreHand);
            Dealer dealer = new Dealer(bustScoreHand);

            // when
            BlackjackResult result = player.calculateResult(dealer.getHand());

            // then
            assertThat(result).isEqualTo(BlackjackResult.LOSE);
        }
    }
}
