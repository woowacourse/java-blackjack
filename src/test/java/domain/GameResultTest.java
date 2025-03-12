package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("딜러와 플레이어 둘 다 블랙잭인 경우")
    @Test
    void bothHasBlackjack() {
        Hand blackjackHand = createHand(List.of(
                new TrumpCard(Rank.ACE, Suit.SPADES),
                new TrumpCard(Rank.KING, Suit.SPADES)
        ));

        Player player = new Player("slinky", blackjackHand);
        Dealer dealer = new Dealer(blackjackHand);

        assertThat(GameResult.from(player, dealer)).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("플레이어가 버스트인 경우")
    @Test
    void playerIsBust() {
        Hand playerHand = createHand(List.of(
                new TrumpCard(Rank.KING, Suit.CLUBS),
                new TrumpCard(Rank.KING, Suit.SPADES),
                new TrumpCard(Rank.QUEEN, Suit.SPADES)
        ));
        Hand dealerHand = createHand(List.of(
                new TrumpCard(Rank.ACE, Suit.HEARTS),
                new TrumpCard(Rank.KING, Suit.HEARTS)
        ));

        Player player = new Player("slinky", playerHand);
        Dealer dealer = new Dealer(dealerHand);

        assertThat(GameResult.from(player, dealer)).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 버스트인 경우")
    @Test
    void dealerIsBust() {
        Hand playerHand = createHand(List.of(
                new TrumpCard(Rank.KING, Suit.CLUBS),
                new TrumpCard(Rank.QUEEN, Suit.SPADES)
        ));
        Hand dealerHand = createHand(List.of(
                new TrumpCard(Rank.KING, Suit.HEARTS),
                new TrumpCard(Rank.QUEEN, Suit.DIAMONDS),
                new TrumpCard(Rank.TWO, Suit.CLUBS)
        ));

        Player player = new Player("slinky", playerHand);
        Dealer dealer = new Dealer(dealerHand);

        assertThat(GameResult.from(player, dealer)).isEqualTo(GameResult.WIN);
        }

    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아닌 경우")
    @Test
    void playerHasBlackjack() {
        Hand playerHand = createHand(List.of(
                new TrumpCard(Rank.ACE, Suit.HEARTS),
                new TrumpCard(Rank.KING, Suit.CLUBS)
        ));
        Hand dealerHand = createHand(List.of(
                new TrumpCard(Rank.TEN, Suit.SPADES),
                new TrumpCard(Rank.NINE, Suit.HEARTS)
        ));

        Player player = new Player("slinky", playerHand);
        Dealer dealer = new Dealer(dealerHand);

        assertThat(GameResult.from(player, dealer)).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러가 블랙잭이고 플레이어가 블랙잭이 아닌 경우")
    @Test
    void dealerHasBlackjack() {
        Hand playerHand = createHand(List.of(
                new TrumpCard(Rank.TEN, Suit.CLUBS),
                new TrumpCard(Rank.NINE, Suit.SPADES)
        ));
        Hand dealerHand = createHand(List.of(
                new TrumpCard(Rank.ACE, Suit.HEARTS),
                new TrumpCard(Rank.KING, Suit.DIAMONDS)
        ));

        Player player = new Player("slinky", playerHand);
        Dealer dealer = new Dealer(dealerHand);

        assertThat(GameResult.from(player, dealer)).isEqualTo(GameResult.LOSE);
        }

    @DisplayName("딜러와 플레이어 점수가 같은 경우")
    @Test
    void sameScoreDraw() {
        Hand playerHand = createHand(List.of(
                new TrumpCard(Rank.TEN, Suit.HEARTS),
                new TrumpCard(Rank.NINE, Suit.SPADES)
        ));
        Hand dealerHand = createHand(List.of(
                new TrumpCard(Rank.TEN, Suit.CLUBS),
                new TrumpCard(Rank.NINE, Suit.DIAMONDS)
        ));

        Player player = new Player("slinky", playerHand);
        Dealer dealer = new Dealer(dealerHand);

        assertThat(GameResult.from(player, dealer)).isEqualTo(GameResult.DRAW);
        }

    @DisplayName("플레이어가 딜러보다 높은 점수를 가진 경우")
    @Test
    void playerHasHigherScore() {
        Hand playerHand = createHand(List.of(
                new TrumpCard(Rank.KING, Suit.CLUBS),
                new TrumpCard(Rank.QUEEN, Suit.HEARTS)
        ));
        Hand dealerHand = createHand(List.of(
                new TrumpCard(Rank.TEN, Suit.SPADES),
                new TrumpCard(Rank.NINE, Suit.HEARTS)
        ));

        Player player = new Player("slinky", playerHand);
        Dealer dealer = new Dealer(dealerHand);

        assertThat(GameResult.from(player, dealer)).isEqualTo(GameResult.WIN);
        }

    @DisplayName("딜러가 플레이어보다 높은 점수를 가진 경우")
    @Test
    void dealerHasHigherScore() {
        Hand playerHand = createHand(List.of(
                new TrumpCard(Rank.TEN, Suit.HEARTS),
                new TrumpCard(Rank.NINE, Suit.SPADES)
        ));
        Hand dealerHand = createHand(List.of(
                new TrumpCard(Rank.KING, Suit.CLUBS),
                new TrumpCard(Rank.QUEEN, Suit.SPADES)
        ));

        Player player = new Player("slinky", playerHand);
        Dealer dealer = new Dealer(dealerHand);

        assertThat(GameResult.from(player, dealer)).isEqualTo(GameResult.LOSE);
        }

    private Hand createHand(List<TrumpCard> cards) {
        return new Hand(cards);
        }
    }

