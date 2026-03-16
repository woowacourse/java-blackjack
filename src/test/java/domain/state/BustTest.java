package domain.state;

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

public class BustTest {

    private static Queue<Card> deck;
    private static State state;

    @BeforeEach
    void setUp() {
        deck = new ArrayDeque(List.of(
                Card.of(Suit.CLUB, Rank.Q),
                Card.of(Suit.CLUB, Rank.ACE),
                Card.of(Suit.CLUB, Rank.K),
                Card.of(Suit.CLUB, Rank.THREE)));
        state = new Bust(
                Hand.of(new ArrayList<>(List.of(
                        Card.of(Suit.HEART, Rank.Q),
                        Card.of(Suit.HEART, Rank.ACE),
                        Card.of(Suit.HEART, Rank.K),
                        Card.of(Suit.HEART, Rank.J)))));
    }

    @Test
    void Bust_상태에서_카드를_뽑으면_Bust가_반환된다() {
        state = state.draw(deck.poll());

        Assertions.assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void Bust_상태에서_Stay를_호출하면_Bust가_반환된다() {
        state = state.stay();
        Assertions.assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    void Bust_상태의_isFinished는_true를_반환해야_한다() {
        Assertions.assertThat(state.isFinished()).isTrue();
    }

    @Test
    void Bust_상태는_반드시_패배한다() {
        Assertions.assertThat(state.judge(STAY_8_STATE)).isEqualTo(Result.LOSE);
    }
}
