package domain.state;

import static domain.state.States.BUST_STATE;

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
        for (int i = 0; i < 3; i++) {
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
    void Hit_상태에서_judge_수행_시_예외가_발생해야_한다() {
        Assertions.assertThatThrownBy(() -> state.judge(BUST_STATE));
    }

    @Test
    void 새롭게_변경된_상태는_기존_hand의_영향을_받으면_안_된다() {
        Hand hand = Hand.of(new ArrayList<>(List.of(
                Card.of(Suit.DIAMOND, Rank.TWO),
                Card.of(Suit.DIAMOND, Rank.TEN))));

        State hitState = Hit.of(hand);
        hand.add(Card.of(Suit.DIAMOND, Rank.NINE));

        Assertions.assertThat(hitState.draw(deck.poll())).isInstanceOf(Hit.class);
    }
}
