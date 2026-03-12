package team.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    private Map<String, Integer> playerSkates;
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        playerSkates = Map.of("pobi", 10000, "jason", 20000);
        blackjackGame = new BlackjackGame(playerSkates);
    }

    @Test
    void 플레이어는_처음_게임_시작시에_서로_다른_2장의_카드를_받는다() {
        Player newPlayer = new Player("pobi", 10000);

        blackjackGame.dealInitialCardsTo(newPlayer);

        assertThat(newPlayer.getHands().getFirst().getCards().size()).isEqualTo(2);
    }

    @Test
    void 딜러는_처음_게임_시작시에_서로_다른_2장의_카드를_받는다() {
        Dealer dealer = new Dealer();

        blackjackGame.dealInitialCardsTo(dealer);

        assertThat(dealer.getHand().getCards().size()).isEqualTo(2);
    }

    @Test
    void 플레이어에게_카드_한장을_발급한다() {
        Player player = new Player("pobi", 10000);

        blackjackGame.dealCardTo(player);

        assertThat(player.getHands().getFirst().getCards().size()).isEqualTo(1);
    }

    @Test
    void 딜러에게_카드_한장을_발급한다() {
        Dealer dealer = new Dealer();

        blackjackGame.dealCardTo(dealer);

        assertThat(dealer.getHand().getCards().size()).isEqualTo(1);
    }

    /**
     * 플레이어 점수 계산 테스트
     */
    @Test
    void 플레이어가_딜러의_점수보다_큰_경우_WIN을_반환한다() {
        Player player = new Player("pobi", 10000);
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.HEARTS, Rank.KING)
        );

        List<Card> dealerCards = List.of(
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.HEARTS, Rank.EIGHT)
        );

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

        assertThat(blackjackGame.getPlayerResult(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어와_딜러의_점수가_동일할때_승패를_DRAW로_반환한다() {
        Player player = new Player("pobi", 10000);
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.HEARTS, Rank.KING)
        );

        List<Card> dealerCards = List.of(
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.CLUBS, Rank.KING)
        );

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

        assertThat(blackjackGame.getPlayerResult(player, dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    void 플레이어가_딜러의_점수보다_작을때_승패를_LOSE로_반환한다() {
        Player player = new Player("pobi", 10000);
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.HEARTS, Rank.KING)
        );

        List<Card> dealerCards = List.of(
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.CLUBS, Rank.ACE)
        );

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

        assertThat(blackjackGame.getPlayerResult(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    void 플레이어가_버스트인_경우_플레이어는_반드시_패배한다() {
        Player player = new Player("pobi", 10000);
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.HEARTS, Rank.KING),
                Card.of(Suit.CLUBS, Rank.KING)
        );

        List<Card> dealerCards = List.of(
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.CLUBS, Rank.TEN)
        );

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

        assertThat(blackjackGame.getPlayerResult(player, dealer)).isEqualTo(Result.LOSE);
    }

    /**
     * 딜러 점수 계산 테스트
     */
    @Test
    void 딜러가_플레이어의_점수보다_큰_경우_WIN을_반환한다() {
        Player player = new Player("pobi", 10000);
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.HEARTS, Rank.EIGHT)
        );

        List<Card> dealerCards = List.of(
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.HEARTS, Rank.KING)
        );

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

        assertThat(blackjackGame.getDealerResult(dealer, player)).isEqualTo(Result.WIN);
    }

    @Test
    void 딜러와_플레이어의_점수가_동일할때_승패를_DRAW로_반환한다() {
        Player player = new Player("pobi", 10000);
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.HEARTS, Rank.KING)
        );

        List<Card> dealerCards = List.of(
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.CLUBS, Rank.KING)
        );

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

        assertThat(blackjackGame.getDealerResult(dealer, player)).isEqualTo(Result.DRAW);
    }

    @Test
    void 딜러가_플레이어의_점수보다_작을때_승패를_LOSE로_반환한다() {
        Player player = new Player("pobi", 10000);
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.HEARTS, Rank.KING)
        );

        List<Card> dealerCards = List.of(
                Card.of(Suit.DIAMONDS, Rank.NINE),
                Card.of(Suit.CLUBS, Rank.KING)
        );

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

        assertThat(blackjackGame.getDealerResult(dealer, player)).isEqualTo(Result.LOSE);
    }

    @Test
    void 플레이어와_딜러_모두_버스트인_경우_딜러가_승리한다() {
        Player player = new Player("pobi", 10000);
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.HEARTS, Rank.KING),
                Card.of(Suit.CLUBS, Rank.KING)
        );

        List<Card> dealerCards = List.of(
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.CLUBS, Rank.TEN),
                Card.of(Suit.DIAMONDS, Rank.TEN)
        );

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

        assertThat(blackjackGame.getPlayerResult(player, dealer)).isEqualTo(Result.LOSE);
        assertThat(blackjackGame.getDealerResult(dealer, player)).isEqualTo(Result.WIN);
    }
}
