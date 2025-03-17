package view;

import domain.CardNumber;
import domain.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardConverterTest {

    @DisplayName("카드 모양과 숫자를 조합한다.")
    @Test
    void createTrumpCardTest() {
        
        //given
        CardShape cardShape = CardShape.DIA;
        CardNumber cardNumber = CardNumber.ACE;

        //when
        String trumpCard = CardConverter.createTrumpCard(cardShape, cardNumber);

        //then
        Assertions.assertThat(trumpCard).isEqualTo("A다이아몬드");
    }
}
