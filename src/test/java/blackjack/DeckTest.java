package blackjack;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("뽑은 모든 카드가 중복이 없고, 52장이 맞는지 테스트")
    @Test
    void pickTopCardTest() {
        Deck deck = Deck.makeRandomShuffledDeck();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i<52; ++i) {
            cards.add(deck.pickTopCard());
        }
        Set<Card> noDuplicatedCards = new HashSet<>(cards);
        assertThat(cards.size() == noDuplicatedCards.size()).isTrue();
    }

    @DisplayName("덱이 비었을떄 뽑으면 익셉션 발생하는지 테스트")
    @Test
    void pickTopCardTest2() {
        Deck deck = Deck.makeRandomShuffledDeck();
        for (int i = 0; i<52; ++i) {
            deck.pickTopCard();
        }
        assertThatThrownBy(() -> deck.pickTopCard()).isInstanceOf(IllegalStateException.class);
    }
}
