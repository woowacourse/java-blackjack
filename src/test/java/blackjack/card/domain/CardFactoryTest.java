package blackjack.card.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardFactoryTest {

    @DisplayName("전체 카드 갯수는 52장이다.")
    @Test
    void getCards() {
        //given
        CardFactory cardFactory = new CardFactory();

        //when
        List<Card> cards = cardFactory.getCards();

        //then
        assertThat(cards).hasSize(52);
    }
}