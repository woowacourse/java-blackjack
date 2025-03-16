package domain.participant.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Symbol;
import domain.participant.Dealer;
import domain.participant.Gamer;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetSystemTest {

    @DisplayName("플레이어는 베팅을 할 수 있다.")
    @Test
    void playerBet() {
        //given
        Player player = new Player("도기");
        long betAmount = 100;

        BetSystem betSystem = new BetSystem();

        //when //then
        assertThatCode(() -> betSystem.betting(player, betAmount))
                .doesNotThrowAnyException();
    }

    @DisplayName("베팅 금액이 0원 이하인 경우 예외가 발생한다.")
    @Test
    void validateBetAmount() {
        //given
        Player player = new Player("도기");
        long betAmount = -1;

        BetSystem betSystem = new BetSystem();

        //when //then
        assertThatThrownBy(() -> betSystem.betting(player, betAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("수익을 계산할 수 있다.")
    @Test
    void calculateProfit() {
        //given
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");

        List<Player> players = new ArrayList<>();
        players.add(pobi);
        players.add(jason);

        Dealer dealer = new Dealer();

        BetSystem betSystem = new BetSystem();
        betSystem.betting(pobi, 10000L);
        betSystem.betting(jason, 20000L);

        List<Card> cards = cardFixture();

        Deck deck = Deck.from(cards);

        dealer.prepareGame(deck);
        pobi.prepareGame(deck);
        jason.prepareGame(deck);

        pobi.hit(deck);
        dealer.hit(deck);

        //when
        Map<Gamer, Long> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual)
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                dealer, 10000L,
                                pobi, 10000L,
                                jason, -20000L
                        )
                );

    }

    @DisplayName("딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관없이 승리해 베팅 금액을 받는다.")
    @Test
    void dealerBustProfit() {
        //given
        Player dogi = new Player("도기");
        Player pobi = new Player("포비");

        List<Player> players = new ArrayList<>(
                List.of(
                        dogi,
                        pobi
                )
        );

        BetSystem betSystem = new BetSystem();
        betSystem.betting(dogi, 1000L);
        betSystem.betting(pobi, 1000L);

        List<Card> cards = new ArrayList<>(
                List.of(
                        new Card(Symbol.COLVER, Rank.JACK),
                        new Card(Symbol.COLVER, Rank.NINE),
                        new Card(Symbol.COLVER, Rank.THREE)
                )
        );

        Deck deck = Deck.from(cards);

        Dealer dealer = new Dealer();

        dealer.prepareGame(deck);
        dealer.hit(deck);

        //when
        Map<Gamer, Long> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        dealer, -2000L,
                        dogi, 1000L,
                        pobi, 1000L
                )
        );

    }

    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.")
    @Test
    void bustAllLoseBatAmount() {
        //given
        Player dogi = new Player("도기");

        List<Player> players = new ArrayList<>(
                List.of(dogi)
        );

        List<Card> cards = new ArrayList<>(
                List.of(
                        new Card(Symbol.COLVER, Rank.JACK),
                        new Card(Symbol.COLVER, Rank.FOUR),
                        new Card(Symbol.COLVER, Rank.EIGHT)
                )
        );

        Dealer dealer = new Dealer();

        Deck deck = Deck.from(cards);

        dogi.prepareGame(deck);
        dogi.hit(deck);

        BetSystem betSystem = new BetSystem();
        betSystem.betting(dogi, 1000L);

        //when
        Map<Gamer, Long> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        dealer, 1000L,
                        dogi, -1000L
                )
        );
    }

    @DisplayName("플레이어가 블랙잭인 경우 딜러에게 베팅 금액의 1.5배를 받는다.")
    @Test
    void playerBlackjackReceiveOnePointFiveForBetAmount() {
        //given
        Player player = new Player("도기");

        List<Player> players = new ArrayList<>(
                List.of(
                        player
                )
        );

        BetSystem betSystem = new BetSystem();
        betSystem.betting(player, 1000L);

        List<Card> cards = new ArrayList<>(
                List.of(
                        new Card(Symbol.COLVER, Rank.ACE),
                        new Card(Symbol.COLVER, Rank.JACK)
                )
        );

        Deck deck = Deck.from(cards);

        player.prepareGame(deck);

        Dealer dealer = new Dealer();

        //when
        Map<Gamer, Long> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        dealer, -1500L,
                        player, 2500L
                )
        );
    }

    @DisplayName("플레어와 딜러가 동시에 블랙잭인 경우 플레이어는 배팅 금액을 돌려받는다.")
    @Test
    void bothPlayerDealerBlackjackGetBackBetAmount() {
        //given
        Player player = new Player("도기");

        List<Player> players = new ArrayList<>(
                List.of(
                        player
                )
        );
        Dealer dealer = new Dealer();

        BetSystem betSystem = new BetSystem();
        betSystem.betting(player, 1000L);

        List<Card> cards = new ArrayList<>(
                List.of(
                        new Card(Symbol.COLVER, Rank.ACE),
                        new Card(Symbol.COLVER, Rank.JACK),
                        new Card(Symbol.SPADE, Rank.ACE),
                        new Card(Symbol.SPADE, Rank.JACK)
                )
        );

        Deck deck = Deck.from(cards);

        player.prepareGame(deck);
        dealer.prepareGame(deck);

        //when
        Map<Gamer, Long> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        dealer, 0L,
                        player, 0L
                ));
    }

    @DisplayName("딜러가 블랙잭인 경우 플레이어는 베팅 금액을 모두 잃는다.")
    @Test
    void dealerBlackjackPlayerAllLoseBetAmount() {
        //given
        Player player = new Player("도기");

        List<Player> players = new ArrayList<>(
                List.of(
                        player
                )
        );
        Dealer dealer = new Dealer();

        BetSystem betSystem = new BetSystem();
        betSystem.betting(player, 1000L);

        List<Card> cards = new ArrayList<>(
                List.of(
                        new Card(Symbol.COLVER, Rank.ACE),
                        new Card(Symbol.COLVER, Rank.EIGHT),
                        new Card(Symbol.SPADE, Rank.ACE),
                        new Card(Symbol.SPADE, Rank.JACK)
                )
        );

        Deck deck = Deck.from(cards);

        dealer.prepareGame(deck);
        player.prepareGame(deck);

        //when
        Map<Gamer, Long> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual)
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                dealer, 1000L,
                                player, -1000L
                        )
                );
    }

    private List<Card> cardFixture() {
        Card card = new Card(Symbol.DIAMOND, Rank.EIGHT);
        Card card1 = new Card(Symbol.COLVER, Rank.ACE);
        Card card2 = new Card(Symbol.SPADE, Rank.KING);
        Card card3 = new Card(Symbol.COLVER, Rank.SEVEN);
        Card card4 = new Card(Symbol.SPADE, Rank.EIGHT);
        Card card5 = new Card(Symbol.HEART, Rank.TWO);
        Card card6 = new Card(Symbol.COLVER, Rank.NINE);
        Card card7 = new Card(Symbol.DIAMOND, Rank.THREE);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);

        return cards;
    }

}
