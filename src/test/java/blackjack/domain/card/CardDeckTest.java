package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 덱에서 카드를 뽑는다.")
    void pickCard() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.pickCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드가 모두 소진 된 상태에서 카드를 뽑을 경우 예외를 발생시킨다.")
    void throwExceptionWhenOutOfIndex() {
        CardDeck cardDeck = new CardDeck();

        for (int i = 0; i < 52; i++) {
            cardDeck.pickCard();
        }

        assertThatThrownBy(cardDeck::pickCard)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("[ERROR] 카드를 더 이상 뽑을 수 없습니다.");
    }
}
