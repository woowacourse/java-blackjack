package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    private Deck deck;
    private Cards cards;

    public void setUp() {
        deck = new Deck();
        cards = deck.popTwo();
    }

    @DisplayName("Cards 객체를 생성한다.")
    @Test
    public void createCards() {
        setUp();

        assertThat(cards).isInstanceOf(Cards.class);
    }

    @DisplayName("Card 객체 하나를 보여준다.")
    @Test
    public void oneCard() {
        setUp();

        assertThat(cards.oneCard()).isInstanceOf(Card.class);
    }

    @DisplayName("카드 합계를 구한다.")
    @Test
    public void calculateScore() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        ));

        assertThat(cards.calculateScore()).isEqualTo(18);
    }

    @DisplayName("Ace 카드를 포함하고 있고, 카드 합계가 21이 넘은 경우 총합에서 -10을 한다.")
    @Test
    public void containsAce() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.SPACE, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)));

        assertThat(cards.calculateScore()).isEqualTo(19);
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
        setUp();
        cards.combine(deck.popTwo());

        assertThat(cards.getCards()).hasSize(4);
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
