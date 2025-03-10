import domain.CardNumber;
import domain.CardShape;
import domain.TrumpCard;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardNumberTest {
    @DisplayName("열 세개의 숫자 중 하나의 숫자를 뽑는다.")
    @Test
    void test() {
        // given
        int oneIndex = 13;

        // when
        CardNumber cardNumber = CardNumber.pick(oneIndex);

        // then
        Assertions.assertThat(cardNumber).isEqualTo(CardNumber.K);
    }

    @DisplayName("카드 출력시 해당 카드의 출력용 이름을 보여준다")
    @Test
    void getDisplayNameTest() {
        //given
        TrumpCard trumpCard1 = new TrumpCard(CardShape.CLOVER, CardNumber.J);
        TrumpCard trumpCard2= new TrumpCard(CardShape.CLOVER, CardNumber.FIVE);
        TrumpCard trumpCard3= new TrumpCard(CardShape.CLOVER, CardNumber.ACE);

        //when
        String displayName1 = trumpCard1.getCardNumber().getDisplayName();
        String displayName2 = trumpCard2.getCardNumber().getDisplayName();
        String displayName3 = trumpCard3.getCardNumber().getDisplayName();
        //then

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(displayName1).isEqualTo("J");
            softAssertions.assertThat(displayName2).isEqualTo("5");
            softAssertions.assertThat(displayName3).isEqualTo("A");
        });

    }
}
