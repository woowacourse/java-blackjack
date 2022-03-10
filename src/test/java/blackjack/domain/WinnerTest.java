package blackjack.domain;

import blackjack.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinnerTest {

    private Card twoSpade;
    private Card threeSpade;
    private Card queenSpade;

    @BeforeEach
    void init() {
        twoSpade = new Card(CardNumber.TWO, CardShape.SPADE);
        threeSpade = new Card(CardNumber.THREE, CardShape.SPADE);
        queenSpade = new Card(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("플레이어의 카드합이 21을 넘길 경우 승자에 포함되지 않는 것을 확인한다.")
    @Test
    void burst_player() {
        List<Card> dealerCards = new ArrayList<>(List.of(twoSpade, twoSpade));
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(dealerCards);

        List<Card> playerCards = new ArrayList<>(List.of(queenSpade, queenSpade, twoSpade));
        Player player = new Player("pobi");
        player.receiveInitCard(playerCards);

        Winner winner = new Winner();
        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isFalse();
    }

    @DisplayName("플레이어의 값이 클 경우 비교하여 승자를 확인한다.")
    @Test
    void winner_player() {
        List<Card> dealerCards = new ArrayList<>(List.of(twoSpade, twoSpade));
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(dealerCards);

        List<Card> playerCards = new ArrayList<>(List.of(twoSpade, threeSpade));
        Player player = new Player("pobi");
        player.receiveInitCard(playerCards);

        Winner winner = new Winner();
        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isTrue();
    }

    @DisplayName("플레이어의 값이 적을 경우 비교하여 승자를 확인한다.")
    @Test
    void winner_dealer() {
        List<Card> dealerCards = new ArrayList<>(List.of(twoSpade, threeSpade));
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(dealerCards);

        List<Card> playerCards = new ArrayList<>(List.of(twoSpade, twoSpade));
        Player player = new Player("pobi");
        player.receiveInitCard(playerCards);

        Winner winner = new Winner();
        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isFalse();
    }

    @DisplayName("딜러의 값과 플레이어의 값이 같을 경우 승자를 확인한다.")
    @Test
    void equals_score() {
        List<Card> dealerCards = new ArrayList<>(List.of(twoSpade, twoSpade, twoSpade));
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(dealerCards);

        List<Card> playerCards = new ArrayList<>(List.of(twoSpade, twoSpade, twoSpade));
        Player player = new Player("pobi");
        player.receiveInitCard(playerCards);

        Winner winner = new Winner();
        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isFalse();
    }

    @DisplayName("플레이어가 21을 초과하지 않았을 때 딜러가 21을 초과할 경우 승자를 확인한다.")
    @Test
    void burst_dealer() {
        List<Card> dealerCards = new ArrayList<>(List.of(queenSpade, queenSpade, queenSpade));
        Dealer dealer = new Dealer();
        dealer.receiveInitCard(dealerCards);

        List<Card> playerCards = new ArrayList<>(List.of(twoSpade, twoSpade, twoSpade));
        Player player = new Player("pobi");
        player.receiveInitCard(playerCards);

        Winner winner = new Winner();
        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isTrue();
    }
}