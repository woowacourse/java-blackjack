package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.GameResult;
import domain.enums.Rank;
import domain.enums.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import service.CardGenerator;
import service.DealerBlackjackCardGenerator;
import service.TestCardGenerator;

public class GameTest {

    private Game twoPlayerGame;
    private Game onePlayerGame;
    private Dealer dealer = new Dealer();
    private Deck deck;
    private Deck delaerBlackjackDeck;

    @BeforeEach
    void setUp() {
        CardGenerator cardGenerator1 = new TestCardGenerator();
        CardGenerator cardGenerator2 = new DealerBlackjackCardGenerator();
        deck = new Deck(cardGenerator1.generate());
        delaerBlackjackDeck = new Deck(cardGenerator2.generate());

        twoPlayerGame = new Game(List.of("피즈", "스타크"), dealer);
        onePlayerGame = new Game(List.of("피즈"), dealer);
    }

    @Nested
    @DisplayName("게임 정상 초기화")
    class gameInitializeSuccess {
        @DisplayName("게임 시작 후 모든 플레이어가 2장의 카드를 분배 받는다.")
        @Test
        void 게임_시작_후_모든_플레이어_2장의_카드_분배를_받는다() {
            //given
            //when
            twoPlayerGame.initializeGame(deck);
            //then
            assertSoftly(softly -> {
                assertThat(twoPlayerGame.getPlayerCard(new Name("피즈")).size()).isEqualTo(2);
                assertThat(twoPlayerGame.getPlayerCard(new Name("스타크")).size()).isEqualTo(2);

                assertThat(dealer.getHand().size()).isEqualTo(2);
            });
        }
    }

    @Nested
    @DisplayName("카드 정상 분배")
    class cardDistributionSuccess {
        @DisplayName("플레이어의 카드 총합이 21미만이고 히트 요청 시 한장을 더 분배한다.")
        @Test
        void 플레이어_카드_합_21_미만_히트_요청_시_한장_더_분배한다() {
            //given
            //when
            distributePlayerCards(onePlayerGame, new Name("피즈"),
                    new Card(Rank.ACE, Suit.CLOVER),
                    new Card(Rank.FOUR, Suit.CLOVER),
                    new Card(Rank.EIGHT, Suit.CLOVER)
            );
            //then
            onePlayerGame.playPlayerTurn(new Name("피즈"), deck, true);

            assertThat(onePlayerGame.getPlayerCard(new Name("피즈")).size()).isEqualTo(4);
        }

        @DisplayName("플레이어가 히트를 원하지 않으면 카드를 추가로 받지 않는다.")
        @Test
        void 플레이어_히트_거절시_카드_추가되지_않는다() {
            //given
            onePlayerGame.initializeGame(deck);
            int beforeCardCount = onePlayerGame.getPlayerCard(new Name("피즈")).size();

            //when
            onePlayerGame.playPlayerTurn(new Name("피즈"), deck, false);

            //then
            assertThat(onePlayerGame.getPlayerCard(new Name("피즈")).size()).isEqualTo(beforeCardCount);
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
            onePlayerGame.playDealerTurn(deck);

            assertThat(dealer.getHand().size()).isEqualTo(4);
        }

        @DisplayName("플레이어와 딜러가 분배된 카드를 알맞게 가지고 있는지 확인한다.")
        @Test
        void 플레이어_딜러_카드_정상_분배_확인한다() {
            //given
            onePlayerGame.initializeGame(deck);
            //when
            onePlayerGame.playPlayerTurn(new Name("피즈"), deck, true);
            onePlayerGame.playDealerTurn(deck);
            //then
            assertCardDistribution(
                    onePlayerGame.getPlayerCard(new Name("피즈")),
                    List.of(Rank.FIVE, Rank.FIVE, Rank.FOUR),
                    List.of("클로버", "하트", "스페이드")
            );

            assertCardDistribution(
                    onePlayerGame.getDealerCard(),
                    List.of(Rank.SIX, Rank.SEVEN, Rank.SEVEN),
                    List.of("클로버", "클로버", "하트")
            );
        }

        @DisplayName("딜러가 처음에 받은 카드 2장으로 바로 블랙잭이 되면 카드를 추가로 더 분배 받지 않는다.")
        @Test
        void 딜러_처음_2장_블랙잭_카드_추가_분배_받지_않는다() {
            //given
            onePlayerGame.initializeGame(delaerBlackjackDeck);
            //when
            //then
            onePlayerGame.playDealerTurn(delaerBlackjackDeck);
            onePlayerGame.playDealerTurn(delaerBlackjackDeck);

            assertSoftly(softly -> {
                assertThat(onePlayerGame.getDealerScore()).isEqualTo(21);
                assertThat(onePlayerGame.getDealerCard().size()).isEqualTo(2);
            });
        }

        private void assertCardDistribution(List<Card> cards, List<Rank> expectedRank, List<String> expectedSuit) {
            for (int i = 0; i < cards.size(); i++) {
                int finalI = i;
                assertSoftly(softly -> {
                    assertThat(cards.get(finalI).getRank()).isEqualTo(expectedRank.get(finalI));
                    assertThat(cards.get(finalI).getSuitString()).isEqualTo(expectedSuit.get(finalI));
                });
            }
        }
    }

    @Nested
    @DisplayName("게임 결과 정상 판정")
    class gameResultJudgementSuccess {
        @DisplayName("플레이어가 버스트 되면 플레이어는 패배하고 딜러는 승리한다.")
        @Test
        void 플레이어_버스트_시_무조건_플레이어는_패배한다() {
            distributePlayerCards(onePlayerGame, new Name("피즈"),
                    new Card(Rank.JACK, Suit.CLOVER),
                    new Card(Rank.QUEEN, Suit.CLOVER),
                    new Card(Rank.EIGHT, Suit.CLOVER)
            );

            assertSoftly(softly -> {
                assertThat(onePlayerGame.getPlayerResult(new Name("피즈"))).isEqualTo(GameResult.LOSE);
                assertThat(onePlayerGame.getDealerResult().get(GameResult.WIN)).isEqualTo(1);
            });
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 높으면 플레이어가 승리하고 딜러는 패배한다.")
        @Test
        void 플레이어_점수_더_높으면_플레이어_승리한다() {
            distributePlayerCards(onePlayerGame, new Name("피즈"),
                    new Card(Rank.JACK, Suit.CLOVER),
                    new Card(Rank.QUEEN, Suit.CLOVER),
                    new Card(Rank.ACE, Suit.CLOVER)
            );

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            assertSoftly(softly -> {
                assertThat(onePlayerGame.getPlayerResult(new Name("피즈"))).isEqualTo(GameResult.WIN);
                assertThat(onePlayerGame.getDealerResult().get(GameResult.LOSE)).isEqualTo(1);
            });
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 딜러가 버스트된 경우 플레이어가 승리한다.")
        @Test
        void 딜러가_버스트된_경우_플레이어가_승리한다() {

            distributePlayerCards(onePlayerGame, new Name("피즈"),
                    new Card(Rank.JACK, Suit.CLOVER),
                    new Card(Rank.QUEEN, Suit.CLOVER)
            );

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.SIX, Suit.HEART));
            dealer.addCard(new Card(Rank.SEVEN, Suit.HEART));

            assertSoftly(softly -> {
                assertThat(onePlayerGame.getPlayerResult(new Name("피즈"))).isEqualTo(GameResult.WIN);
                assertThat(onePlayerGame.getDealerResult().get(GameResult.LOSE)).isEqualTo(1);
            });
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어의 점수가 더 낮으면 플레이어가 패배하고 딜러는 승리한다.")
        @Test
        void 플레이어_점수_더_높으면_플레이어_패배한다() {
            distributePlayerCards(onePlayerGame, new Name("피즈"),
                    new Card(Rank.JACK, Suit.CLOVER),
                    new Card(Rank.NINE, Suit.CLOVER)
            );

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            assertSoftly(softly -> {
                assertThat(onePlayerGame.getPlayerResult(new Name("피즈"))).isEqualTo(GameResult.LOSE);
                assertThat(onePlayerGame.getDealerResult().get(GameResult.WIN)).isEqualTo(1);
            });
        }

        @DisplayName("플레이어가 버스트 되지 않았을 때 플레이어와 딜러의 점수가 같으면 무승부가 된다.")
        @Test
        void 플레이어_딜러_점수_같으면_무승부_된다() {

            distributePlayerCards(onePlayerGame, new Name("피즈"),
                    new Card(Rank.JACK, Suit.CLOVER),
                    new Card(Rank.QUEEN, Suit.CLOVER)
            );

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            //then
            assertSoftly(softly -> {
                assertThat(onePlayerGame.getPlayerResult(new Name("피즈"))).isEqualTo(GameResult.DRAW);
                assertThat(onePlayerGame.getDealerResult().get(GameResult.DRAW)).isEqualTo(1);
            });
        }

        @DisplayName("플레이어가 카드 2장으로 블랙잭이 되어 승리하면 BLACKJACK_WIN이 된다")
        @Test
        void 플레이어_카드_2장_블랙잭_승리_시_블랙잭_승리_된다() {
            //given
            distributePlayerCards(onePlayerGame, new Name("피즈"),
                    new Card(Rank.JACK, Suit.CLOVER),
                    new Card(Rank.ACE, Suit.CLOVER)
            );

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.QUEEN, Suit.HEART));

            assertSoftly(softly -> {
                assertThat(onePlayerGame.getPlayerResult(new Name("피즈"))).isEqualTo(GameResult.BLACKJACK_WIN);
                assertThat(onePlayerGame.getDealerResult().get(GameResult.LOSE)).isEqualTo(1);
            });
        }

        @DisplayName("딜러가 블랙잭이고 플레이어가 카드 3장으로 21점이 되면 플레이어가 패배한다.")
        @Test
        void 딜러_블랙잭_플레이어_패배() {
            //given
            distributePlayerCards(onePlayerGame, new Name("피즈"),
                    new Card(Rank.JACK, Suit.CLOVER),
                    new Card(Rank.EIGHT, Suit.CLOVER),
                    new Card(Rank.THREE, Suit.CLOVER)
            );

            dealer.addCard(new Card(Rank.JACK, Suit.HEART));
            dealer.addCard(new Card(Rank.ACE, Suit.HEART));

            assertSoftly(softly -> {
                assertThat(onePlayerGame.getPlayerResult(new Name("피즈"))).isEqualTo(GameResult.LOSE);
                assertThat(onePlayerGame.getDealerResult().get(GameResult.WIN)).isEqualTo(1);
            });
        }
    }

    private void distributePlayerCards(Game game, Name name, Card... cards) {
        Deck testDeck = new Deck(Arrays.asList(cards));
        for (int i = 0; i < cards.length; i++) {
            game.playPlayerTurn(name, testDeck, true);
        }
    }
}
