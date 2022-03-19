package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StandTest {

    @Test
    @DisplayName("스탠드 상태에서 스코어 비교시 상대가 블랙잭이면 패배 상태가 된다.")
    void loseBlackjack() {
        State stand = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State blackjack = Ready.start(new Card(Denomination.ACE, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        State lose = stand.judge(blackjack);
        assertThat(lose).isInstanceOf(Lose.class);
    }

    @Test
    @DisplayName("스탠드 상태에서 스코어 비교시 상대가 버스트면 승리 상태가 된다.")
    void winBust() {
        State stand = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State bust = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .hit(new Card(Denomination.TWO, Suit.CLUBS));
        State win = stand.judge(bust);
        assertThat(win).isInstanceOf(Win.class);
    }

    @Test
    @DisplayName("스탠드 상태에서 스코어 비교시 상대가 버스트도 블랙잭도 아니면 점수를 비교해서 상대보다 높으면 승리 상태가 된다.")
    void matchScoreWin() {
        State stand1 = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State draw = stand1.judge(stand2);
        assertThat(draw).isInstanceOf(Draw.class);
    }

    @Test
    @DisplayName("스탠드 상태에서 스코어 비교시 상대가 버스트도 블랙잭도 아니면 점수를 비교해서 상대보다 낮으면 패배 상태가 된다.")
    void matchScoreLose() {
        State stand1 = Ready.start(new Card(Denomination.NINE, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State lose = stand1.judge(stand2);
        assertThat(lose).isInstanceOf(Lose.class);
    }

    @Test
    @DisplayName("스탠드 상태에서 스코어 비교시 상대가 버스트도 블랙잭도 아니면 점수를 비교해서 상대랑 똑같으면 무승부가 된다.")
    void matchScoreDraw() {
        State stand1 = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State draw = stand1.judge(stand2);
        assertThat(draw).isInstanceOf(Draw.class);
    }
}
