package domain;

import static org.assertj.core.api.Assertions.*;

import domain.enums.Rank;
import domain.enums.Result;
import domain.enums.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameTest {

    @Nested
    @DisplayName("정상 경우")
    class success {

        private Game twoPlayerGame;
        private Game onePlayerGame;

        @BeforeEach
        void setUp() {

            Deck deck = new Deck(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.FIVE, Suit.CLOVER),
                    new Card(Rank.SIX, Suit.CLOVER), new Card(Rank.SEVEN, Suit.CLOVER),
                    new Card(Rank.FOUR, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART)));

            List<String> twoPlayer = List.of("피즈", "스타크");
            twoPlayerGame = new Game(twoPlayer, deck);

            List<String> onePlayer = List.of("피즈");
            onePlayerGame = new Game(onePlayer, new Deck(List.of()));

        }

        @DisplayName("모든 플레이어가 정상적으로 생성된다.")
        @Test
        public void 모든_플레이어가_정상적으로_생성된다() {
            //when
            List<Player> players = twoPlayerGame.getPlayers();
            List<String> playerNames = players.stream()
                    .map(Player::getName)
                    .toList();
            //then
            assertThat(playerNames).contains("피즈");
            assertThat(playerNames).contains("스타크");
        }

        @DisplayName("게임 시작 후 모든 플레이어가 2장의 카드를 분배 받는다.")
        @Test
        void 게임_시작_후_모든_플레이어_2장의_카드_분배를_받는다() {
            //given
            //when
            twoPlayerGame.startGame();
            List<Player> players = twoPlayerGame.getPlayers();
            Dealer dealer = twoPlayerGame.getDealer();
            //then
            for (Player player : players) {
                assertThat(player.getCards().size()).isEqualTo(2);
            }

            assertThat(dealer.getCards().size()).isEqualTo(2);
        }

        @DisplayName("플레이어의 카드 총합이 21미만이고 히트 요청 시 한장을 더 분배한다.")
        @Test
        void 플레이어_카드_합_21_미만_히트_요청_시_한장_더_분배한다() {
            //given
            Player firstPlayer = twoPlayerGame.getPlayers().getFirst();
            //when
            firstPlayer.addCard(new Card(Rank.ACE, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.FOUR, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.EIGHT, Suit.CLOVER));
            //then
            twoPlayerGame.playerHit(firstPlayer, true);

            assertThat(firstPlayer.getCards().size()).isEqualTo(4);
        }

        @DisplayName("딜러의 카드 총합이 17미만이면 한장을 더 분배한다.")
        @Test
        void 딜러의_카드_총합이_17미만이면_한장을_더_분배한다() {
            //given
            Dealer dealer = twoPlayerGame.getDealer();
            //when
            dealer.addCard(new Card(Rank.ACE, Suit.CLOVER));
            dealer.addCard(new Card(Rank.FOUR, Suit.CLOVER));
            dealer.addCard(new Card(Rank.EIGHT, Suit.CLOVER));
            //then
            twoPlayerGame.playerHit(dealer, true);

            assertThat(dealer.getCards().size()).isEqualTo(4);
        }

        @DisplayName("플레이어가 버스트 되면 플레이어는 패배하고 딜러는 승리한다.")
        @Test
        void 플레이어_버스트_시_무조건_플레이어는_패배한다() {
            //given
            Player firstPlayer = onePlayerGame.getPlayers().getFirst();
            Dealer dealer = onePlayerGame.getDealer();

            //when
            firstPlayer.addCard(new Card(Rank.JACK, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.QUEEN, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.EIGHT, Suit.CLOVER));

            onePlayerGame.settlementOfResults();

            assertThat(firstPlayer.getResult()).isEqualTo(Result.LOSE);
            assertThat(dealer.getResult().get(Result.WIN)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 높으면 플레이어가 승리하고 딜러는 패배한다.")
        @Test
        void 플레이어_점수_더_높으면_플레이어_승리한다() {
            //given
            Player firstPlayer = onePlayerGame.getPlayers().getFirst();
            Dealer dealer = onePlayerGame.getDealer();

            //when
            firstPlayer.addCard(new Card(Rank.JACK, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.QUEEN, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.ACE, Suit.CLOVER));

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            onePlayerGame.settlementOfResults();

            assertThat(firstPlayer.getResult()).isEqualTo(Result.WIN);
            assertThat(dealer.getResult().get(Result.LOSE)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 딜러가 버스트된 경우 플레이어가 승리한다.")
        @Test
        void 딜러가_버스트된_경우_플레이어가_승리한다() {
            //given
            Player firstPlayer = onePlayerGame.getPlayers().getFirst();
            Dealer dealer = onePlayerGame.getDealer();

            //when
            firstPlayer.addCard(new Card(Rank.JACK, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.QUEEN, Suit.CLOVER));

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.SIX, Suit.HEART));
            dealer.addCard(new Card(Rank.SEVEN, Suit.HEART));

            onePlayerGame.settlementOfResults();

            assertThat(firstPlayer.getResult()).isEqualTo(Result.WIN);
            assertThat(dealer.getResult().get(Result.LOSE)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 낮으면 플레이어가 패배하고 딜러는 승리한다.")
        @Test
        void 플레이어_점수_더_높으면_플레이어_패배한다() {
            //given
            Player firstPlayer = onePlayerGame.getPlayers().getFirst();
            Dealer dealer = onePlayerGame.getDealer();

            //when
            firstPlayer.addCard(new Card(Rank.JACK, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.NINE, Suit.CLOVER));

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            onePlayerGame.settlementOfResults();

            assertThat(firstPlayer.getResult()).isEqualTo(Result.LOSE);
            assertThat(dealer.getResult().get(Result.WIN)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어와 딜러의 점수가 같으면 무승부가 된다.")
        @Test
        void 플레이어_딜러_점수_같으면_무승부_된다() {
            //given
            Player firstPlayer = onePlayerGame.getPlayers().getFirst();
            Dealer dealer = onePlayerGame.getDealer();

            //when
            firstPlayer.addCard(new Card(Rank.JACK, Suit.CLOVER));
            firstPlayer.addCard(new Card(Rank.QUEEN, Suit.CLOVER));

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            onePlayerGame.settlementOfResults();

            assertThat(firstPlayer.getResult()).isEqualTo(Result.DRAW);
            assertThat(dealer.getResult().get(Result.DRAW)).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("예외 경우")
    class failure {

        @DisplayName("플레이어 이름이 중복된 경우 예외가 발생한다.")
        @Test
        void 플레이어_이름이_중복된_경우_예외가_발생한다() {
            List<String> duplicatedNames = List.of("피즈", "피즈", "스타크");

            assertThatThrownBy(() -> new Game(duplicatedNames, new Deck(List.of())))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }

        private static Stream<Arguments> playerNumberOutOfRange() {
            return Stream.of(
                    Arguments.of(List.of()),
                    Arguments.of(List.of("피즈1","피즈2","피즈3","피즈4","피즈5","피즈6","피즈7","피즈8"))
            );
        }

        @DisplayName("게임에 참여하는 플레이어 인원이 1~7명이 아닐 경우 예외가 발생한다.")
        @ParameterizedTest
        @MethodSource("playerNumberOutOfRange")
        void 게임_참여_플레이어가_1명에서_7명_사이가_아닐_경우_예외가_발생한다(List<String> names) {
            assertThatThrownBy(() -> new Game(names, new Deck(List.of())))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 플레이어는 1명 이상 7명 이하여야 합니다.");
        }
    }
}
