package blackjack.domain.state;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayRecord;

public class ReadyTest {

    @Test
    @DisplayName("draw으로 카드 한 장을 뽑은 경우 Ready 상태이다.")
    void draw() {
        //given
        State state = new Ready();

        //when
        State newState = state.draw(new Card(HEART, JACK));

        //then
        assertThat(newState).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("2 장을 뽑으면 Hit 상태로 바뀐다.")
    void toHit() {
        //given
        State state = new Ready();

        //when
        State newState = state.draw(new Card(HEART, JACK));
        State hitState = newState.draw(new Card(CLUB, JACK));

        //then
        assertThat(hitState).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("처음 2장의 카드의 합이 21이면 Blackjack 상태로 바뀐다.")
    void toBlackjack() {
        //given
        State state = new Ready();

        //when
        State newState = state.draw(new Card(HEART, JACK));
        State blackjackState = newState.draw(new Card(HEART, ACE));

        //then
        assertThat(blackjackState).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("Ready 상태에서 stay를 호출하면 예외를 던진다.")
    void stay_error() {
        //given
        State readyState = new Ready();

        //then
        assertThatThrownBy(readyState::stay
        ).isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Ready");
    }

    @Test
    @DisplayName("Hit 상태에서 draw 로 21이 초과하면 Bust 상태로 바뀐다.")
    void toBust() {
        //given
        State hitState = new Hit(new Cards(Set.of(new Card(HEART, JACK), new Card(CLUB, JACK))));

        //when
        State bustState = hitState.draw(new Card(HEART, TWO));

        //then
        assertThat(bustState).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("stay 메소드 호출로 Stay 상태로 바뀐다.")
    void toPush() {
        State hitState = new Hit(new Cards(Set.of(new Card(HEART, JACK), new Card(CLUB, JACK))));

        //when
        State stayState = hitState.stay();

        //then
        assertThat(stayState).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("Finished 상태가 아니면 revenue 호출시 오류를 던진다.")
    void revenue_error() {
        //given
        State notFinishedState = new Hit(new Cards(Set.of(new Card(HEART, JACK), new Card(CLUB, JACK))));

        //then
        assertThatThrownBy(() ->
            notFinishedState.revenue(PlayRecord.WIN, new Bet(100))
        ).isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Finished");
    }
}
