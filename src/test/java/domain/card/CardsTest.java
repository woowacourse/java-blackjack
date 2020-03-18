package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CardsTest {

    @Test
    @DisplayName("#of() : should return cards with cards which size is bigger than MIN_SIZE")
    void ofValidSize() {
        List<Card> validCards = Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER));
        assertThat(Cards.of(validCards)).isNotNull();
    }

    @Test
    @DisplayName("#of() : should throw IllegalArguemntException with cards which size is smaller than MIN_SIZE")
    void ofInvalidSize() {
        List<Card> invalidCards = Collections.emptyList();
        assertThatThrownBy(() -> Cards.of(invalidCards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Cards.INVALID_SIZE_MESSAGE);
    }

    @Test
    @DisplayName("#add() : should add Card")
    void addCard() {
        //given
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.QUEEN, Type.SPADE);
        Cards cards = Cards.of(new ArrayList<>(Collections.singletonList(card1)));
        //when
        Cards actual = cards.add(card2);
        //then
        assertThat(actual).isEqualTo(Cards.of(Arrays.asList(card1, card2)));
    }

    @Test
    @DisplayName("#add() : should add Cards")
    void addCards() {
        //given
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.QUEEN, Type.SPADE);
        Card card3 = new Card(Symbol.QUEEN, Type.DIAMOND);
        Cards cards = Cards.of(new ArrayList<>(Collections.singletonList(card1)));
        Cards cardsToAdd = Cards.of(Arrays.asList(card2, card3));
        //when
        Cards actual = cards.add(cardsToAdd);
        assertThat(actual).isEqualTo(Cards.of(Arrays.asList(card1, card2, card3)));
    }
}