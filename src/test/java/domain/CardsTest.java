package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardsTest {

    @Test
    @DisplayName("카드 합계를 계산합니다.")
    void calculate_card_score() {
        Card card1 = new Card(Shape.from("스페이드"), Number.from(8));
        Card card2 = new Card(Shape.from("하트"), Number.from(8));

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        Cards realCards = new Cards(cards);
        assertEquals(16, realCards.calculateScore());
    }

    @Test
    @DisplayName("Ace가 2개일 경우")
    void two_ace() {
        Card card1 = new Card(Shape.from("스페이드"), Number.from(11));
        Card card2 = new Card(Shape.from("하트"), Number.from(11));
        List<Card> cards = new ArrayList<>();
        Cards realCards = new Cards(cards);

        realCards.addCard(card1);
        realCards.addCard(card2);

        assertEquals(true, realCards.canReceiveCard(21));
    }

    @Test
    @DisplayName("King,9,Ace,Ace일경우")
    void king_nine_two_ace() {
        Card card1 = new Card(Shape.from("스페이드"), Number.from(10));
        Card card2 = new Card(Shape.from("하트"), Number.from(9));
        Card card3 = new Card(Shape.from("스페이드"), Number.from(11));
        Card card4 = new Card(Shape.from("하트"), Number.from(11));

        List<Card> cards = new ArrayList<>();
        Cards realCards = new Cards(cards);

        realCards.addCard(card1);
        realCards.addCard(card2);
        realCards.addCard(card3);
        realCards.addCard(card4);

        assertEquals(false, realCards.canReceiveCard(21));
    }

    @Test
    @DisplayName("King,King,King 일경우")
    void three_king() {
        Card card1 = new Card(Shape.from("스페이드"), Number.from(10));
        Card card2 = new Card(Shape.from("하트"), Number.from(10));
        Card card3 = new Card(Shape.from("스페이드"), Number.from(10));

        List<Card> cards = new ArrayList<>();
        Cards realCards = new Cards(cards);

        realCards.addCard(card1);
        realCards.addCard(card2);
        realCards.addCard(card3);

        assertEquals(false, realCards.canReceiveCard(21));
    }
}
