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

    @DisplayName("Ace 카드를 포함하고 있고, 카드 합계가 21이 넘은 경우 총합에서 -10을 한다.")
    @Test
    public void containsAce() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.SPACE, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)));

        assertThat(cards.calculateTotalValue()).isEqualTo(19);
    }

    @DisplayName("카드들에 ACE 카드가 포함되는지 확인한다.")
    @Test
    void contains() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.QUEEN),
                new Card(Shape.SPACE, Value.ACE)));

        assertThat(cards.containAce()).isTrue();
    }

    @DisplayName("카드들을 하나의 객체로 합친다.")
    @Test
    void combineCards() {
        Cards cards = Deck.popTwo();
        Cards otherCards = Deck.popTwo();
        cards.combine(otherCards);

        assertThat(cards.cards()).hasSize(4);
    }

    @DisplayName("카드의 총합이 버스트인 경우를 확인한다.")
    @Test
    void isBustTrue() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.QUEEN),
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.SPACE, Value.JACK)));

        assertThat(cards.isBust()).isTrue();
    }

    @DisplayName("카드의 총합이 버스트가 아닌 경우를 확인한다.")
    @Test
    void isBustFalse() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.QUEEN),
                new Card(Shape.SPACE, Value.ACE)));

        assertThat(cards.isBust()).isFalse();
    }
}
