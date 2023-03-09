package domain;

import domain.card.Card;
import domain.deck.ShuffledDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.*;

public class ShuffledDeckTest {
    private ShuffledDeck shuffledDeck;

    @BeforeEach
    void setting() {
        shuffledDeck = new ShuffledDeck();
    }

    @Test
    @DisplayName("덱에서 한 장 뽑으면 카드가 나온다.")
    void createDeck() {
        assertThat(shuffledDeck.drawCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드를 가져오면 해당 덱에서는 카드가 한장 사라진다.")
    void drawCardTest() {
        shuffledDeck.drawCard();
        assertThat(shuffledDeck).extracting("cards", collection(List.class))
                .size()
                .isSameAs(51);
    }

    @Test
    @DisplayName("Deck은 52개의 다른 카드를 반환한다")
    void returnDifferentCardTest() {
        final Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(shuffledDeck.drawCard());
        }
        assertThat(cards)
                .size()
                .isSameAs(52);
    }

    @Test
    @DisplayName("52개 이상을 덱에서 뽑으면 예외가 발생한다.")
    void drawExceptionTest() {
        for (int i = 0; i < 52; i++) {
            shuffledDeck.drawCard();
        }
        assertThatThrownBy(() -> shuffledDeck.drawCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱에 더이상 카드가 남아있지 않습니다.");
    }
}
