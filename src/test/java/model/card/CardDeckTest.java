package model.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CardDeckTest {

    private CardDeck deck;

    @BeforeEach
    void beforeEach() {
        deck = new CardDeck();
    }

    @Test
    @DisplayName("카드덱 객체가 잘 생성되는 지")
    void newCardDeck() {
        // given
        // when
        // then
        Assertions.assertThat(deck.getDeck().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드 덱에서 카드가 잘 추출 되는지")
    void getRandomCard() {
        //given
        int amount = 2;
        //when
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(deck.pickCard());
        }
        //then
        Assertions.assertThat(cards.size()).isEqualTo(amount);
    }
}
