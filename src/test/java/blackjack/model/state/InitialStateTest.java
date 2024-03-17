package blackjack.model.state;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialStateTest {
    private InitialState initialState;

    @BeforeEach
    void setUp() {
        initialState = new InitialState();
    }

    @DisplayName("initialState 상태일 떄 한장의 추가카드는 받을 수 없다")
    @Test
    void drawBust() {
        Card card = new Card(CardNumber.TEN, CardShape.CLOVER);

        assertThatThrownBy(() -> initialState.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("initialState 상태에서는 카드 여러장을 뽑을 수 있다")
    @Test
    void drawCards() {
        List<Card> cardsToAdd = List.of(new Card(CardNumber.TWO, CardShape.SPADE),
                new Card(CardNumber.THREE, CardShape.HEART));

        assertThatCode(() -> initialState.drawCards(cardsToAdd))
                .doesNotThrowAnyException();
    }

    @DisplayName("initialState 상태에서 카드뽑기를 멈출 수 없다")
    @Test
    void stand() {
        assertThatThrownBy(() -> initialState.stand())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("initialState 상태에서는 승패를 계산할 수 없다")
    @Test
    void calculateProfit() {
        State state = new Stand(new Cards());

        assertThatThrownBy(() -> initialState.determineResult(state))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
