package domain;

import static org.assertj.core.api.Assertions.*;

import domain.enums.Rank;
import domain.enums.Result;
import domain.enums.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Nested
    @DisplayName("정상 경우")
    class success {

        private Game twoPlayerGame;
        private Game onePlayerGame;
        private Players twoPlayers;
        private Players onePlayer;
        private Dealer dealer = new Dealer();

        @BeforeEach
        void setUp() {

            Deck deck = new Deck(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.FIVE, Suit.CLOVER),
                    new Card(Rank.SIX, Suit.CLOVER), new Card(Rank.SEVEN, Suit.CLOVER),
                    new Card(Rank.FOUR, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART)));

            twoPlayers = new Players(List.of("피즈", "스타크"));
            twoPlayerGame = new Game(twoPlayers, dealer, deck);

            onePlayer = new Players(List.of("피즈"));
            onePlayerGame = new Game(onePlayer, dealer, deck);

        }

        @DisplayName("게임 시작 후 모든 플레이어가 2장의 카드를 분배 받는다.")
        @Test
        void 게임_시작_후_모든_플레이어_2장의_카드_분배를_받는다() {
            //given
            //when
            twoPlayerGame.initializeGame();
            //then

            assertThat(twoPlayers.getPlayerCards("피즈").size()).isEqualTo(2);
            assertThat(twoPlayers.getPlayerCards("스타크").size()).isEqualTo(2);

            assertThat(dealer.cardBoard.getCards().size()).isEqualTo(2);
        }

        @DisplayName("플레이어의 카드 총합이 21미만이고 히트 요청 시 한장을 더 분배한다.")
        @Test
        void 플레이어_카드_합_21_미만_히트_요청_시_한장_더_분배한다() {
            //given
            //when
            onePlayer.distributeCard("피즈",new Card(Rank.ACE, Suit.CLOVER));
            onePlayer.distributeCard("피즈",new Card(Rank.FOUR, Suit.CLOVER));
            onePlayer.distributeCard("피즈",new Card(Rank.EIGHT, Suit.CLOVER));
            //then
            onePlayerGame.playerHit("피즈", true);

            assertThat(onePlayer.getPlayerCards("피즈").size()).isEqualTo(4);
        }

        @DisplayName("딜러의 카드 총합이 17미만이면 한장을 더 분배한다.")
        @Test
        void 딜러의_카드_총합이_17미만이면_한장을_더_분배한다() {
            //given
            //when
            dealer.addCard(new Card(Rank.ACE, Suit.CLOVER));
            dealer.addCard(new Card(Rank.FOUR, Suit.CLOVER));
            dealer.addCard(new Card(Rank.EIGHT, Suit.CLOVER));
            //then
            onePlayerGame.dealerHit();

            assertThat(dealer.cardBoard.getCards().size()).isEqualTo(4);
        }

        @DisplayName("플레이어가 버스트 되면 플레이어는 패배하고 딜러는 승리한다.")
        @Test
        void 플레이어_버스트_시_무조건_플레이어는_패배한다() {
            onePlayer.distributeCard("피즈", new Card(Rank.JACK, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.QUEEN, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.EIGHT, Suit.CLOVER));

            onePlayerGame.settlementOfResults();

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.LOSE);
            assertThat(dealer.getResult().get(Result.WIN)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 높으면 플레이어가 승리하고 딜러는 패배한다.")
        @Test
        void 플레이어_점수_더_높으면_플레이어_승리한다() {
            onePlayer.distributeCard("피즈", new Card(Rank.JACK, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.QUEEN, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.ACE, Suit.CLOVER));

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            onePlayerGame.settlementOfResults();

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.WIN);
            assertThat(dealer.getResult().get(Result.LOSE)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 딜러가 버스트된 경우 플레이어가 승리한다.")
        @Test
        void 딜러가_버스트된_경우_플레이어가_승리한다() {

            onePlayer.distributeCard("피즈", new Card(Rank.JACK, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.QUEEN, Suit.CLOVER));

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.SIX, Suit.HEART));
            dealer.addCard(new Card(Rank.SEVEN, Suit.HEART));

            onePlayerGame.settlementOfResults();

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.WIN);
            assertThat(dealer.getResult().get(Result.LOSE)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 낮으면 플레이어가 패배하고 딜러는 승리한다.")
        @Test
        void 플레이어_점수_더_높으면_플레이어_패배한다() {
            onePlayer.distributeCard("피즈",new Card(Rank.JACK, Suit.CLOVER));
            onePlayer.distributeCard("피즈",new Card(Rank.NINE, Suit.CLOVER));

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            onePlayerGame.settlementOfResults();

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.LOSE);
            assertThat(dealer.getResult().get(Result.WIN)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어와 딜러의 점수가 같으면 무승부가 된다.")
        @Test
        void 플레이어_딜러_점수_같으면_무승부_된다() {

            onePlayer.distributeCard("피즈",new Card(Rank.JACK, Suit.CLOVER));
            onePlayer.distributeCard("피즈",new Card(Rank.QUEEN, Suit.CLOVER));

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            //then
            onePlayerGame.settlementOfResults();

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.DRAW);
            assertThat(dealer.getResult().get(Result.DRAW)).isEqualTo(1);
        }
    }
}
