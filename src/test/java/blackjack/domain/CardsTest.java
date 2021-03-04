package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @DisplayName("Cards 객체를 생성한다.")
    @Test
    public void createCards() {
        Cards cards = Deck.popTwo();

        assertThat(cards).isInstanceOf(Cards.class);
    }

    @DisplayName("Card 객체 하나를 보여준다.")
    @Test
    public void oneCard() {
        Cards cards = Deck.popTwo();
        Card card = cards.oneCard();

        assertThat(card).isInstanceOf(Card.class);
    }

    @DisplayName("카드 합계를 구한다.")
    @Test
    public void calculateTotalCards() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        ));
        assertThat(cards.calculateTotalValue()).isEqualTo(18);
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
