package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class DeckInitializerTest {

    @Test
    void 일반카드셋을_생성한다() {
        // given
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        List<Card> normalCards = deckInitializer.generateNormalCards();
        // then
        assertThat(normalCards.size()).isEqualTo(36);
    }

    @Test
    void 스페셜카드셋을_생성한다() {
        // given
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        List<Card> specialCards = deckInitializer.generateSpecialCards();
        // then
        assertThat(specialCards.size()).isEqualTo(12);
    }

    @Test
    void 에이스카드셋을_생성한다() {
        // given
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        List<Card> aceCards = deckInitializer.generateAceCards();
        // then
        assertThat(aceCards.size()).isEqualTo(4);
    }

    @Test
    void 모든_카드가_존재하는_덱을_생성한다() {
        // given
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        Deck deck = deckInitializer.generateDeck();
        // then
        assertThat(deck.getCardCount()).isEqualTo(52);
    }
}
