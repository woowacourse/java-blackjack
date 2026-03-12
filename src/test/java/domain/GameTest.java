package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.Rank;
import domain.enums.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
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
    private Dealer dealer;
    private Deck deck;
    private Deck delaerBlackjackDeck;

    @BeforeEach
    void setUp() {
        CardGenerator cardGenerator1 = new TestCardGenerator();
        CardGenerator cardGenerator2 = new DealerBlackjackCardGenerator();
        deck = new Deck(cardGenerator1.generate());
        delaerBlackjackDeck = new Deck(cardGenerator2.generate());

        dealer = new Dealer();

        twoPlayerGame = new Game(List.of("피즈", "스타크"), dealer, deck.getCards());
        onePlayerGame = new Game(List.of("피즈"), dealer, deck.getCards());
    }

    @Nested
    @DisplayName("게임 정상 초기화")
    class gameInitializeSuccess {
        @DisplayName("게임 시작 후 모든 플레이어가 2장의 카드를 분배 받는다.")
        @Test
        void 게임_시작_후_모든_플레이어_2장의_카드_분배를_받는다() {
            //given
            //when
            twoPlayerGame.initializeGame();
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
            List<Card> cards = List.of(
                    new Card(Rank.ACE, Suit.CLOVER),
                    new Card(Rank.FOUR, Suit.CLOVER),
                    new Card(Rank.SIX, Suit.CLOVER),
                    new Card(Rank.SEVEN, Suit.CLOVER),
                    new Card(Rank.EIGHT, Suit.CLOVER),
                    new Card(Rank.TWO, Suit.CLOVER)
            );
            Game game = new Game(List.of("피즈"), new Dealer(), cards);

            game.initializeGame();
            game.playPlayerTurn(new Name("피즈"), true);
            game.playPlayerTurn(new Name("피즈"), true);

            assertThat(game.getPlayerCard(new Name("피즈")).size()).isEqualTo(4);
        }

        @DisplayName("플레이어가 히트를 원하지 않으면 카드를 추가로 받지 않는다.")
        @Test
        void 플레이어_히트_거절시_카드_추가되지_않는다() {
            //given
            onePlayerGame.initializeGame();
            int beforeCardCount = onePlayerGame.getPlayerCard(new Name("피즈")).size();

            //when
            onePlayerGame.playPlayerTurn(new Name("피즈"), false);

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
            onePlayerGame.playDealerTurn();

            assertThat(dealer.getHand().size()).isEqualTo(4);
        }

        @DisplayName("플레이어와 딜러가 분배된 카드를 알맞게 가지고 있는지 확인한다.")
        @Test
        void 플레이어_딜러_카드_정상_분배_확인한다() {
            //given
            onePlayerGame.initializeGame();
            //when
            onePlayerGame.playPlayerTurn(new Name("피즈"), true);
            onePlayerGame.playDealerTurn();
            //then
            assertCardDistribution(
                    onePlayerGame.getPlayerCard(new Name("피즈")),
                    List.of(Rank.FIVE, Rank.FIVE, Rank.FOUR),
                    List.of("클로버", "하트", "스페이드")
            );

            assertCardDistribution(
                    dealer.getHand(),
                    List.of(Rank.SIX, Rank.SEVEN, Rank.SEVEN),
                    List.of("클로버", "클로버", "하트")
            );
        }

        @DisplayName("딜러가 처음에 받은 카드 2장으로 바로 블랙잭이 되면 카드를 추가로 더 분배 받지 않는다.")
        @Test
        void 딜러_처음_2장_블랙잭_카드_추가_분배_받지_않는다() {
            //given
            Game blackjackGame = new Game(List.of("피즈"), dealer, delaerBlackjackDeck.getCards());
            blackjackGame.initializeGame();
            //when
            //then
            blackjackGame.playDealerTurn();
            blackjackGame.playDealerTurn();

            assertSoftly(softly -> {
                assertThat(dealer.getScore()).isEqualTo(21);
                assertThat(dealer.getHand().size()).isEqualTo(2);
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
}
