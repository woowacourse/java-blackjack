import static org.assertj.core.api.Assertions.*;

import domain.CardNumber;
import domain.GameResult;
import domain.TrumpCardManager;
import domain.CardShape;
import domain.TrumpCard;
import domain.user.Dealer;
import domain.GameManager;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameManagerTest {
    @BeforeEach
    public void setUp() {
        TrumpCardManager.bin();
        TrumpCardManager.initCache();
    }

    @DisplayName("유저는 최소 1명 이상 7명 이하여야 한다.")
    @ParameterizedTest
    @MethodSource("userTestCase")
    void test(List<String> names) {
        assertThatCode(() -> new GameManager(names)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> userTestCase() {
        return Stream.of(
                Arguments.arguments(List.of("수양", "레몬", "키키", "나나", "모모", "부부", "롸롸")),
                Arguments.arguments(List.of("수양"))
        );
    }

    @DisplayName("등록한 유저가 기준보다 적거나 많으면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("userExceptionTestCase")
    void test2(List<String> names) {
        assertThatThrownBy(() -> new GameManager(names))
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
        assertThatThrownBy(() -> new GameManager(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 중복될 수 없습니다.");
    }

    @DisplayName("게임 시작 시 모든 유저와 딜러는 카드를 두 장씩 배부받는다.")
    @Test
    void test4() {
        // given
        GameManager gameManager = new GameManager(List.of("수양", "레몬"));

        // when
        gameManager.firstHandOutCard();
        User user = (Player) gameManager.findUserByUsername("수양");
        User dealer = (Dealer) gameManager.getDealer();

        assertThat(user.getSize()).isEqualTo(2);
        assertThat(dealer.getSize()).isEqualTo(2);
    }

    @DisplayName("딜러와 유저의 카드의 총합을 가져온다.")
    @Test
    void test7() {
        //given
        GameManager gameManager = new GameManager(List.of("수양"));
        User player = gameManager.findUserByUsername("수양");
        User dealer = gameManager.getDealer();

        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.DIA, CardNumber.ACE));
        player.getCardDeck().addTrumpCard(new TrumpCard(CardShape.DIA, CardNumber.NINE));

        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.HEART, CardNumber.THREE));
        dealer.getCardDeck().addTrumpCard(new TrumpCard(CardShape.HEART, CardNumber.TWO));

        //when
        GameResult result = gameManager.compare(player);

        //then
        Assertions.assertThat(result).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 유저의 총합이 같을 때 딜러가 블랙잭이라면 무승부 혹은 패배이다.")
    @ParameterizedTest
    @MethodSource("addCardDeck")
    void test8(List<TrumpCard> playerCards, List<TrumpCard> dealerCards, GameResult expectStatus) {
        //given
        GameManager gameManager = new GameManager(List.of("수양"));
        User player = gameManager.findUserByUsername("수양");
        User dealer = gameManager.getDealer();

        for (TrumpCard card : playerCards) {
            player.getCardDeck().addTrumpCard(card);
        }
        for (TrumpCard card : dealerCards) {
            dealer.getCardDeck().addTrumpCard(card);
        }

        //when
        GameResult result = gameManager.compare(player);

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
        GameManager gameManager = new GameManager(List.of("유저"));
        User user = gameManager.findUserByUsername("유저");
        user.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.J));
        user.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.Q));
        user.getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.K));

        gameManager.getDealer().getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.K));
        gameManager.getDealer().getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.J));
        gameManager.getDealer().getCardDeck().addTrumpCard(new TrumpCard(CardShape.CLOVER, CardNumber.Q));

        // when
        Map<User, GameResult> gameResult = gameManager.createGameResult();

        // then
        Assertions.assertThat(gameResult.get(user)).isEqualTo(GameResult.LOSE);
    }
}
