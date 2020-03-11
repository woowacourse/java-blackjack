package carddeck;

import card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("NonAsciiCharacters")
public class CardDeckTest {
    @Test
    void 카드덱_생성_테스트() {
        CardDeck cardDeck = CardDeckFactory.create();

        Assertions.assertThat(cardDeck).hasFieldOrProperty("cards");
    }

    @Test
    void 카드를_뽑는_메서드_테스트() {
        CardDeck cardDeck = CardDeckFactory.create();

        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(cardDeck.draw());
        }

        Assertions.assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    void 예외상황_카드를_다뽑았을때_테스트() {
        CardDeck cardDeck = CardDeckFactory.create();

        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }

        Assertions.assertThatThrownBy(() -> cardDeck.draw())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("더 이상 뽑을 카드가 없습니다.");
    }
}
