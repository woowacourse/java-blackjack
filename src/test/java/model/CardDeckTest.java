package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("랜덤 값 추출")
    void getRandomCard() {
        //given
        int amount = 2;
        //when
        List<Card> cards = deck.pickCard(amount);
        //then
        Assertions.assertThat(cards.size()).isEqualTo(amount);
    }
}
