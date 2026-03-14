package blackjack.model.game;

import static blackjack.model.game.BlackjackResult.LOSS;
import static blackjack.model.game.BlackjackResult.PUSH;
import static blackjack.model.game.BlackjackResult.WIN;
import static blackjack.model.game.BlackjackResult.judge;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.state.BlackjackState;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    private final BlackjackState lowerScoreState = BlackjackState.init()
            .hit(new Card(Rank.TWO, Suit.HEART));

    private final BlackjackState defaultState = BlackjackState.init()
            .hit(new Card(Rank.THREE, Suit.HEART));

    private final BlackjackState higherScoreState = BlackjackState.init()
            .hit(new Card(Rank.TEN, Suit.HEART));

    private final BlackjackState bustScoreState = BlackjackState.init()
            .hit(new Card(Rank.JACK, Suit.HEART))
            .hit(new Card(Rank.QUEEN, Suit.HEART))
            .hit(new Card(Rank.KING, Suit.HEART));

    @Nested
    class 딜러와_플레이어의_손패를_비교하여_수익을_계산한다 {
        @Test
        void 둘_다_버스트가_아니면서_플레이어의_점수가_더_높다면_승리한다() {
            // given
            BlackjackState playerState = higherScoreState;
            BlackjackState dealerState = lowerScoreState;

            // when
            BlackjackResult result = judge(playerState, dealerState);

            // then
            assertThat(result).isEqualTo(WIN);
        }

        @Test
        void 둘_다_버스트가_아니면서_본인의_점수가_더_낮다면_패배한다() {
            // given
            BlackjackState playerState = lowerScoreState;
            BlackjackState dealerState = higherScoreState;

            // when
            BlackjackResult result = judge(playerState, dealerState);

            // then
            assertThat(result).isEqualTo(LOSS);
        }

        @Test
        void 둘_다_버스트가_아니면서_점수가_같다면_푸시한다() {
            // given
            BlackjackState playerState = defaultState;
            BlackjackState dealerState = defaultState;

            // when
            BlackjackResult result = judge(playerState, dealerState);

            // then
            assertThat(result).isEqualTo(PUSH);
        }

        @Test
        void 본인이_버스트라면_패배한다() {
            // given
            BlackjackState playerState = bustScoreState;
            BlackjackState dealerState = higherScoreState;

            // when
            BlackjackResult result = judge(playerState, dealerState);

            // then
            assertThat(result).isEqualTo(LOSS);
        }

        @Test
        void 딜러만_버스트라면_승리한다() {
            // given
            BlackjackState playerState = defaultState;
            BlackjackState dealerState = bustScoreState;

            // when
            BlackjackResult result = judge(playerState, dealerState);

            // then
            assertThat(result).isEqualTo(WIN);
        }

        @Test
        void 둘_다_버스트라면_패배한다() {
            // given
            BlackjackState playerState = bustScoreState;
            BlackjackState dealerState = bustScoreState;

            // when
            BlackjackResult result = judge(playerState, dealerState);

            // then
            assertThat(result).isEqualTo(LOSS);
        }
    }
}
