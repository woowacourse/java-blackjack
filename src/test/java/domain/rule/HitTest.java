package domain.rule;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardScore;
import domain.card.CardSuit;
import domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    @DisplayName("초기 2장이 블랙잭이면 Blackjack 상태를 반환한다.")
    void blackjackInitialStateTest() {
        Hand hand = new Hand(List.of(
                new Card(CardScore.ACE, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        State state = Hit.of(hand);
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("초기 2장이 블랙잭이 아니면 Hit 상태를 반환한다.")
    void hitInitialStateTest() {
        Hand hand = new Hand(List.of(
                new Card(CardScore.TWO, CardSuit.CLUB),
                new Card(CardScore.THREE, CardSuit.HEART)
        ));
        State state = Hit.of(hand);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 뽑아서 버스트가 아니면 Hit 상태를 반환한다.")
    void drawNotBustTest() {
        Hand hand = new Hand(List.of(
                new Card(CardScore.TWO, CardSuit.CLUB),
                new Card(CardScore.THREE, CardSuit.HEART)
        ));
        Hit hit = new Hit(hand);
        State state = hit.draw(new Card(CardScore.FOUR, CardSuit.CLUB));
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 뽑아서 버스트면 Bust 상태를 반환한다.")
    void drawBustTest() {
        Hand hand = new Hand(List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.KING, CardSuit.HEART)
        ));
        Hit hit = new Hit(hand);
        State state = hit.draw(new Card(CardScore.FIVE, CardSuit.CLUB));
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("스테이하면 Stay 상태를 반환한다.")
    void stayTest() {
        Hand hand = new Hand(List.of(
                new Card(CardScore.TWO, CardSuit.CLUB),
                new Card(CardScore.THREE, CardSuit.HEART)
        ));
        Hit hit = new Hit(hand);
        State state = hit.stay();
        assertThat(state).isInstanceOf(Stay.class);
    }
}
