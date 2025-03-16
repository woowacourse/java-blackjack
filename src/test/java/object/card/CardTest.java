package object.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource(value = {"ACE, SPADE", "TWO, CLOVER", "THREE, HEART", "FOUR, DIAMOND"})
    void 카드_생성_테스트(CardNumber number, CardSymbol cardType) {
        //when
        Card card = new Card(number, cardType);

        //then
        Assertions.assertThat(card).isInstanceOf(Card.class);
    }
}
