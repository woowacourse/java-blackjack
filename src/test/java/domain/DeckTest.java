package domain;

import java.util.List;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱을 생성한다.")
    @Test
    void 덱을_생성한다() {

        // given
        final DeckGenerator deckGenerator = new DeckGenerator();

        // when & then
        Assertions.assertThatCode(() -> new Deck(deckGenerator.generate()))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 셔플한다.")
    @Test
    void 카드를_셔플한다() {

        // given
        final DeckGenerator deckGenerator = new DeckGenerator();
        Deck deck = new Deck(deckGenerator.generate());
        List<Card> previousCards = List.copyOf(deck.getCards());
        final Random random = new Random(123);

        // when
        deck.shuffle(random);
        List<Card> cards = deck.getCards();

        // then
        Assertions.assertThat(previousCards).isNotEqualTo(cards);
    }
}
