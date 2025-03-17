package domain;

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

class ScoreTest {
    @DisplayName("딜러와 유저의 카드의 총합을 가져온다.")
    @Test
    void test1() {
        //given
        List<String> names = List.of("수양");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame blackjackGame = BlackjackGame.of(users, new Dealer(), new CardDeck());
        User player = users.getFirst();
        User dealer = blackjackGame.getDealer();

        player.addTrumpCard(new Card(CardShape.DIA, CardRank.ACE));
        player.addTrumpCard(new Card(CardShape.DIA, CardRank.NINE));

        dealer.addTrumpCard(new Card(CardShape.HEART, CardRank.THREE));
        dealer.addTrumpCard(new Card(CardShape.HEART, CardRank.TWO));

        //when
        Score score = new Score(blackjackGame.getParticipants());
        Map<User, GameResult> gameResult = score.calculatePlayerScore();

        //then
        Assertions.assertThat(gameResult.get(player)).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 유저의 총합이 같을 때 딜러가 블랙잭이라면 무승부 혹은 패배이다.")
    @ParameterizedTest
    @MethodSource("addCardDeck")
    void test2(List<Card> playerCards, List<Card> dealerCards, GameResult expectStatus) {
        //given
        List<String> names = List.of("수양");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame blackjackGame = BlackjackGame.of(users, new Dealer(), new CardDeck());
        User player = users.getFirst();
        User dealer = blackjackGame.getDealer();

        for (Card card : playerCards) {
            player.addTrumpCard(card);
        }
        for (Card card : dealerCards) {
            dealer.addTrumpCard(card);
        }

        //when
        Score score = new Score(blackjackGame.getParticipants());
        Map<User, GameResult> gameResult = score.calculatePlayerScore();

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
    void test3() {
        // given
        List<String> names = List.of("유저");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame blackjackGame = BlackjackGame.of(users, new Dealer(), new CardDeck());
        User user = users.getFirst();
        user.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
        user.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
        user.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));

        blackjackGame.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.K));
        blackjackGame.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
        blackjackGame.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.Q));

        // when
        Score score = new Score(blackjackGame.getParticipants());
        Map<User, GameResult> gameResult = score.calculatePlayerScore();

        // then
        Assertions.assertThat(gameResult.get(user)).isEqualTo(GameResult.LOSE);
    }
}