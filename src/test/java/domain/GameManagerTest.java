package domain;

import static org.assertj.core.api.Assertions.*;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
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
        List<Player> users = names.stream().map(Player::new).toList();
        assertThatCode(() -> new BlackjackGame(users, new Dealer(), new CardDeck())).doesNotThrowAnyException();
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
        List<Player> users = names.stream().map(Player::new).toList();
        assertThatThrownBy(() -> new BlackjackGame(users, new Dealer(), new CardDeck()))
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
        List<Player> users = names.stream().map(Player::new).toList();
        assertThatThrownBy(() -> new BlackjackGame(users, new Dealer(), new CardDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 중복될 수 없습니다.");
    }

    @DisplayName("게임 시작 시 모든 유저와 딜러는 카드를 두 장씩 배부받는다.")
    @Test
    void test4() {
        // given
        List<String> names = List.of("수양", "레몬");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame gameManger = new BlackjackGame(users, new Dealer(), new CardDeck());

        // when
        gameManger.firstHandOutCard();
        User user = (Player) gameManger.findUserByUsername("수양");
        User dealer = (Dealer) gameManger.getDealer();

        assertThat(user.getSize()).isEqualTo(2);
        assertThat(dealer.getSize()).isEqualTo(2);
    }

    @DisplayName("딜러와 유저의 카드의 총합을 가져온다.")
    @Test
    void test7() {
        //given
        List<String> names = List.of("수양");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame gameManger = new BlackjackGame(users, new Dealer(), new CardDeck());
        User player = gameManger.findUserByUsername("수양");
        User dealer = gameManger.getDealer();

        player.addTrumpCard(new Card(CardShape.DIA, CardRank.ACE));
        player.addTrumpCard(new Card(CardShape.DIA, CardRank.NINE));

        dealer.addTrumpCard(new Card(CardShape.HEART, CardRank.THREE));
        dealer.addTrumpCard(new Card(CardShape.HEART, CardRank.TWO));

        //when
        Map<User, GameResult> gameResult = gameManger.calculatePlayerScore();

        //then
        Assertions.assertThat(gameResult.get(player)).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 유저의 총합이 같을 때 딜러가 블랙잭이라면 무승부 혹은 패배이다.")
    @ParameterizedTest
    @MethodSource("addCardDeck")
    void test8(List<Card> playerCards, List<Card> dealerCards, GameResult expectStatus) {
        //given
        List<String> names = List.of("수양");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame gameManger = new BlackjackGame(users, new Dealer(), new CardDeck());
        User player = gameManger.findUserByUsername("수양");
        User dealer = gameManger.getDealer();

        for (Card card : playerCards) {
            player.addTrumpCard(card);
        }
        for (Card card : dealerCards) {
            dealer.addTrumpCard(card);
        }

        //when
        Map<User, GameResult> gameResult = gameManger.calculatePlayerScore();

        //then
        Assertions.assertThat(gameResult.get(player)).isEqualTo(expectStatus);
    }

    private static Stream<Arguments> addCardDeck() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new Card(CardShape.DIA, CardRank.ACE),
                                new Card(CardShape.CLOVER, CardRank.J)),
                        List.of(
                                new Card(CardShape.DIA, CardRank.ACE),
                                new Card(CardShape.CLOVER, CardRank.J)),
                        GameResult.DRAW),
                Arguments.arguments(
                        List.of(
                                new Card(CardShape.DIA, CardRank.FIVE),
                                new Card(CardShape.CLOVER, CardRank.SIX),
                                new Card(CardShape.CLOVER, CardRank.J)),
                        List.of(
                                new Card(CardShape.DIA, CardRank.ACE),
                                new Card(CardShape.CLOVER, CardRank.J)),
                        GameResult.LOSE),
                Arguments.arguments(
                        List.of(
                                new Card(CardShape.DIA, CardRank.ACE),
                                new Card(CardShape.CLOVER, CardRank.J),
                                new Card(CardShape.CLOVER, CardRank.J)),
                        List.of(
                                new Card(CardShape.DIA, CardRank.ACE),
                                new Card(CardShape.CLOVER, CardRank.J),
                                new Card(CardShape.CLOVER, CardRank.J)),
                        GameResult.DRAW)
        );
    }

    @DisplayName("딜러가 버스트되면 이미 버스트된 유저 빼고 모두 승리한다")
    @Test
    void test9() {
        // given
        List<String> names = List.of("유저");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame gameManger = new BlackjackGame(users, new Dealer(), new CardDeck());
        User user = gameManger.findUserByUsername("유저");
        user.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
        user.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
        user.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));

        gameManger.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.K));
        gameManger.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
        gameManger.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.Q));

        // when
        Map<User, GameResult> gameResult = gameManger.calculatePlayerScore();

        // then
        Assertions.assertThat(gameResult.get(user)).isEqualTo(GameResult.LOSE);
    }
}
