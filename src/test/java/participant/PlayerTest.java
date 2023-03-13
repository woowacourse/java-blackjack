package participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.TestDataGenerator;
import domain.card.Card;
import domain.card.Cards;
import domain.money.BetAmount;
import domain.participant.Participant;
import domain.participant.ParticipantName;
import domain.participant.Player;
import domain.card.TrumpCardNumber;
import domain.card.TrumpCardType;
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
        player.start(Cards.of(HEART_THREE, HEART_TEN));
        player.receive(HEART_QUEEN);

        assertThat(player.isAbleToReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 초기 카드 두장 모두 공개한다.")
    @Test
    void getInitialCardsTest() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        player.start(Cards.of(HEART_QUEEN, HEART_TEN));

        Cards initialOpeningCards = player.getInitialOpeningCards();
        assertThat(initialOpeningCards.getCards()).hasSize(2);
    }


    @DisplayName("플레이어의 이름은 \"딜러\"일 수 없다.")
    @Test
    void createPlayerNamesFailTestWithExistedName() {
        ParticipantName dealerName = ParticipantName.getDealerName();
        assertThatThrownBy(() -> Player.of(dealerName, BetAmount.from(1000)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
