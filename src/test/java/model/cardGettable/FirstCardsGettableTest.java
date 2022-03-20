package model.cardGettable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardFace;
import model.card.CardSuit;
import model.card.cardGettable.FirstCardsGettable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FirstCardsGettableTest {
    private FirstCardsGettable firstCardsGettable;

    @BeforeEach
    void setUp() {
        firstCardsGettable = new FirstCardsGettable();
    }

    @Test
    void getFirstCard() {
        List<Card> cards = firstCardsGettable.getCards(List.of(new Card(CardSuit.SPADE, CardFace.ACE)));
        assertThat(cards).contains(new Card(CardSuit.SPADE, CardFace.ACE));
    }

    @Test
    void getEmptyCards() {
        assertThatThrownBy(() -> firstCardsGettable.getCards(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보유한 카드가 없습니다.");
    }
}