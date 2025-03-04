package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
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

        Assertions.assertThat(card).isEqualTo(firstCard);
    }
}
