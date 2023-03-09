import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Card;
import domain.Cards;
import domain.Participant;
import domain.ParticipantName;
import domain.Player;
import domain.TrumpCardNumber;
import domain.TrumpCardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private static final Card HEART_THREE = new Card(TrumpCardType.HEART, TrumpCardNumber.THREE);
    private static final Card HEART_TEN = new Card(TrumpCardType.HEART, TrumpCardNumber.TEN);
    private static final Card HEART_QUEEN = new Card(TrumpCardType.HEART, TrumpCardNumber.QUEEN);

    @DisplayName("플레이어는 버스트된 경우 카드를 받을 수 없다.")
    @Test
    void isAbleToReceiveCardWhenUnderMoreCardLimitTest() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        player.receive(HEART_THREE);
        player.receive(HEART_TEN);
        player.receive(HEART_QUEEN);

        assertThat(player.isAbleToReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 초기 카드 두장 모두 공개한다.")
    @Test
    void getInitialCardsTest() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        player.receive(HEART_QUEEN);
        player.receive(HEART_TEN);

        Cards initialOpeningCards = player.getInitialOpeningCards();
        assertThat(initialOpeningCards.getCards()).hasSize(2);
    }


    @DisplayName("플레이어의 이름은 \"딜러\"일 수 없다.")
    @Test
    void createPlayerNamesFailTestWithExistedName() {
        ParticipantName dealerName = ParticipantName.getDealerName();
        assertThatThrownBy(() -> Player.from(dealerName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
