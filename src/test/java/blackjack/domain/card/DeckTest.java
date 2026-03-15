package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱에서 카드 한 장을 뽑으면 덱의 남은 카드 수가 감소한다.")
    void drawCard_DecreaseDeckSize() {
        Deck deck = new Deck();
        deck.drawCard();
        assertThat(deck.drawCards(51)).hasSize(51);
    }

    @Test
    @DisplayName("지정한 개수만큼 덱에서 카드를 뽑아 반환한다.")
    void drawCards_ReturnSpecifiedCountOfCards() {
        Deck deck = new Deck();
        List<Card> drawnCards = deck.drawCards(2);
        assertThat(drawnCards).hasSize(2);
    }

    @Test
    @DisplayName("덱에 남은 카드보다 많은 카드를 뽑으려 하면 예외가 발생한다.")
    void drawCards_ThrowException_WhenCountIsGreaterThanRemainingCards() {
        Deck deck = new Deck();
        deck.drawCards(52);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> deck.drawCards(1))
                .withMessageContaining("남은 카드가 뽑을 카드보다 적습니다");
    }
}
