package blackjack.model.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerStateTest {

    @DisplayName("카드 숫자 합이 21미만 이면 true를 반환한다.")
    @Test
    void canHit_true() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.CLOVER));

        assertThat(state.canHit()).isTrue();
    }

    @DisplayName("카드 숫자 합이 21이상 이면 false를 반환한다.")
    @Test
    void canHit_false() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));

        assertThat(state.canHit()).isFalse();
    }

    @DisplayName("카드 숫자 합이 21을 넘으면 true를 반환한다.")
    @Test
    void isBust_true() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        state.canHit();

        assertThat(state.isBust()).isTrue();
    }

    @DisplayName("카드 숫자 합이 21을 넘지 않으면 false를 반환한다.")
    @Test
    void isBust_false() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));
        state.canHit();

        assertThat(state.isBust()).isFalse();
    }

    @DisplayName("카드 숫자 합이 21을 넘으면 false를 반환한다.")
    @Test
    void isBlackjack_false() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));
        state.canHit();

        assertThat(state.isBust()).isFalse();
    }

    @DisplayName("카드 숫자 합이 21이면 true를 반환한다.")
    @Test
    void isBlackjack_true() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));
        state.canHit();

        assertThat(state.isBust()).isFalse();
    }

    @DisplayName("카드 숫자 합이 다른 State의 카드 숫자 합보다 크면 true를 반환한다.")
    @Test
    void isWinBy_true() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));

        State otherState = new PlayerState();
        otherState.addCard(new Card(TrumpNumber.KING, TrumpSymbol.HEART));
        otherState.addCard(new Card(TrumpNumber.JACK, TrumpSymbol.SPADE));

        assertThat(state.isWinBy(otherState)).isTrue();
    }

    @DisplayName("카드 숫자 합이 다른 State의 카드 숫자 합보다 작으면 false를 반환한다.")
    @Test
    void isWinBy_false() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));

        State otherState = new PlayerState();
        otherState.addCard(new Card(TrumpNumber.KING, TrumpSymbol.HEART));
        otherState.addCard(new Card(TrumpNumber.JACK, TrumpSymbol.SPADE));

        assertThat(state.isWinBy(otherState)).isFalse();
    }

    @DisplayName("카드 숫자 합이 다른 State의 카드 숫자 합과 같으면 true를 반환한다.")
    @Test
    void isDrawWith_true() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));

        State otherState = new PlayerState();
        otherState.addCard(new Card(TrumpNumber.THREE, TrumpSymbol.HEART));
        otherState.addCard(new Card(TrumpNumber.EIGHT, TrumpSymbol.SPADE));

        assertThat(state.isDrawWith(otherState)).isTrue();
    }

    @DisplayName("카드 숫자 합이 다른 State의 카드 숫자 합과 다르면 false를 반환한다.")
    @Test
    void isDrawWith_false() {
        State state = new PlayerState();
        state.addCard(new Card(TrumpNumber.FOUR, TrumpSymbol.HEART));
        state.addCard(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));
        state.addCard(new Card(TrumpNumber.FIVE, TrumpSymbol.HEART));

        State otherState = new PlayerState();
        otherState.addCard(new Card(TrumpNumber.THREE, TrumpSymbol.HEART));
        otherState.addCard(new Card(TrumpNumber.EIGHT, TrumpSymbol.SPADE));

        assertThat(state.isDrawWith(otherState)).isFalse();
    }
}