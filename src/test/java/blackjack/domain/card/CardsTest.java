package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class CardsTest {
    @Test
    void create() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.create(Suit.CLUB, Denomination.KING));
        cardList.add(Card.create(Suit.CLUB, Denomination.QUEEN));

        assertThatCode(() -> {
            Cards cards = new Cards();
            cards.of(cardList);
        }).doesNotThrowAnyException();
    }

    @Test
    void containsAce() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.create(Suit.CLUB, Denomination.ACE));
        cardList.add(Card.create(Suit.CLUB, Denomination.QUEEN));
        Cards cards = new Cards();
        cards.of(cardList);

        assertTrue(cards.containsAce());
    }

    @Test
    void sumWithoutAce() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.create(Suit.CLUB, Denomination.ACE));
        cardList.add(Card.create(Suit.CLUB, Denomination.QUEEN));
        Cards cards = new Cards();
        cards.of(cardList);

        assertThat(cards.sumWithoutAce()).isEqualTo(10);
    }

    @Test
    void getCardsWithSize() {
        List<Card> cardList = new ArrayList<>();
        final Card card = Card.create(Suit.CLUB, Denomination.ACE);
        cardList.add(card);
        cardList.add(Card.create(Suit.CLUB, Denomination.QUEEN));
        Cards cards = new Cards();
        cards.of(cardList);

        assertThat(cards.getCardsWithSize(1))
                .contains(card);
    }

    @Test
    void isBlackjack() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.create(Suit.CLUB, Denomination.ACE));
        cardList.add(Card.create(Suit.CLUB, Denomination.QUEEN));
        Cards cards = new Cards();
        cards.of(cardList);

        assertTrue(cards.isBlackjack());
    }

    @Test
    void calculate() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.create(Suit.CLUB, Denomination.ACE));
        cardList.add(Card.create(Suit.CLUB, Denomination.QUEEN));
        Cards cards = new Cards();
        cards.of(cardList);

        assertThat(cards.calculate()).isEqualTo(21);
    }
}