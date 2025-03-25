package game;

import card.Card;
import card.CardDeck;
import card.CardRank;
import card.CardShape;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import user.Dealer;
import user.Participant;
import user.Player;

public class BlackjackGameTest {
    @DisplayName("유저는 최소 1명 이상 7명 이하여야 한다.")
    @ParameterizedTest
    @MethodSource("userTestCase")
    void test(List<String> names) {
        // given
        List<Player> players = names.stream().map(Player::new).toList();

        // when & then
        Assertions.assertThatCode(() -> BlackjackGame.of(
                players,
                new Dealer(),
                new CardDeck())).doesNotThrowAnyException();
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
        // given
        List<Player> users = names.stream().map(Player::new).toList();

        // when & then
        Assertions.assertThatThrownBy(() -> BlackjackGame.of(users, new Dealer(), new CardDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 2명 이상 8명 이하로 등록해야 합니다.");
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
        // given
        List<String> names = List.of("수양", "레몬", "수양", "레몬", "부부", "롸롸", "뫄뫄");
        List<Player> users = names.stream().map(Player::new).toList();

        // when & then
        Assertions.assertThatThrownBy(() -> BlackjackGame.of(users, new Dealer(), new CardDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 중복될 수 없습니다.");
    }

    @DisplayName("게임 시작 시 모든 유저와 딜러는 카드를 두 장씩 배부받는다.")
    @Test
    void test4() {
        // given
        List<String> names = List.of("수양", "레몬");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame blackjackGame = BlackjackGame.of(users, new Dealer(), new CardDeck());

        // when
        blackjackGame.firstHandOutCard();

        Assertions.assertThat(blackjackGame.getDealer().getSize()).isEqualTo(2);
        blackjackGame.getParticipants()
                .getPlayers()
                .forEach(player -> Assertions.assertThat(player.getSize()).isEqualTo(2));
    }

    @Nested
    @DisplayName("점수 계산")
    class Score {
        @DisplayName("딜러와 유저의 카드의 총합을 가져온다.")
        @Test
        void test1() {
            //given
            List<String> names = List.of("수양");
            List<Player> users = names.stream().map(Player::new).toList();

            BlackjackGame blackjackGame = BlackjackGame.of(users, new Dealer(), new CardDeck());

            Participant player = users.getFirst();
            Participant dealer = blackjackGame.getDealer();

            player.addTrumpCard(new Card(CardShape.DIA, CardRank.ACE));
            player.addTrumpCard(new Card(CardShape.DIA, CardRank.NINE));

            dealer.addTrumpCard(new Card(CardShape.HEART, CardRank.THREE));
            dealer.addTrumpCard(new Card(CardShape.HEART, CardRank.TWO));

            //when
            Map<Participant, GameResult> gameResult = blackjackGame.calculatePlayerScore();

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
            Participant player = users.getFirst();
            Participant dealer = blackjackGame.getDealer();

            for (Card card : playerCards) {
                player.addTrumpCard(card);
            }
            for (Card card : dealerCards) {
                dealer.addTrumpCard(card);
            }

            //when
            Map<Participant, GameResult> gameResult = blackjackGame.calculatePlayerScore();

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
            Participant participant = users.getFirst();
            participant.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
            participant.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
            participant.addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));

            blackjackGame.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.K));
            blackjackGame.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.J));
            blackjackGame.getDealer().addTrumpCard(new Card(CardShape.CLOVER, CardRank.Q));

            // when
            Map<Participant, GameResult> gameResult = blackjackGame.calculatePlayerScore();

            // then
            Assertions.assertThat(gameResult.get(participant)).isEqualTo(GameResult.LOSE);
        }

        @DisplayName("플레이어가 블랙잭으로 승리하면 150%의 수익을 얻는다.")
        @Test
        void test4() {
            // given
            Player a = new Player("A");
            a.betMoney(1000);
            a.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));
            a.drawCard(new Card(CardShape.CLOVER, CardRank.J));

            Dealer dealer = new Dealer();
            dealer.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));

            BlackjackGame blackjackGame = BlackjackGame.of(List.of(a), dealer, new CardDeck());
            Map<Participant, GameResult> gameResults = blackjackGame.calculatePlayerScore();

            // when
            Map<Participant, Double> rewards = blackjackGame.calculateRewards(gameResults, dealer);

            // then
            Assertions.assertThat(rewards.get(a)).isEqualTo(1500);
            Assertions.assertThat(rewards.get(dealer)).isEqualTo(-1500);
        }

        @DisplayName("플레이어가 승리하면 100%의 수익을 얻는다.")
        @Test
        void test5() {
            // given
            Player a = new Player("A");
            a.betMoney(1000);
            a.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));
            a.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));

            Dealer dealer = new Dealer();
            dealer.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));

            BlackjackGame blackjackGame = BlackjackGame.of(List.of(a), dealer, new CardDeck());
            Map<Participant, GameResult> gameResults = blackjackGame.calculatePlayerScore();

            // when
            Map<Participant, Double> rewards = blackjackGame.calculateRewards(gameResults, dealer);

            // then
            Assertions.assertThat(rewards.get(a)).isEqualTo(1000);
            Assertions.assertThat(rewards.get(dealer)).isEqualTo(-1000);
        }

        @DisplayName("무승부 시 플레이어와 딜러 누구도 수익을 얻지 않는다.")
        @Test
        void test6() {
            // given
            Player a = new Player("A");
            a.betMoney(1000);
            a.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));
            a.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));

            Dealer dealer = new Dealer();
            dealer.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));
            dealer.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));

            BlackjackGame blackjackGame = BlackjackGame.of(List.of(a), dealer, new CardDeck());
            Map<Participant, GameResult> gameResults = blackjackGame.calculatePlayerScore();

            // when
            Map<Participant, Double> rewards = blackjackGame.calculateRewards(gameResults, dealer);

            // then
            Assertions.assertThat(rewards.get(a)).isEqualTo(0);
            Assertions.assertThat(rewards.get(dealer)).isEqualTo(0);
        }

        @DisplayName("플레이어가 패배하면 100%의 손해를 얻는다.")
        @Test
        void test7() {
            // given
            Player a = new Player("A");
            a.betMoney(1000);
            a.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));

            Dealer dealer = new Dealer();
            dealer.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));
            dealer.drawCard(new Card(CardShape.CLOVER, CardRank.ACE));

            BlackjackGame blackjackGame = BlackjackGame.of(List.of(a), dealer, new CardDeck());
            Map<Participant, GameResult> gameResults = blackjackGame.calculatePlayerScore();

            // when
            Map<Participant, Double> rewards = blackjackGame.calculateRewards(gameResults, dealer);

            // then
            Assertions.assertThat(rewards.get(a)).isEqualTo(-1000);
            Assertions.assertThat(rewards.get(dealer)).isEqualTo(1000);
        }
    }
}
