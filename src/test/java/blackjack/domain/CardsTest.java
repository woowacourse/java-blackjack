package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @DisplayName("Cards 객체를 생성한다.")
    @Test
    public void createCards() {
        Cards cards = Deck.popTwo();

        assertThat(cards).isInstanceOf(Cards.class);
    }

    @DisplayName("카드들을 하나의 객체로 합친다.")
    @Test
    void combineCards() {
        Cards cards = Deck.popTwo();
        Cards otherCards = Deck.popTwo();
        cards.combine(otherCards);

        assertThat(cards.cards().size()).isEqualTo(4);
    }
}
