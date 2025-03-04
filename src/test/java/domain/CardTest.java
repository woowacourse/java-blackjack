package domain;

import domain.card.Card;
import domain.card.CardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource(value = {"1, SPADE", "2, CLOVER", "3, HEART", "4, DIAMOND"})
    void 카드_생성_테스트(int number, CardType cardType) {
        //when
        Card card = new Card(number, cardType);

        //then
        Assertions.assertThat(card).isInstanceOf(Card.class);
    }
}
