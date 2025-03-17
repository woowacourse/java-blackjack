import domain.CardNumber;
import domain.CardShape;
import domain.TrumpCard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrumpCardTest {

    //given
    TrumpCard trumpCard = new TrumpCard(CardShape.CLOVER, CardNumber.J);

    @DisplayName("카드의 모양을 가져온다.")
    @Test
    void getCardShapeTest() {

        //when
        CardShape cardShape = trumpCard.getCardShape();

        //then
        Assertions.assertThat(cardShape).isEqualTo(CardShape.CLOVER);
    }

    @DisplayName("카드의 숫자를 가져온다.")
    @Test
    void getCardNumberTest() {

        //when
        CardNumber cardNumber = trumpCard.getCardNumber();

        //then
        Assertions.assertThat(cardNumber).isEqualTo(CardNumber.J);
    }
}
