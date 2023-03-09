import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Result;
import domain.TrumpCardNumber;
import domain.TrumpCardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {
    private static final Card CLUB_ACE = new Card(TrumpCardType.CLUB, TrumpCardNumber.ACE);
    private static final Card HEART_THREE = new Card(TrumpCardType.HEART, TrumpCardNumber.THREE);
    private static final Card HEART_TEN = new Card(TrumpCardType.HEART, TrumpCardNumber.TEN);
    private static final Card CLUB_THREE = new Card(TrumpCardType.CLUB, TrumpCardNumber.THREE);
    private static final Card HEART_QUEEN = new Card(TrumpCardType.HEART, TrumpCardNumber.QUEEN);
    private static final Card SPADE_QUEEN = new Card(TrumpCardType.SPADE, TrumpCardNumber.QUEEN);
    private static final Card SPADE_KING = new Card(TrumpCardType.SPADE, TrumpCardNumber.KING);

    @DisplayName("딜러는 17보다 낮은 경우 카드를 받을 수 있다.")
    @Test
    void isAbleToReceiveCardWhenUnderMoreCardLimitTest() {
        Dealer dealer = TestDataGenerator.getDealer();
        Cards cards = Cards.of(CLUB_ACE, HEART_THREE);
        dealer.receive(cards);

        assertThat(dealer.isAbleToReceiveCard()).isTrue();
    }

    @DisplayName("딜러는 17이상인 경우 카드를 받을 수 없다.")
    @Test
    void isAbleToReceiveCardWhenOverMoreCardLimitTest() {
        Dealer dealer = TestDataGenerator.getDealer();
        Cards cards = Cards.of(HEART_QUEEN, HEART_TEN);
        dealer.receive(cards);

        assertThat(dealer.isAbleToReceiveCard()).isFalse();
    }

    @DisplayName("딜러는 초기 카드를 한 장만 공개한다.")
    @Test
    void getInitialCardsTest() {
        Dealer dealer = TestDataGenerator.getDealer();
        Cards cards = Cards.of(HEART_QUEEN, HEART_TEN);
        dealer.receive(cards);

        Cards initialOpeningCards = dealer.getInitialOpeningCards();
        assertThat(initialOpeningCards.getCards()).hasSize(1);
    }


    @DisplayName("딜러는 플레이어와 경합 한다.")
    @Nested
    class competeWithPlayer {
        Dealer dealer;
        Player player;

        @BeforeEach
        void setUp() {
            dealer = TestDataGenerator.getDealer();
            player = TestDataGenerator.getPlayerWithName("pobi");
        }

        @DisplayName("딜러의 게임 점수가 높으면 승리한다.")
        @Test
        void competeWithPlayerWinTest() {
            dealer.receive(Cards.of(HEART_QUEEN, HEART_TEN)); // 20
            player.receive(Cards.of(HEART_TEN, HEART_THREE)); // 13

            Result result = dealer.competeWithPlayer(player);
            assertThat(result).isEqualTo(Result.WIN);
        }

        @DisplayName("게임 점수가 낮으면 패배한다.")
        @Test
        void competeWithPlayerWLoseTest() {
            dealer.receive(Cards.of(HEART_TEN, HEART_THREE)); // 13
            player.receive(Cards.of(HEART_QUEEN, HEART_TEN)); // 20

            Result result = dealer.competeWithPlayer(player);
            assertThat(result).isEqualTo(Result.LOSE);
        }

        @DisplayName("게임 점수가 같으면 비긴다.")
        @Test
        void competeWithPlayerDrawTest() {
            dealer.receive(Cards.of(HEART_TEN, HEART_THREE)); // 13
            player.receive(Cards.of(HEART_QUEEN, CLUB_THREE)); // 13

            Result result = dealer.competeWithPlayer(player);
            assertThat(result).isEqualTo(Result.DRAW);
        }

        @DisplayName("딜러가 버스트 된 경우 패배한다.")
        @Test
        void competeWithPlayerLoseWhenBustedTest() {
            dealer.receive(Cards.of(HEART_TEN, HEART_THREE, HEART_QUEEN));
            player.receive(Cards.of(CLUB_THREE, CLUB_ACE));

            assertThat(dealer.isBusted()).isTrue();
            Result result = dealer.competeWithPlayer(player);
            assertThat(result).isEqualTo(Result.LOSE);
        }

        @DisplayName("플레이어가 버스트 된 경우 승리한다.")
        @Test
        void competeWithPlayerWinWhenPlayerBustedTest() {
            dealer.receive(Cards.of(CLUB_THREE, CLUB_ACE));
            player.receive(Cards.of(HEART_TEN, HEART_THREE, HEART_QUEEN));

            assertThat(player.isBusted()).isTrue();

            Result result = dealer.competeWithPlayer(player);
            assertThat(result).isEqualTo(Result.WIN);
        }

        @DisplayName("딜러와 플레이어 모두 버스트 된 경우 딜러가 승리한다.")
        @Test
        void competeWithPlayerWinWhenBothBustedTest() {
            dealer.receive(Cards.of(CLUB_THREE, SPADE_KING, SPADE_QUEEN));
            player.receive(Cards.of(HEART_THREE, HEART_TEN, HEART_QUEEN));

            assertThat(dealer.isBusted()).isTrue();
            assertThat(player.isBusted()).isTrue();

            Result result = dealer.competeWithPlayer(player);
            assertThat(result).isEqualTo(Result.WIN);
        }
    }

}
