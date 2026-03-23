package domain.state;

import static domain.state.States.BLACKJACK_STATE;
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

public class BlackjackTest {

    private static Queue<Card> deck;
    private static State state;

    @BeforeEach
    void setUp() {
        deck = new ArrayDeque(List.of(
                Card.of(Suit.CLUB, Rank.Q),
                Card.of(Suit.CLUB, Rank.ACE),
                Card.of(Suit.CLUB, Rank.K),
                Card.of(Suit.CLUB, Rank.THREE)));
        state = new Blackjack(
                Hand.of(new ArrayList<>(List.of(
                        Card.of(Suit.HEART, Rank.Q),
                        Card.of(Suit.HEART, Rank.ACE)))));
    }


    @Test
    void Blackjack_상태의_isFinished는_true를_반환해야_한다() {
        Assertions.assertThat(state.isFinished()).isTrue();
    }

    @Test
    void Blackjack과_Blackjack을_비교하면_무승부를_반환한다() {
        Result result = state.judge(BLACKJACK_STATE);
        Assertions.assertThat(result).isEqualTo(Result.DRAW);
    }

    @Test
    void 비교_대상이_Blackjack이_아니면_블랙잭을_반환한다() {
        Result result = state.judge(STAY_8_STATE);
        Assertions.assertThat(result).isEqualTo(Result.BLACKJACK);
    }

    @Test
    void 카드를_더_뽑으면_예외가_발상해야_한다() {
        Assertions.assertThatThrownBy(() -> state.draw(deck.poll()));
    }

    @Test
    void 스테이를_하면_예외가_발상해야_한다() {
        Assertions.assertThatThrownBy(() -> state.stay());
    }

    @Test
    void Finsihed는_참을_반환해야_한다() {
        Assertions.assertThat(state.isFinished()).isTrue();
    }
}
