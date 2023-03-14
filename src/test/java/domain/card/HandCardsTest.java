package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandCardsTest {

    @Test
    @DisplayName("A가 1로 쓰이는 경우")
    void whenAceIsOne() {
        HandCards handCards = new HandCards();
        handCards.takeCard(new Card("A스페이드", 1));
        handCards.takeCard(new Card("10하트", 10));
        handCards.takeCard(new Card("8다이아몬드", 8));
        assertThat(handCards.calculateScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("A가 11로 쓰이는 경우")
    void whenAceIsEleven() {
        HandCards handCards = new HandCards();
        handCards.takeCard(new Card("A스페이드", 1));
        handCards.takeCard(new Card("10하트", 10));
        assertThat(handCards.calculateScore()).isEqualTo(21);
    }
}
