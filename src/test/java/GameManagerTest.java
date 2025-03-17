import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.CardNumber;
import domain.CardShape;
import domain.GameManager;
import domain.GameResult;
import domain.TrumpCard;
import domain.TrumpCardManager;
import domain.user.Betting;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameManagerTest {
  
    @DisplayName("유저는 최소 1명 이상 7명 이하여야 한다.")
    @ParameterizedTest
    @MethodSource("userTestCase")
    void test(List<String> names) {

        //when && then
        assertThatCode(
                () -> GameManager.initailizeGameManager(names,
                        List.of(new Betting(1), new Betting(1), new Betting(1), new Betting(1), new Betting(1),
                                new Betting(1), new Betting(1)),
                        new TrumpCardManager())).doesNotThrowAnyException();
    }

    private static Stream<Arguments> userTestCase() {
        return Stream.of(
                //given
                Arguments.arguments(List.of("수양", "레몬", "키키", "나나", "모모", "부부", "롸롸")),
                Arguments.arguments(List.of("수양"))
        );
    }

    @DisplayName("등록한 유저가 기준보다 적거나 많으면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("userExceptionTestCase")
    void test2(List<String> names) {
        assertThatThrownBy(() -> GameManager.initailizeGameManager(names,
                List.of(new Betting(10000), new Betting(10000), new Betting(10000), new Betting(10000),
                        new Betting(10000), new Betting(10000), new Betting(10000)),
                new TrumpCardManager()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 1명 이상 7명 이하로 등록해야 합니다.");
    }

    private static Stream<Arguments> userExceptionTestCase() {
        return Stream.of(
                Arguments.arguments(List.of("수양", "레몬", "키키", "나나", "모모", "부부", "롸롸", "뫄뫄")),
                Arguments.arguments(List.of())
        );
    }

    @Test
    @DisplayName("유저는 중복될 수 없다.")
    void test3() {
        List<String> names = List.of("수양", "레몬", "수양", "레몬", "부부", "롸롸", "뫄뫄");
        assertThatThrownBy(() -> GameManager.initailizeGameManager(names,
                List.of(new Betting(10000), new Betting(10000), new Betting(10000), new Betting(10000),
                        new Betting(10000), new Betting(10000), new Betting(10000)),
                new TrumpCardManager()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 중복될 수 없습니다.");
    }

    @DisplayName("게임 시작 시 모든 유저와 딜러는 카드를 두 장씩 배부받는다.")
    @Test
    void test4() {

        // given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("수양", "레몬"),
                List.of(new Betting(10000), new Betting(10000)),
                new TrumpCardManager());

        // when
        gameManager.firstHandOutCard();
        Player player1 = gameManager.findPlayerByUsername("수양");
        Player player2 = gameManager.findPlayerByUsername("레몬");
        Dealer dealer = gameManager.getDealer();

        List<TrumpCard> allPlayer1Card = player1.getCardDeck().getAllCard();
        List<TrumpCard> allPlayer2Card = player2.getCardDeck().getAllCard();
        List<TrumpCard> allDealerCard = dealer.getCardDeck().getAllCard();

        //then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(allPlayer1Card).hasSize(2);
            softAssertions.assertThat(allPlayer2Card).hasSize(2);
            softAssertions.assertThat(allDealerCard).hasSize(2);
        });
    }

    @DisplayName("딜러와 유저의 카드의 총합을 가져온다.")
    @Test
    void test7() {

        //given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("수양"), List.of(new Betting(10000)),
                new TrumpCardManager());
        Player player = gameManager.findPlayerByUsername("수양");
        Dealer dealer = gameManager.getDealer();

        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.DIA, CardNumber.ACE));
        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.DIA, CardNumber.NINE));

        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.HEART, CardNumber.THREE));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.HEART, CardNumber.TWO));

        //when
        GameResult result = gameManager.compare(player, dealer);

        //then
        Assertions.assertThat(result).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 유저의 총합이 같을 때 딜러가 블랙잭이라면 무승부 혹은 패배이다.")
    @ParameterizedTest
    @MethodSource("addCardDeck")
    void test8(List<TrumpCard> playerCards, List<TrumpCard> dealerCards, GameResult expectStatus) {

        //given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("수양"), List.of(new Betting(10000)),
                new TrumpCardManager());
        Player player = gameManager.findPlayerByUsername("수양");
        Dealer dealer = gameManager.getDealer();

        for (TrumpCard card : playerCards) {
            player.getCardDeck().addTrumpCard(card);
        }
        for (TrumpCard card : dealerCards) {
            dealer.getCardDeck().addTrumpCard(card);
        }

        //when
        GameResult result = gameManager.compare(player, dealer);

        //then
        Assertions.assertThat(result).isEqualTo(expectStatus);
    }

    private static Stream<Arguments> addCardDeck() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new TrumpCard(CardShape.DIA, CardNumber.ACE),
                                new TrumpCard(CardShape.CLOVER, CardNumber.J)),
                        List.of(
                                new TrumpCard(CardShape.DIA, CardNumber.ACE),
                                new TrumpCard(CardShape.CLOVER, CardNumber.J)),
                        GameResult.DRAW),
                Arguments.arguments(
                        List.of(
                                new TrumpCard(CardShape.DIA, CardNumber.FIVE),
                                new TrumpCard(CardShape.CLOVER, CardNumber.SIX),
                                new TrumpCard(CardShape.CLOVER, CardNumber.J)),
                        List.of(
                                new TrumpCard(CardShape.DIA, CardNumber.ACE),
                                new TrumpCard(CardShape.CLOVER, CardNumber.J)),
                        GameResult.LOSE),
                Arguments.arguments(
                        List.of(
                                new TrumpCard(CardShape.DIA, CardNumber.ACE),
                                new TrumpCard(CardShape.CLOVER, CardNumber.J),
                                new TrumpCard(CardShape.CLOVER, CardNumber.J)),
                        List.of(
                                new TrumpCard(CardShape.DIA, CardNumber.ACE),
                                new TrumpCard(CardShape.CLOVER, CardNumber.J),
                                new TrumpCard(CardShape.CLOVER, CardNumber.J)),
                        GameResult.DRAW)
        );
    }

    @DisplayName("딜러가 버스트되면 이미 버스트된 유저 빼고 모두 승리한다")
    @Test
    void test9() {
        // given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("유저"), List.of(new Betting(10000)),
                new TrumpCardManager());

        Player player = gameManager.findPlayerByUsername("유저");
        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.J));
        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.Q));
        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.K));

        Dealer dealer = gameManager.getDealer();
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.K));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.J));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.Q));

        // when
        GameResult compare = gameManager.compare(player, dealer);

        // then
        Assertions.assertThat(compare).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("입력된 금액에 따라 배팅을 한다.")
    @Test
    void test10() {
        //given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬", "띵화"),
                List.of(new Betting(300000000), new Betting(500000000)), new TrumpCardManager());
        Player user1 = gameManager.findPlayerByUsername("레몬");
        Player user2 = gameManager.findPlayerByUsername("띵화");

        //then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(user1.getBetting().getBettingMoney()).isEqualTo(300000000);
            softAssertions.assertThat(user2.getBetting().getBettingMoney()).isEqualTo(500000000);
        });
    }

    @DisplayName("플레이어는 게임에서 승리시 배팅금액만큼 얻는다.")
    @Test
    void test11() {
        //given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬"),
                List.of(new Betting(300000000)), new TrumpCardManager());
        Player player = gameManager.findPlayerByUsername("레몬");
        Dealer dealer = gameManager.getDealer();

        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.J));
        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.Q));

        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.FIVE));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.TWO));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.K));

        //when
        Map<User, Long> gameResult = gameManager.createGameResult();

        //then
        Assertions.assertThat(gameResult.get(player)).isEqualTo(300000000);
    }

    @DisplayName("플레이어는 블랙잭으로 승리시 배팅금액의 1.5배를 얻는다.")
    @Test
    void test12() {
        //given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬"),
                List.of(new Betting(300000000)), new TrumpCardManager());
        Player player = gameManager.findPlayerByUsername("레몬");
        Dealer dealer = gameManager.getDealer();

        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.ACE));
        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.Q));

        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.FIVE));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.TWO));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.K));

        //when
        Map<User, Long> gameResult = gameManager.createGameResult();
        GameResult compare = gameManager.compare(player, dealer);

        //then
        Assertions.assertThat(compare).isEqualTo(GameResult.BLACKJACK);
        Assertions.assertThat(gameResult.get(player)).isEqualTo(450000000);
    }

    @DisplayName("딜러는 최종 수익을 낸다.")
    @Test
    void test13() {

        //given
        GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬", "륜도"),
                List.of(new Betting(300000000), new Betting(20000)), new TrumpCardManager());
        Player player1 = gameManager.findPlayerByUsername("레몬");
        Player player2 = gameManager.findPlayerByUsername("륜도");
        Dealer dealer = gameManager.getDealer();

        player1.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.ACE));
        player1.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.Q));
        player2.getCardDeck().addTrumpCard(new TrumpCard(CardShape.HEART, CardNumber.FIVE));
        player2.getCardDeck().addTrumpCard(new TrumpCard(CardShape.HEART, CardNumber.FOUR));

        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.FIVE));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.TWO));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.K));

        //when
        Map<User, Long> gameResult = gameManager.createGameResult();
        long dealerProfit = gameManager.calculateDealerProfit(gameResult);

        //then
        Assertions.assertThat(dealerProfit).isEqualTo(-449980000);
    }
}
