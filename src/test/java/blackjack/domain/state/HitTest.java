package blackjack.domain.state;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(new ArrayList<>(Arrays.asList(
                new Card(Pattern.CLOVER, Number.KING),
                new Card(Pattern.CLOVER, Number.TEN)
        )));
    }

    @Test
    @DisplayName("카드를 뽑았을 때 21이하라면 힛 상태 유지된다.")
    void keepHitTest() {


        State state = new Hit(hand);

        assertThat(state).isInstanceOf(Hit.class);

        state = state.receiveCard(new Card(Pattern.CLOVER, Number.ACE));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 뽑았을 때 21을 초과하면 버스트 상태가 된다.")
    void changeBustTest() {

        State state = new Hit(hand);

        assertThat(state.receiveCard(new Card(Pattern.CLOVER, Number.ACE))).isInstanceOf(Hit.class);

        assertThat(state.receiveCard(new Card(Pattern.CLOVER, Number.ACE))).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("힛 상태에서 스테이 상태로 전환된다.")
    void changeStayTest() {

        State state = new Hit(hand);

        assertThat(state.receiveCard(new Card(Pattern.CLOVER, Number.ACE))).isInstanceOf(Hit.class);
        assertThat(state.stay()).isInstanceOf(Stay.class);
    }
}