package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardDeckTest {

    @Test
    @DisplayName("카드덱 객체가 잘 생성되는 지")
    void newCardDeck() {

        // given
        // when
        final List<Card> cards = CardDeck.getCards();

        // then
        Assertions.assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("랜덤 값 추출")
    void getRandomCard() {
        //given
        int amount = 2;
        //when
        List<Card> cards = CardDeck.pickCard(amount);
        //then
        Assertions.assertThat(cards.size()).isEqualTo(amount);

    }
}