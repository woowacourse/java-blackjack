package domain.cards;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @DisplayName("카드가 모두 소진됐는지 확인한다.")
    @Test
    void allCardsUsedTest() {
        List<Card> randomCards = CardsGenerator.generateRandomCards();
        Deck deck = new Deck(randomCards);
        for (int i = 0; i < 52; i++) {
            deck.pickOneCard();
        }
        assertThat(deck.isUsedAll()).isTrue();
    }
}
