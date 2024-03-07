import domain.Card;
import domain.Dealer;
import domain.constants.CardValue;
import domain.constants.Shape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("딜러가 가지고 있는 카드가 16 초과이면 참을 반환한다.")
    @Test
    void isUpToThreshold() {
        int threshold = 16;
        Dealer dealer = new Dealer("딜러");
        dealer.saveCard(new Card(CardValue.TEN, Shape.CLOVER));
        dealer.saveCard(new Card(CardValue.SEVEN, Shape.CLOVER));

        boolean isUp = dealer.isUpToThreshold(threshold);
        Assertions.assertTrue(isUp);
    }
}
