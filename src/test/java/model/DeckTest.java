package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void makeDeck() {
        //given
        Card firstCard = new Card(CardRank.FOUR, CardSuit.CLOVER);
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(
                firstCard
        )));

        //when
        Card card = deck.pick();

        assertThat(card).isEqualTo(firstCard);
    }
}
