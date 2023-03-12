package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackJackGameTest {

    private Players players;
    private BettingTable bettingTable;

    @BeforeEach
    void init() {
        players = Players.create(List.of("gray", "luca", "bada"));
        Map<String, Betting> bettingInfo = new HashMap<>();

        List<String> names = players.getNames();
        for (String name : names) {
            bettingInfo.put(name, new Betting(10000));
        }
        bettingTable = new BettingTable(bettingInfo);
    }

    @Test
    @DisplayName("블랙잭 게임이 정상적으로 생성된다.")
    void createBlackjackGame() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);

        assertThat(blackJackGame).isNotNull();
    }

    @Test
    @DisplayName("게임에 참여중인 플레이어들의 이름을 반환한다.")
    void getPlayersName() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);

        assertThat(blackJackGame.getPlayerNames()).containsExactly("gray", "luca", "bada");
    }

    @Test
    @DisplayName("초기에 딜러와 플레이어에게 2장씩 카드를 나눠준다.")
    void passCard() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);

        blackJackGame.setUp();
        List<Player> players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();

        Assertions.assertAll(
                () -> assertThat(players.get(0).getCards().getCount()).isEqualTo(2),
                () -> assertThat(players.get(1).getCards().getCount()).isEqualTo(2),
                () -> assertThat(players.get(2).getCards().getCount()).isEqualTo(2),
                () -> assertThat(dealer.getCards().getCount()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("플레어어에게 카드를 한 장 나눠준다.")
    void passExtraCardToPlayer() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);
        List<Player> players = this.players.getPlayers();
        Player gray = players.get(0);
        Player luca = players.get(1);

        blackJackGame.passCardTo(gray);
        blackJackGame.passCardTo(luca);
        blackJackGame.passCardTo(luca);

        assertAll(
                () -> assertThat(gray.getCards().getCount()).isEqualTo(1),
                () -> assertThat(luca.getCards().getCount()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("딜러가 16점 이하이면 카드 한 장을 나눠준다.")
    void passExtraCardToDealer() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);
        Dealer dealer = blackJackGame.getDealer();
        dealer.addCard(new Card(Suit.SPADE, Denomination.TEN));
        int beforeCount = dealer.getCards().getCount();

        blackJackGame.passCardTo(dealer);

        assertThat(dealer.getCards().getCount()).isEqualTo(beforeCount + 1);
    }

    @Test
    @DisplayName("딜러가 17점 이상이면 카드 나눠줄 수 없다.")
    void canNotPassExtraCardToDealer() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);
        Dealer dealer = blackJackGame.getDealer();
        dealer.addCard(new Card(Suit.SPADE, Denomination.TEN));
        dealer.addCard(new Card(Suit.HEART, Denomination.SEVEN));
        int beforeCount = dealer.getCards().getCount();

        blackJackGame.passCardTo(dealer);

        assertThat(dealer.getCards().getCount()).isEqualTo(beforeCount);
    }

    @Test
    @DisplayName("플레이어의 수익을 계산한다.")
    void calculatePlayerProfit() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);
        List<Player> players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        Player gray = players.get(0);
        Player luca = players.get(1);
        Player bada = players.get(2);

        setCards(dealer, gray, luca, bada);
        Map<String, Integer> result = blackJackGame.calculatePlayersProfit();

        Assertions.assertAll(
                () -> assertThat(result).containsEntry(gray.getName(), 15000),
                () -> assertThat(result).containsEntry(luca.getName(), -10000),
                () -> assertThat(result).containsEntry(bada.getName(), 0)
        );
    }

    @Test
    @DisplayName("딜러가 처음에 블랙잭인 경우, 플레이어의 수익을 계산한다.")
    void calculateProfitWhenDealerBlackJack() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);
        List<Player> players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        Player gray = players.get(0);
        Player luca = players.get(1);
        Player bada = players.get(2);

        setDealCardBlackJack(dealer, gray, luca, bada);
        Map<String, Integer> result = blackJackGame.calculatePlayersProfit();

        Assertions.assertAll(
                () -> assertThat(result).containsEntry(gray.getName(), 0),
                () -> assertThat(result).containsEntry(luca.getName(), -10000),
                () -> assertThat(result).containsEntry(bada.getName(), -10000)
        );
    }

    @Test
    @DisplayName("딜러의 수익을 계산한다.")
    void calculateDealerProfit() {
        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);
        List<Player> players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        Player gray = players.get(0);
        Player luca = players.get(1);
        Player bada = players.get(2);

        setCards(dealer, gray, luca, bada);
        int dealerProfit = blackJackGame.calculateDealerProfit();

        assertThat(dealerProfit).isEqualTo(-5000);
    }

    private void setCards(Dealer dealer, Player gray, Player luca, Player bada) {
        dealer.addCard(new Card(Suit.HEART, Denomination.TEN));
        dealer.addCard(new Card(Suit.HEART, Denomination.SEVEN));
        gray.addCard(new Card(Suit.SPADE, Denomination.ACE));
        gray.addCard(new Card(Suit.SPADE, Denomination.TEN));
        luca.addCard(new Card(Suit.DIAMOND, Denomination.SIX));
        luca.addCard(new Card(Suit.DIAMOND, Denomination.TEN));
        bada.addCard(new Card(Suit.HEART, Denomination.TEN));
        bada.addCard(new Card(Suit.HEART, Denomination.SEVEN));
    }

    private void setDealCardBlackJack(Dealer dealer, Player gray, Player luca, Player bada) {
        dealer.addCard(new Card(Suit.HEART, Denomination.TEN));
        dealer.addCard(new Card(Suit.HEART, Denomination.ACE));
        gray.addCard(new Card(Suit.SPADE, Denomination.ACE));
        gray.addCard(new Card(Suit.SPADE, Denomination.TEN));
        luca.addCard(new Card(Suit.DIAMOND, Denomination.SIX));
        luca.addCard(new Card(Suit.DIAMOND, Denomination.TEN));
        bada.addCard(new Card(Suit.HEART, Denomination.TEN));
        bada.addCard(new Card(Suit.HEART, Denomination.SEVEN));
    }
}
