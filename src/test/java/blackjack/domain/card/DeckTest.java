package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        this.deck = new Deck();
    }

    @Test
    @DisplayName("Deck을 새로 생성하면 52장의 카드가 존재한다.")
    void createTest() {
        assertThat(deck.getDeck().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("deck에 카드가 남아있지 않은 경우 예외가 발생한다.")
    void drawCard_empty_exception() {
        for (int i = 1; i <= 52; i++) {
            deck.drawCard();
        }

        assertThatIllegalStateException().isThrownBy(
                () -> deck.drawCard()
        ).withMessage("현재 남아있는 카드가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("deck에서 카드를 한 장 뽑는다.")
    void drawCardTest() {
        deck.drawCard();

        assertThat(deck.getDeck().size()).isEqualTo(51);
    }

    @Test
    @DisplayName("deck에서 두 장의 카드를 뽑는다.")
    void drawTwoCard() {
        List<Card> cards = deck.drawTwoCard();

        assertThat(cards.size()).isEqualTo(2);
    }
}
