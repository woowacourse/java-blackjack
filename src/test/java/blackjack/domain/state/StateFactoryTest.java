package blackjack.domain.state;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.running.Hit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {

    @Test
    @DisplayName("처음지 2장의 카드를 받을 때 A와 10(10,K,Q,J)를 받으면 블랙잭이다.")
    void blackjackTest() {
        List<Card> cards = Arrays.asList(
                new Card(Pattern.CLOVER, Number.ACE),
                new Card(Pattern.CLOVER, Number.TEN)
        );

        State state = StateFactory.initHand(cards);

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("처음 2장의 카드를 받을 때 점수가 21이 아니라면 힛 상태다.")
    void hitTest() {
        List<Card> cards = Arrays.asList(
                new Card(Pattern.CLOVER, Number.KING),
                new Card(Pattern.CLOVER, Number.TEN)
        );

        State state = StateFactory.initHand(cards);

        assertThat(state).isInstanceOf(Hit.class);
    }

}