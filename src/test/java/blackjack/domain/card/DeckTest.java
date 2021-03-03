package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class DeckTest {
    @DisplayName("Deck 객체 생성")
    @Test
    void createCards() {
        assertThatCode(() -> Deck.create()).doesNotThrowAnyException();
    }


    @DisplayName("초기 나눠주는 Hands는 두 장의 카드로 이루어진다.")
    @Test
    public void giveFirstHands() {
        final Deck deck = Deck.create();
        assertThat(deck.giveFirstHand().toList().size()).isEqualTo(2);
    }
}
