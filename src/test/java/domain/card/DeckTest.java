package domain.card;

import static message.ErrorMessage.DECK_IS_EMPTY;

import java.util.NoSuchElementException;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @DisplayName("52장의 서로 다른 카드가 정상 생성된다.")
    @Test
    void _52장의_서로_다른_카드가_정상_생성된다() {
        Deck deck = new Deck(new ShuffledCardGenerator());

        Set<Card> distinctCards = Set.copyOf(deck.getCards());

        Assertions.assertThat(distinctCards.size()).isEqualTo(deck.getCards().size());
        Assertions.assertThat(distinctCards.size()).isEqualTo(52);
    }

    @DisplayName("더 이상 뽑을 카드가 없는데 드로우할 경우 예외가 발생한다.")
    @Test
    void 카드가_없는데_드로우할_경우_예외가_발생한다() {
        Deck deck = new Deck(new ShuffledCardGenerator());
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        Assertions.assertThatThrownBy(deck::drawCard)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(DECK_IS_EMPTY.getMessage());
    }
}
