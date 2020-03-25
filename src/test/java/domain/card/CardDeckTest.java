package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("NonAsciiCharacters")
public class CardDeckTest {
    private static final int MAX_CARD_COUNT = 52;

    @Test
    void 카드덱_생성_테스트() {
        CardDeck cardDeck = new CardDeck();

        Assertions.assertThat(cardDeck).hasFieldOrProperty("cards");
    }

    @Test
    void 카드를_뽑는_메서드_테스트() {
        CardDeck cardDeck = new CardDeck();

        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < MAX_CARD_COUNT; i++) {
            cards.add(cardDeck.pick());
        }

        Assertions.assertThat(cards.size()).isEqualTo(MAX_CARD_COUNT);
    }

    @Test
    void 카드를_다뽑았을때_예외발생확인_테스트() {
        CardDeck cardDeck = new CardDeck();

        for (int i = 0; i < MAX_CARD_COUNT; i++) {
            cardDeck.pick();
        }

        Assertions.assertThatThrownBy(cardDeck::pick)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("더 이상 뽑을 카드가 없습니다.");
    }
}
