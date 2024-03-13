package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(List.of(
                new Card(CardShape.CLOVER, CardNumber.KING),
                new Card(CardShape.HEART, CardNumber.FIVE)
        ));
    }

    @Test
    @DisplayName("핸드의 첫 번째 카드를 반환한다")
    void getFirstCardTest() {
        Card firstCard = hand.getFirstCard();

        assertThat(firstCard).isEqualTo(new Card(CardShape.CLOVER, CardNumber.KING));
    }

    @Test
    @DisplayName("핸드의 밸류를 정상적으로 생성한다.")
    void createHandValueTest() {
        assertThatNoException().isThrownBy(() -> hand.getValue());
    }
}
