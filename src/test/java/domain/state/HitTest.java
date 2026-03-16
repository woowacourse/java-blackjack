package domain.state;

import static domain.state.States.BLACKJACK_STATE;
import static domain.state.States.BUST_STATE;
import static domain.state.States.STAY_12_STATE;
import static domain.state.States.STAY_21_STATE;
import static domain.state.States.STAY_8_STATE;

import domain.Result;
import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HitTest {
    private static Queue<Card> deck;
    private static State state;

    @BeforeEach
    void setUp() {
        deck = new ArrayDeque(List.of(
                Card.of(Suit.CLUB, Rank.TWO),
                Card.of(Suit.CLUB, Rank.ACE),
                Card.of(Suit.CLUB, Rank.K),
                Card.of(Suit.CLUB, Rank.THREE)));
        state = Hit.of(Hand.of(new ArrayList<>(List.of(
                Card.of(Suit.DIAMOND, Rank.TWO),
                Card.of(Suit.DIAMOND, Rank.TEN)))));
    }

    @Test
    void 카드를_뽑고_합계가_21을_넘지_않으면_Hit가_반환된다() {
        state = state.draw(deck.poll());

        Assertions.assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void 카드를_뽑고_합계가_21을_넘으면_않으면_Bust가_반환된다() {
        for (int i = 0; i < 4; i++) {
            state = state.draw(deck.poll());
        }

        Assertions.assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void Hit_상태에서_Stay를_호출하면_Stay가_반환된다() {
        state = state.stay();
        Assertions.assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    void Hit_상태의_isFinished는_false를_반환해야_한다() {
        Assertions.assertThat(state.isFinished()).isFalse();
    }

    @Test
    void Hit_상태에서_Blackjack과_비교하면_패배한다() {
        Result judge = state.judge(BLACKJACK_STATE);
        Assertions.assertThat(judge).isEqualTo(Result.LOSE);
    }

    @Test
    void Hit_상태에서_Bust과_비교하면_승리한다() {
        Result judge = state.judge(BUST_STATE);
        Assertions.assertThat(judge).isEqualTo(Result.WIN);
    }

    @Test
    void Hit_상태에서_점수가_더_높으면_승리한다() {
        Result judge = state.judge(STAY_8_STATE);
        Assertions.assertThat(judge).isEqualTo(Result.WIN);
    }

    @Test
    void Hit_상태에서_점수가_더_낮으면_패배한다() {
        Result judge = state.judge(STAY_21_STATE);
        Assertions.assertThat(judge).isEqualTo(Result.LOSE);
    }

    @Test
    void Hit_상태에서_점수가_같으면_무승부한다() {
        Result judge = state.judge(STAY_12_STATE);
        Assertions.assertThat(judge).isEqualTo(Result.DRAW);
    }
}
