package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class CardDeckTest {
    @Test
    @DisplayName("카드덱이 잘 생성되는지 확인")
    void create() {
        assertThatCode(CardDeck::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드덱의 사이즈가 52인지 확인")
    void size() {
        final CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.initialize().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드덱이 정상적으로 카드를 분배하는지 확인")
    void distribute() {
        final CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.distribute()).isInstanceOf(Card.class);
    }
}
