package blackjack.model;

import static blackjack.TestFixtures.DEALER;
import static blackjack.TestFixtures.UNSHUFFLED_DECK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import blackjack.model.card.Deck;
import blackjack.model.card.FixedCardShuffler;
import blackjack.model.card.Suit;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임 테스트")
class GameTest {

    @DisplayName("딜러와 플레이어와 덱을 가진다.")
    @Test
    void createTest() {
        // given
        Deck deck = Deck.createShuffledDeck(Card.createDeck(), new FixedCardShuffler());
        List<Player> players = List.of(new Player("pobi"), new Player("neo"));

        // when, then
        assertThatCode(() -> new Game(new Dealer(deck), players))
                .doesNotThrowAnyException();
    }

    @DisplayName("모든 참가자에게 2장의 패를 준다.")
    @Test
    void dealInitialCardsTest() {
        // given
        Deck deck = Deck.createShuffledDeck(Card.createDeck(), new FixedCardShuffler());
        Dealer dealer = new Dealer(deck);
        List<Player> players = List.of(new Player("pobi"), new Player("neo"));
        Game game = new Game(dealer, players);

        // when
        game.dealInitialCards();

        // then
        assertThat(players)
                .allSatisfy(player -> assertThat(player.getHand()).hasSize(2));
        assertThat(dealer.getHand())
                .hasSize(2);
    }

    @DisplayName("플레이어들이 원하면 카드를 더 준다")
    @Test
    void playTurnForAllPlayerTest() {
        // given
        Player pobi = new Player("pobi");
        List<Player> players = List.of(pobi);
        Game game = new Game(DEALER, players);
        game.dealInitialCards();

        //when
        game.hitPlayer(pobi);

        // then
        assertThat(players)
                .allSatisfy(player -> assertThat(player.getHand()).hasSize(3));
    }

    @DisplayName("딜러가 패 한장을 공개한다.")
    @Test
    void getDealerVisibleCardTest() {
        // given
        Card spadeFive = new Card(Suit.SPADES, CardValue.FIVE);
        Card spadeTen = new Card(Suit.SPADES, CardValue.TEN);
        Deck deck = Deck.createShuffledDeck(List.of(spadeFive, spadeTen, spadeFive, spadeTen), new FixedCardShuffler());
        Dealer dealer = new Dealer(deck);
        Game game = new Game(dealer, List.of(new Player("pobi")));
        game.dealInitialCards();

        //when
        Card dealerVisibleCard = game.getDealerVisibleCard();

        // then
        assertThat(dealerVisibleCard)
                .isEqualTo(spadeFive);
    }

    @DisplayName("버스트인 경우 히트 여부를 묻지 않는다.")
    @Test
    void playTurn_ForAllPlayer_WhenBustTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.TWO),
                new Card(Suit.SPADES, CardValue.FIVE),
                new Card(Suit.HEARTS, CardValue.TWO)
        );
        Deck deck = Deck.createShuffledDeck(cards, new FixedCardShuffler());
        Dealer dealer = new Dealer(deck);
        List<Player> players = List.of(new Player("pobi"));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        //when
        for (Player player : players) {
            game.hitPlayer(player);
        }

        // then
        assertThat(players)
                .allSatisfy(player -> assertThat(player.getHand()).hasSize(3));
    }

    @DisplayName("딜러가 가진 패의 합이 16 이하인 경우 카드 한장을 추가로 받는다.")
    @Test
    void dealerHitTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.SIX),
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.FOUR)
        );
        Deck deck = Deck.createShuffledDeck(cards, new FixedCardShuffler());
        Dealer dealer = new Dealer(deck);
        List<Player> players = List.of(new Player("pobi"));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        //when
        boolean isDealerHit = game.hitDealer();

        // then
        assertThat(isDealerHit)
                .isSameAs(true);
        assertThat(dealer.getHand())
                .hasSize(3);
    }

    @DisplayName("딜러가 가진 패의 합이 16 초과인 경우 카드 한장을 추가로 받지 않는다.")
    @Test
    void dealerHitWhenOver16Test() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.SEVEN),
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.FOUR)
        );
        Deck deck = Deck.createShuffledDeck(cards, new FixedCardShuffler());
        Dealer dealer = new Dealer(deck);
        List<Player> players = List.of(new Player("pobi"));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        //when
        boolean isDealerHit = game.hitDealer();

        // then
        assertThat(isDealerHit)
                .isSameAs(false);
        assertThat(dealer.getHand())
                .hasSize(2);
    }

    @DisplayName("모든 플레이어의 승부를 판단한다.")
    @Test
    void judgeMatchResultsTest() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.SEVEN),
                new Card(Suit.SPADES, CardValue.TEN)
        );
        Deck deck = Deck.createShuffledDeck(cards, new FixedCardShuffler());
        Dealer dealer = new Dealer(deck);
        List<Player> players = List.of(new Player("pobi"));
        Game game = new Game(dealer, players);
        game.dealInitialCards();

        // when
        Map<Player, MatchResult> playerMatchResults = game.judgeMatchResults();

        // then
        assertThat(playerMatchResults.get(new Player("pobi")))
                .isSameAs(MatchResult.WIN);
    }
}
