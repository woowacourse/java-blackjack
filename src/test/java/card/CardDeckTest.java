package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardDeckTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -19, 53, 100})
    @DisplayName("총 카드의 갯수 이상이거나 음수의 숫자가 들어오는 경우 ")
    void unAcceptableCardIndex(int randomIndex) {
        CardDeck cardDeck = new CardDeck();

        Assertions.assertThatThrownBy(() -> cardDeck.pickCard(randomIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(randomIndex + "는 뽑을 수 있는 카드가 아닙니다.");
    }
}