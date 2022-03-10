package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private static Deck deck;

    @BeforeAll
    static void setUp() {
        deck = Deck.generateDeck();
    }

    @DisplayName("카드 중복생성 안되는지 테스트")
    @Test
    void duplicatedCardTest() {
        List<Card> cards = new ArrayList<>();
        boolean duplicatedCardExist = false;
        for (int i = 0; i<48; ++i) {
            Card card = deck.randomPick(new RandomNumberGenerator());
            duplicatedCardExist |= cards.contains(card);
            cards.add(card);
        }
        assertThat(duplicatedCardExist).isFalse();
    }

    @DisplayName("카드 초과생성 방지기능 테스트")
    @Test
    void cardConstructOverLimitTest() {
        assertThatThrownBy(() -> deck.randomPick(new RandomNumberGenerator()))
                .isInstanceOf(RuntimeException.class);
    }
}
