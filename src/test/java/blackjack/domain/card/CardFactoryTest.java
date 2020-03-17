package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardFactoryTest {

    @DisplayName("전체 카드 갯수는 52장이다.")
    @Test
    void getCards() {
        //given
        CardFactory cardFactory = new CardFactory();

        //when
        List<Card> cards = cardFactory.getCards();

        //then
        Assertions.assertThat(cards).hasSize(52);
    }
}