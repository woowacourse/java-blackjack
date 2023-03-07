import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Participant;
import domain.Result;
import domain.TrumpCardNumber;
import domain.TrumpCardType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    private static final Card CLUB_ACE = new Card(TrumpCardType.CLUB, TrumpCardNumber.ACE);
    private static final Card HEART_THREE = new Card(TrumpCardType.HEART, TrumpCardNumber.THREE);
    private static final Card HEART_TEN = new Card(TrumpCardType.HEART, TrumpCardNumber.TEN);
    private static final Card HEART_ACE = new Card(TrumpCardType.HEART, TrumpCardNumber.ACE);
    private static final int BUST_LIMIT_SCORE = 21;
    private static final Card HEART_QUEEN = new Card(TrumpCardType.HEART, TrumpCardNumber.QUEEN);

    @DisplayName("플레이어는 카드를 받을 수 있다.")
    @Test
    void receiveCardSuccessTest() {
        Participant player = TestDataManager.getPlayerWithName("pobi");
        player.receive(HEART_ACE);

        assertThat(player.getCards())
                .hasSize(1);
    }

    @DisplayName("플레이어가 받은 카드의 점수를 확인할 수 있다.")
    @Test
    void calculateScoreSuccessTest() {
        Participant player = TestDataManager.getPlayerWithName("pobi");
        List<Card> cards = List.of(CLUB_ACE, HEART_THREE);
        receiveCards(player, cards);
        int sumOfSimpleScore = sumSimpleScore(cards);

        assertThat(player.calculateBlackjackScore())
                .isEqualTo(sumOfSimpleScore);
    }

    @DisplayName("21을 넘은 경우 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAce() {
        Participant player = TestDataManager.getPlayerWithName("pobi");
        List<Card> cards = List.of(CLUB_ACE, HEART_THREE, HEART_TEN);
        receiveCards(player, cards);

        int sumSimpleScore = sumSimpleScore(cards);
        assertThat(sumSimpleScore).isGreaterThan(BUST_LIMIT_SCORE);
        sumSimpleScore -= (TrumpCardNumber.getAceGap() * countAceInPlayerCards(player));

        assertThat(player.calculateBlackjackScore())
                .isEqualTo(sumSimpleScore);
    }

    @DisplayName("21을 넘지 않는 경우 까지 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAceUntilNotBusted() {
        Participant player = TestDataManager.getPlayerWithName("pobi");
        List<Card> cards = List.of(CLUB_ACE, HEART_ACE, HEART_TEN);
        receiveCards(player, cards);

        int sumSimpleScore = sumSimpleScore(cards);
        assertThat(sumSimpleScore).isGreaterThan(BUST_LIMIT_SCORE);
        sumSimpleScore -= (TrumpCardNumber.getAceGap() * countAceInPlayerCards(player));

        assertThat(player.calculateBlackjackScore())
                .isEqualTo(sumSimpleScore);
    }

    @DisplayName("카드의 합이 21이 넘으면 버스트 된다.")
    @Test
    void isBustedSuccessTest() {
        Participant player = TestDataManager.getPlayerWithName("pobi");
        receiveCards(player, List.of(HEART_THREE, HEART_TEN, HEART_QUEEN));

        assertThat(player.calculateBlackjackScore()).isGreaterThan(BUST_LIMIT_SCORE);
        assertThat(player.isBusted()).isTrue();
    }

    @DisplayName("카드의 합이 21이 넘지 않으면 버스트 되지 않는다.")
    @Test
    void isNotBustedSuccessTest() {
        Participant player = TestDataManager.getPlayerWithName("pobi");
        receiveCards(player, List.of(HEART_TEN, HEART_QUEEN));

        assertThat(player.calculateBlackjackScore()).isLessThan(BUST_LIMIT_SCORE);
        assertThat(player.isBusted()).isFalse();
    }

    private int countAceInPlayerCards(Participant player) {
        return (int) player.getCards().stream()
                .map(Card::getNumberSignature)
                .filter(c -> c.equals("A"))
                .count();
    }

    private void receiveCards(Participant player, List<Card> cards) {
        for (Card card : cards) {
            player.receive(card);
        }
    }

    private int sumSimpleScore(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    @DisplayName("게임 점수가 높으면 승리한다.")
    @Test
    void competeWithWinTest() {
        Participant pobi = TestDataManager.getPlayerWithName("pobi");
        Participant crong = TestDataManager.getPlayerWithName("crong");

        receiveCards(pobi, List.of(HEART_TEN, HEART_ACE));
        receiveCards(crong, List.of(HEART_THREE, HEART_QUEEN));

        Result pobiResult = pobi.competeWith(crong);
        assertThat(pobiResult).isEqualTo(Result.WIN);
    }

    @DisplayName("게임 점수와 관계없이 버스트 된 경우 패배한다.")
    @Test
    void competeWithLoseWhenBustedTest() {
        Participant pobi = TestDataManager.getPlayerWithName("pobi");
        Participant crong = TestDataManager.getPlayerWithName("crong");

        receiveCards(pobi, List.of(HEART_TEN, HEART_THREE, HEART_QUEEN));
        receiveCards(crong, List.of(HEART_ACE, CLUB_ACE));

        assertThat(pobi.isBusted()).isTrue();
        Result pobiResult = pobi.competeWith(crong);
        assertThat(pobiResult).isEqualTo(Result.LOSE);
    }

}
