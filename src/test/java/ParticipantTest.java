import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackScore;
import domain.Card;
import domain.Cards;
import domain.Participant;
import domain.TrumpCardNumber;
import domain.TrumpCardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    private static final Card CLUB_ACE = new Card(TrumpCardType.CLUB, TrumpCardNumber.ACE);
    private static final Card HEART_THREE = new Card(TrumpCardType.HEART, TrumpCardNumber.THREE);
    private static final Card HEART_TEN = new Card(TrumpCardType.HEART, TrumpCardNumber.TEN);
    private static final Card HEART_ACE = new Card(TrumpCardType.HEART, TrumpCardNumber.ACE);
    private static final Card HEART_QUEEN = new Card(TrumpCardType.HEART, TrumpCardNumber.QUEEN);

    @DisplayName("플레이어가 받은 카드의 점수를 확인할 수 있다.")
    @Test
    void calculateScoreSuccessTest() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        Cards cards = Cards.of(CLUB_ACE, HEART_THREE);
        player.receive(cards);

        BlackjackScore expectedScore = BlackjackScore.from(14);
        assertThat(player.calculateBlackjackScore())
                .isEqualTo(expectedScore);
    }

    @DisplayName("21을 넘은 경우 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAce() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        Cards cards = Cards.of(CLUB_ACE, HEART_THREE, HEART_TEN);
        assertThat(CLUB_ACE.getScore() + HEART_THREE.getScore() + HEART_TEN.getScore())
                .isGreaterThan(21);
        player.receive(cards);

        BlackjackScore expectedScore = BlackjackScore.from(14);
        assertThat(player.calculateBlackjackScore())
                .isEqualTo(expectedScore);
    }

    @DisplayName("21을 넘지 않는 경우 까지 ACE는 11로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAceUntilNotBusted() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        Cards cards = Cards.of(CLUB_ACE, HEART_TEN);
        player.receive(cards);
        assertThat(player.calculateBlackjackScore())
                .isEqualTo(BlackjackScore.from(21));

        player.receive(HEART_ACE);
        assertThat(player.calculateBlackjackScore())
                .isEqualTo(BlackjackScore.from(12));
    }

    @DisplayName("카드의 합이 21이 넘으면 버스트 된다.")
    @Test
    void isBustedSuccessTest() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        Cards cards = Cards.of(HEART_THREE, HEART_TEN, HEART_QUEEN);
        player.receive(cards);

        BlackjackScore playerScore = player.calculateBlackjackScore();

        boolean isGreaterThanMaxScore = playerScore.isGreaterThan(BlackjackScore.getMaxScore());
        assertThat(isGreaterThanMaxScore).isTrue();
        assertThat(player.isBusted()).isTrue();
    }

    @DisplayName("카드의 합이 21이 넘지 않으면 버스트 되지 않는다.")
    @Test
    void isNotBustedSuccessTest() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        Cards cards = Cards.of(HEART_TEN, HEART_QUEEN);
        player.receive(cards);

        BlackjackScore playerScore = player.calculateBlackjackScore();

        boolean isGreaterThanMaxScore = playerScore.isGreaterThan(BlackjackScore.getMaxScore());

        assertThat(isGreaterThanMaxScore).isFalse();
        assertThat(player.isBusted()).isFalse();
    }

}
