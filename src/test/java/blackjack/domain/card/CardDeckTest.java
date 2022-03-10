package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck();
    }

    @DisplayName("pop 메서드는 서로 중복되지 않는 카드를 카드덱에서 뽑아온다.")
    @Test
    void pop_eachCardIsUnique() {
        Card card1 = cardDeck.pop();
        Card card2 = cardDeck.pop();
        Card card3 = cardDeck.pop();

        int originalSize = List.of(card1, card2, card3).size();
        int noDuplicateSize = Set.of(card1, card2, card3).size();

        assertThat(originalSize).isEqualTo(noDuplicateSize);
    }

    @DisplayName("pop 메서드는 52회까지 실행할 수 있으며, 53번째에서는 예외가 발생한다.")
    @Test
    void pop_cardDeckConsistsOf52Cards() {
        for (int i = 0; i < 52; i++) {
            Assertions.assertDoesNotThrow(() -> cardDeck.pop());
        }

        assertThatThrownBy(() -> cardDeck.pop())
                .isInstanceOf(NoSuchElementException.class);
    }
}
