package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private static Deck deck;

    @BeforeEach
    void setUp() {
        deck = Deck.makeBlackjackDeck();
    }

    @DisplayName("카드 중복생성 안되는지 테스트")
    @Test
    void duplicatedCardTest() {
        List<Card> cards = new ArrayList<>();
        boolean duplicatedCardExist = false;
        for (int i = 0; i<BlackjackCardType.values().length; ++i) {
            Card card = deck.randomPick(new RandomNumberGenerator());
            duplicatedCardExist |= cards.contains(card);
            cards.add(card);
        }
        assertThat(duplicatedCardExist).isFalse();
    }

    @DisplayName("덱이 비었을때 카드 뽑으면 예외 발생하는지 테스트")
    @Test
    void validateDeckIsEmpty() {
        for (int i = 0; i<BlackjackCardType.values().length; ++i) {
            deck.randomPick(new RandomNumberGenerator());
        }
        assertThatThrownBy(() -> deck.randomPick(new RandomNumberGenerator()))
                .isInstanceOf(RuntimeException.class);
    }
}
