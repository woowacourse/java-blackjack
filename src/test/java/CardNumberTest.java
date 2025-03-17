import domain.CardNumber;
import domain.CardShape;
import domain.GameManager;
import domain.TrumpCard;
import domain.TrumpCardManager;
import domain.user.Betting;
import domain.user.User;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardNumberTest {
    @DisplayName("뽑은 카드의 숫자는 1이상 13 이하이다.")
    @Test
    void test() {

        // given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬"), List.of(new Betting(11000L)),
                new TrumpCardManager());
        User user = gameManager.findPlayerByUsername("레몬");
        gameManager.drawMoreCard(user);

        // when
        List<TrumpCard> trumpCards = user.openCard();
        int cardNumberValue = trumpCards.getFirst().getCardNumberValue();

        // then
        Assertions.assertThat(cardNumberValue).isBetween(1, 13);
    }

    @DisplayName("카드 출력시 해당 카드의 출력용 이름을 보여준다")
    @Test
    void getDisplayNameTest() {
        //given
        TrumpCard trumpCard1 = new TrumpCard(CardShape.CLOVER, CardNumber.J);
        TrumpCard trumpCard2 = new TrumpCard(CardShape.CLOVER, CardNumber.FIVE);
        TrumpCard trumpCard3 = new TrumpCard(CardShape.CLOVER, CardNumber.ACE);

        //when
        String displayName1 = CardNumber.numberToText(trumpCard1.getCardNumber());
        String displayName2 = CardNumber.numberToText(trumpCard2.getCardNumber());
        String displayName3 = CardNumber.numberToText(trumpCard3.getCardNumber());

        //then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(displayName1).isEqualTo("J");
            softAssertions.assertThat(displayName2).isEqualTo("5");
            softAssertions.assertThat(displayName3).isEqualTo("A");
        });

    }
}
