package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.Rank;
import domain.enums.Result;
import domain.enums.Suit;
import domain.participant.Players;
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
        private Deck deck;

        @BeforeEach
        void setUp() {
            deck = new Deck(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.FIVE, Suit.HEART),
                    new Card(Rank.SIX, Suit.CLOVER), new Card(Rank.SEVEN, Suit.CLOVER),
                    new Card(Rank.FOUR, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART)));

            twoPlayers = new Players(List.of("피즈", "스타크"));
            twoPlayerGame = new Game(twoPlayers);

            onePlayer = new Players(List.of("피즈"));
            onePlayerGame = new Game(onePlayer);
        }

        @DisplayName("게임 시작 후 모든 플레이어가 2장의 카드를 분배 받는다.")
        @Test
        void 게임_시작_후_모든_플레이어_2장의_카드_분배를_받는다() {
            //given
            //when
            twoPlayerGame.initializeGame(deck);
            //then

            assertThat(twoPlayers.getPlayerCards("피즈").size()).isEqualTo(2);
            assertThat(twoPlayers.getPlayerCards("스타크").size()).isEqualTo(2);

            assertThat(twoPlayerGame.getDealerCards().size()).isEqualTo(2);
        }

        @DisplayName("플레이어의 카드 총합이 21미만이고 히트 요청 시 한장을 더 분배한다.")
        @Test
        void 플레이어_카드_합_21_미만_히트_요청_시_한장_더_분배한다() {
            //given
            //when
            onePlayer.distributeCard("피즈", new Card(Rank.ACE, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.FOUR, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.EIGHT, Suit.CLOVER));
            //then
            onePlayerGame.playerHit("피즈", deck, false);

            assertThat(onePlayer.getPlayerCards("피즈").size()).isEqualTo(4);
        }

        @DisplayName("딜러의 카드 총합이 17미만이면 한장을 더 분배한다.")
        @Test
        void 딜러의_카드_총합이_17미만이면_한장을_더_분배한다() {
            deck = new Deck(List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.FOUR, Suit.CLOVER),
                    new Card(Rank.EIGHT, Suit.CLOVER), new Card(Rank.SEVEN, Suit.HEART)));

            onePlayerGame.dealerHit(deck);
            onePlayerGame.dealerHit(deck);
            onePlayerGame.dealerHit(deck);

            onePlayerGame.dealerHit(deck);

            assertThat(onePlayerGame.getDealerCards().size()).isEqualTo(4);
        }

        @DisplayName("플레이어가 버스트 되면 플레이어는 패배하고 딜러는 승리한다.")
        @Test
        void 플레이어_버스트_시_무조건_플레이어는_패배한다() {
            onePlayer.distributeCard("피즈", new Card(Rank.JACK, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.QUEEN, Suit.CLOVER));
            onePlayer.distributeCard("피즈", new Card(Rank.EIGHT, Suit.CLOVER));

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.LOSE);
            assertThat(onePlayerGame.getDealerResult().get(Result.WIN)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 높으면 플레이어가 승리하고 딜러는 패배한다.")
        @Test
        void 플레이어_점수_더_높으면_플레이어_승리한다() {
            Deck playerDeck = new Deck(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER),
                    new Card(Rank.ACE, Suit.CLOVER)));
            onePlayerGame.playerHit("피즈", playerDeck, false);
            onePlayerGame.playerHit("피즈", playerDeck, false);
            onePlayerGame.playerHit("피즈", playerDeck, false);

            Deck dealerDeck = new Deck(List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.FOUR, Suit.CLOVER),
                    new Card(Rank.EIGHT, Suit.CLOVER), new Card(Rank.SEVEN, Suit.HEART)));
            onePlayerGame.dealerHit(dealerDeck);
            onePlayerGame.dealerHit(dealerDeck);
            onePlayerGame.dealerHit(dealerDeck);

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.WIN);
            assertThat(onePlayerGame.getDealerResult().get(Result.LOSE)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 딜러가 버스트된 경우 플레이어가 승리한다.")
        @Test
        void 딜러가_버스트된_경우_플레이어가_승리한다() {
            Deck playerDeck = new Deck(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER)));
            onePlayerGame.playerHit("피즈", playerDeck, false);
            onePlayerGame.playerHit("피즈", playerDeck, false);

            Deck dealerDeck = new Deck(List.of(new Card(Rank.JACK, Suit.HEART), new Card(Rank.SIX, Suit.HEART),
                    new Card(Rank.SEVEN, Suit.HEART)));
            onePlayerGame.dealerHit(dealerDeck);
            onePlayerGame.dealerHit(dealerDeck);
            onePlayerGame.dealerHit(dealerDeck);

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.WIN);
            assertThat(onePlayerGame.getDealerResult().get(Result.LOSE)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 낮으면 플레이어가 패배하고 딜러는 승리한다.")
        @Test
        void 플레이어_점수_더_높으면_플레이어_패배한다() {
            Deck playerDeck = new Deck(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.NINE, Suit.CLOVER)));
            onePlayerGame.playerHit("피즈", playerDeck, false);
            onePlayerGame.playerHit("피즈", playerDeck, false);

            Deck dealerDeck = new Deck(List.of(new Card(Rank.JACK, Suit.HEART), new Card(Rank.SIX, Suit.HEART),
                    new Card(Rank.FOUR, Suit.HEART)));
            onePlayerGame.dealerHit(dealerDeck);
            onePlayerGame.dealerHit(dealerDeck);
            onePlayerGame.dealerHit(dealerDeck);

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.LOSE);
            assertThat(onePlayerGame.getDealerResult().get(Result.WIN)).isEqualTo(1);
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어와 딜러의 점수가 같으면 무승부가 된다.")
        @Test
        void 플레이어_딜러_점수_같으면_무승부_된다() {
            Deck playerDeck = new Deck(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.NINE, Suit.CLOVER)));
            onePlayerGame.playerHit("피즈", playerDeck, false);
            onePlayerGame.playerHit("피즈", playerDeck, false);

            Deck dealerDeck = new Deck(List.of(new Card(Rank.JACK, Suit.HEART), new Card(Rank.NINE, Suit.HEART)));
            onePlayerGame.dealerHit(dealerDeck);
            onePlayerGame.dealerHit(dealerDeck);

            assertThat(onePlayerGame.getPlayerResult("피즈")).isEqualTo(Result.DRAW);
            assertThat(onePlayerGame.getDealerResult().get(Result.DRAW)).isEqualTo(1);
        }

        @DisplayName("플레이어와 딜러가 분배된 카드를 알맞게 가지고 있는지 확인한다.")
        @Test
        void 플레이어_딜러_카드_정상_분배_확인한다() {
            onePlayerGame.initializeGame(deck);
            onePlayerGame.playerHit("피즈", deck, false);

            List<Card> fizzCards = onePlayerGame.getPlayerCards("피즈");
            List<Card> expectedFizzCards = List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.SIX, Suit.CLOVER),
                    new Card(Rank.FOUR, Suit.SPADE));

            assertThat(fizzCards).isEqualTo(expectedFizzCards);

            onePlayerGame.dealerHit(deck);

            List<Card> dealerCard = onePlayerGame.getDealerCards();
            List<Card> expectedDealerCards = List.of(new Card(Rank.FIVE, Suit.HEART), new Card(Rank.SEVEN, Suit.CLOVER),
                    new Card(Rank.SEVEN, Suit.HEART));

            deck = new Deck(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.FIVE, Suit.HEART),
                    new Card(Rank.SIX, Suit.CLOVER), new Card(Rank.SEVEN, Suit.CLOVER),
                    new Card(Rank.FOUR, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART)));

            assertThat(dealerCard).isEqualTo(expectedDealerCards);
        }

        @DisplayName("딜러가 처음에 받은 카드 2장으로 바로 블랙잭이 되면 카드를 추가로 더 분배 받지 않는다.")
        @Test
        void 딜러_처음_2장_블랙잭_카드_추가_분배_받지_않는다() {
            Deck deck = new Deck(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.JACK, Suit.CLOVER),
                    new Card(Rank.FIVE, Suit.HEART), new Card(Rank.ACE, Suit.CLOVER),
                    new Card(Rank.SEVEN, Suit.HEART), new Card(Rank.SIX, Suit.HEART)));
            onePlayerGame.initializeGame(deck);
            onePlayerGame.dealerHit(deck);
            onePlayerGame.dealerHit(deck);

            assertThat(onePlayerGame.getDealerScore()).isEqualTo(21);
            assertThat(onePlayerGame.getDealerCards().size()).isEqualTo(2);
        }
    }
}
