import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Cards;
import domain.Participant;
import domain.TrumpCardNumber;
import domain.TrumpCardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private static final Card CLUB_ACE = new Card(TrumpCardType.CLUB, TrumpCardNumber.ACE);
    private static final Card HEART_THREE = new Card(TrumpCardType.HEART, TrumpCardNumber.THREE);
    private static final Card HEART_TEN = new Card(TrumpCardType.HEART, TrumpCardNumber.TEN);
    private static final Card HEART_QUEEN = new Card(TrumpCardType.HEART, TrumpCardNumber.QUEEN);

    @DisplayName("딜러는 17보다 낮은 경우 카드를 받을 수 있다.")
    @Test
    void isAbleToReceiveCardWhenUnderMoreCardLimitTest() {
        Participant dealer = TestDataManager.getDealer();
        dealer.receive(Cards.of(CLUB_ACE, HEART_THREE));

        assertThat(dealer.isAbleToReceiveCard()).isTrue();
    }

    @DisplayName("딜러는 17이상인 경우 카드를 받을 수 없다.")
    @Test
    void isAbleToReceiveCardWhenOverMoreCardLimitTest() {
        Participant dealer = TestDataManager.getDealer();
        dealer.receive(HEART_QUEEN);
        dealer.receive(HEART_TEN);

        assertThat(dealer.isAbleToReceiveCard()).isFalse();
    }

    @DisplayName("딜러는 초기 카드를 한 장만 공개한다.")
    @Test
    void getInitialCardsTest() {
        Participant dealer = TestDataManager.getDealer();
        dealer.receive(HEART_QUEEN);
        dealer.receive(HEART_TEN);

        assertThat(dealer.getInitialCards()).hasSize(1);
    }
}
