package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    private Deck blackJackDeck;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        blackJackDeck = Deck.create();
        dealer = new Dealer(
                new Hand(List.of(new TrumpCard(Rank.SIX, Suit.CLUBS), new TrumpCard(Rank.SIX, Suit.SPADES))));
    }

    @Nested
    class ValidCases {

        @Test
        @DisplayName("플레이어가 히트하면 카드가 추가된다.")
        void processPlayerHit() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer);
            Player player = new Player("Alice",
                    Hand.of(new TrumpCard(Rank.TWO, Suit.DIAMONDS), new TrumpCard(Rank.FIVE, Suit.SPADES)));

            // when
            blackJackGame.processPlayerHit(player);

            // then
            assertThat(player.getHand().getCards()).hasSize(3);
        }

        @Test
        @DisplayName("딜러가 히트하면 적절한 횟수만큼 카드가 추가된다.")
        void processDealerHit() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer);
            int initialCards = dealer.getHand().getCards().size();

            // when
            int hitCount = blackJackGame.processDealerHit();

            // then
            assertThat(dealer.getHand().getCards()).hasSize(initialCards + hitCount);
        }


        @Test
        @DisplayName("게임 결과를 올바르게 계산한다.")
        void calculateGameResults() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer);
            Player player1 = new Player("Alice",
                    Hand.of(new TrumpCard(Rank.TEN, Suit.DIAMONDS), new TrumpCard(Rank.JACK, Suit.HEARTS)));
            Player player2 = new Player("Bob",
                    Hand.of(new TrumpCard(Rank.NINE, Suit.CLUBS), new TrumpCard(Rank.SEVEN, Suit.DIAMONDS)));
            List<Player> players = List.of(player1, player2);

            // when
            Map<String, GameResult> results = blackJackGame.calculateGameResults(players);

            // then
            assertThat(results).containsKeys("Alice", "Bob");
        }
    }
}
