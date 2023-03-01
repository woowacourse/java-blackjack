package blackjack.domain;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    void setting(){
        deck = new Deck();
    }

    @Test
    @DisplayName("덱이 생성된다.")
    void createDeck() {
        final Deck deck = new Deck();
        assertThat(deck.drawCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드를 가져오면 해당 덱에서는 카드가 한장 사라진다.")
    void drawCardTest() {
        Card card = deck.drawCard();
        assertThat(deck).extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(51);

    }

    @Test
    @DisplayName("Deck은 52개의 다른 카드를 반환한다")
    void returnDifferentCardTest() {
        final HashSet<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(deck.drawCard());
        }
        assertThat(cards)
                .size()
                .isEqualTo(52);
    }

    @Test
    @DisplayName("52개 이상을 덱에서 뽑으면 예외가 발생한다.")
    void drawExceptionTest() {
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        assertThatThrownBy(() -> deck.drawCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드 업슝");
    }
}
