package model.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("Deck에서 카드를 한 장 뽑는다.")
    void makeDeck() {
        //given
        Card firstCard = new Card(CardRank.FOUR, CardSuit.CLOVER);
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(
                firstCard
        )));

        //when
        Card card = deck.pick();

        //then
        assertThat(card).isEqualTo(firstCard);
    }

    @Test
    @DisplayName("Deck에서 뽑을 카드가 없는 경우 예외 발생.")
    void Deck에서_뽑을_카드가_없는_경우() {
        //given
        Deck deck = new Deck(new ArrayList<>(0));

        //when, then
        assertThatThrownBy(() -> deck.pick()).isInstanceOf(IllegalStateException.class);
    }
}
