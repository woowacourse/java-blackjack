package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @DisplayName("Card 객체 하나를 보여준다.")
    @Test
    public void oneCard() {
        Deck deck = new Deck();
        Cards cards = deck.popToInitialCards();

        Card card = cards.getOneCard();

        assertThat(card).isInstanceOf(Card.class);
    }

    @DisplayName("카드 합계를 구한다.")
    @Test
    public void calculateTotalCards() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        ));

        Score totalScore = cards.totalScore();
        int totalValue = totalScore.getValue();

        assertThat(totalValue).isEqualTo(18);
    }

    @DisplayName("Ace 카드를 포함하고 있고, 카드 합계가 21이 넘은 경우")
    @Test
    public void containsAce() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.KING)));

        Score totalScore = cards.totalScore();
        int totalValue = totalScore.getValue();

        assertThat(totalValue).isEqualTo(19);
    }

    @DisplayName("카드들에 ACE 카드가 포함되는지 확인한다.")
    @Test
    void contains() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.QUEEN),
                new Card(Suit.SPACE, Denomination.ACE)));

        boolean isSoftHand = cards.isSoftHand();

        assertThat(isSoftHand).isTrue();
    }

    @DisplayName("카드들을 하나의 객체로 합친다.")
    @Test
    void combineCards() {
        Deck deck = new Deck();
        Cards cards = deck.popToInitialCards();
        Cards otherCards = deck.popToInitialCards();
        cards.combine(otherCards);

        int cardCount = cards.getCards().size();

        assertThat(cardCount).isEqualTo(4);
    }

    @DisplayName("카드의 총합이 버스트인 경우를 확인한다.")
    @Test
    void isBustTrue() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.QUEEN),
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.SPACE, Denomination.JACK)));

        boolean isBust = cards.isBust();

        assertThat(isBust).isTrue();
    }

    @DisplayName("카드의 총합이 버스트가 아닌 경우를 확인한다.")
    @Test
    void isBustFalse() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.QUEEN),
                new Card(Suit.SPACE, Denomination.ACE)));

        boolean isBust = cards.isBust();

        assertThat(isBust).isFalse();
    }

    @Test
    @DisplayName("카드 한장을 더한다.")
    void add() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.QUEEN),
                new Card(Suit.SPACE, Denomination.ACE)));
        Card card = new Card(Suit.DIAMOND, Denomination.QUEEN);

        cards.add(card);
        int count = cards.getCards().size();

        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void isBlackjack() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.SPACE, Denomination.QUEEN)));

        boolean isBlackjack = cards.isBlackjack();

        assertThat(isBlackjack).isTrue();
    }
}
