package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        int betAmount = 100;

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
        int betAmount = -1;

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
        betSystem.betting(pobi, 10000);
        betSystem.betting(jason, 20000);

        List<Card> cards = cardFixture();

        Deck deck = Deck.from(cards);

        dealer.prepareGame(deck);
        pobi.prepareGame(deck);
        jason.prepareGame(deck);

        pobi.hit(deck);
        dealer.hit(deck);

        //when
        Map<Gamer, Integer> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual)
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                dealer, 10000,
                                pobi, 10000,
                                jason, -20000
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
        betSystem.betting(player, 1000);

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
        Map<Gamer, Integer> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        dealer, -1500,
                        player, 2500
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
        betSystem.betting(player, 1000);

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
        Map<Gamer, Integer> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        dealer, 0,
                        player, 0)
        );
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
        betSystem.betting(player, 1000);

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
        Map<Gamer, Integer> actual = betSystem.calculateProfit(dealer, players);

        //then
        assertThat(actual)
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                dealer, 1000,
                                player, -1000
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
