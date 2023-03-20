package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.TestDataGenerator;
import domain.blackjack.Result;
import domain.card.Card;
import domain.card.Cards;
import domain.card.TrumpCardNumber;
import domain.card.TrumpCardType;
import domain.money.BetAmount;
import domain.money.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private static final Card HEART_THREE = new Card(TrumpCardType.HEART, TrumpCardNumber.THREE);
    private static final Card HEART_TEN = new Card(TrumpCardType.HEART, TrumpCardNumber.TEN);
    private static final Card HEART_QUEEN = new Card(TrumpCardType.HEART, TrumpCardNumber.QUEEN);
    private static final Card HEART_ACE = new Card(TrumpCardType.HEART, TrumpCardNumber.ACE);

    @DisplayName("플레이어는 버스트된 경우 카드를 받을 수 없다.")
    @Test
    void isAbleToReceiveCardWhenUnderMoreCardLimitTest() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        player.startWith(Cards.of(HEART_THREE, HEART_TEN));
        player.receive(HEART_QUEEN);

        assertThat(player.isAbleToReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 초기 카드 두장 모두 공개한다.")
    @Test
    void getInitialCardsTest() {
        Participant player = TestDataGenerator.getPlayerWithName("pobi");
        player.startWith(Cards.of(HEART_QUEEN, HEART_TEN));

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

    @DisplayName("플레이어는 승리하면 배팅 금액과 동일한 금액의 이익이 발생한다.")
    @Test
    void getProfitByResultWinTest() {
        Player player = TestDataGenerator.getPlayerWithNameAndBetAmount("pobi", 1000);
        player.startWith(Cards.of(HEART_QUEEN, HEART_TEN));

        Profit profitByResult = player.getProfitByResult(Result.WIN);
        assertThat(profitByResult.getAmount()).isEqualTo(1000);
    }

    @DisplayName("블랙잭으로 승리하면 배팅 금액의 1.5배의 이익이 발생한다.")
    @Test
    void getProfitByResultWinBlackjackTest() {
        Player player = TestDataGenerator.getPlayerWithNameAndBetAmount("pobi", 1000);
        player.startWith(Cards.of(HEART_QUEEN, HEART_ACE));

        Profit profitByResult = player.getProfitByResult(Result.WIN);
        assertThat(profitByResult.getAmount()).isEqualTo(1500);
    }

    @DisplayName("패배하면 배팅 금액 만큼의 손해가 발생한다.")
    @Test
    void getProfitByResultLoseTest() {
        Player player = TestDataGenerator.getPlayerWithNameAndBetAmount("pobi", 1000);
        player.startWith(Cards.of(HEART_QUEEN, HEART_TEN));

        Profit profitByResult = player.getProfitByResult(Result.LOSE);
        assertThat(profitByResult.getAmount()).isEqualTo(-1000);
    }

    @DisplayName("비기면 이익이 존재하지 않는다.")
    @Test
    void getProfitByResultDrawTest() {
        Player player = TestDataGenerator.getPlayerWithNameAndBetAmount("pobi", 1000);
        player.startWith(Cards.of(HEART_QUEEN, HEART_TEN));

        Profit profitByResult = player.getProfitByResult(Result.DRAW);
        assertThat(profitByResult.getAmount()).isEqualTo(0);
    }
}
