package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardTest {

    @Test
    @DisplayName("카드 정상 생성 테스트")
    void validCardTest() {
        for(CardNumber cardNumber : CardNumber.values()){
            assertDoesNotThrow(() -> new Card(Shape.DIAMOND, cardNumber));
        }
    }

    @Test
    @DisplayName("")
    void Test() {}

}
