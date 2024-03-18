package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<Card> cards = List.of(new Card(CardRank.THREE, CardShape.CLOVER));

        assertThatCode(() -> new CardDeck(cards))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드 한장을 뽑을 수 있다.")
    @Test
    void draw() {
        Card card = new Card(CardRank.EIGHT, CardShape.CLOVER);
        CardDeck cardDeck = new CardDeck(List.of(card));

        Card result = cardDeck.draw();

        assertThat(result).isEqualTo(card);
    }

    @DisplayName("중복 카드는 존재할 수 없다.")
    @Test
    void validateDuplicated() {
        Card card = new Card(CardRank.EIGHT, CardShape.DIAMOND);
        List<Card> duplicatedCards = List.of(card, card);

        assertThatThrownBy(() -> new CardDeck(duplicatedCards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드가 존재하지 않으면, 더 이상 뽑을 수 없다.")
    @Test
    void drawEmpty() {
        Card card = new Card(CardRank.EIGHT, CardShape.CLOVER);
        CardDeck cardDeck = new CardDeck(List.of(card));
        cardDeck.draw();

        assertThatThrownBy(cardDeck::draw)
                .isInstanceOf(IllegalStateException.class);
    }
}
