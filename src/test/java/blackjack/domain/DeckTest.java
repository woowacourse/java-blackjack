package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("Deck에서 카드를 한장 빼서 반환한다.")
    void pickCardFromDeck() {
        Deck deck = new Deck();

        Card card = deck.pickCard();

        assertThat(card).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("Deck에 카드가 남아있지 않을 시 오류 발생")
    void pickCardFail() {
        Deck deck = new Deck();
        for (int i = 0; i <52; i++) {
            Card card = deck.pickCard();
        }

        assertThatThrownBy(deck::pickCard).isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 카드를 모두 사용하였습니다.");
    }

}
