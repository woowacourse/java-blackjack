package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {


    @Test
    @DisplayName("카드덱 객체가 잘 생성되는 지")
    void newCardDeck() {

        // given
        // when
        final List<String> cards = CardDeck.getCards();

        // then
        Assertions.assertThat(cards.size()).isEqualTo(52);
    }
}