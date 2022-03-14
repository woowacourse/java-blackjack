package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    private Card twoSpade;
    private Card threeSpade;
    private Card queenSpade;
    private Dealer dealer;
    private Player player;

    @BeforeEach
    void before() {
        dealer = new Dealer();
        player = new Player("pobi");
        twoSpade = new Card(CardNumber.TWO, CardShape.SPADE);
        threeSpade = new Card(CardNumber.THREE, CardShape.SPADE);
        queenSpade = new Card(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("딜러의 카드 총 합이 16이하일 경우 True 를 반환하는지 확인한다.")
    @Test
    void isGiven_true() {
        List<Card> cards = new ArrayList<>(List.of(twoSpade, threeSpade));

        Dealer dealer = new Dealer();
        dealer.dealInit(cards);

        final boolean given = dealer.isPossibleToDraw();
        assertThat(given).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 17 이상일 경우 False 를 반환하는지 확인한다.")
    @Test
    void isGiven_false() {
        List<Card> cards = new ArrayList<>(List.of(twoSpade, threeSpade, queenSpade, threeSpade));

        Dealer dealer = new Dealer();
        dealer.dealInit(cards);

        final boolean given = dealer.isPossibleToDraw();
        assertThat(given).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 큰 경우를 확인한다.")
    @Test
    void compare_true() {
        List<Card> playerCards = new ArrayList<>(List.of(twoSpade, threeSpade));
        List<Card> dealerCards = new ArrayList<>(List.of(twoSpade, threeSpade, threeSpade));
        player.dealInit(playerCards);
        dealer.dealInit(dealerCards);

        final boolean compare = dealer.compare(player);
        assertThat(compare).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 같은 경우를 확인한다.")
    @Test
    void compare_same() {
        List<Card> playerCards = new ArrayList<>(List.of(twoSpade, threeSpade));
        List<Card> dealerCards = new ArrayList<>(List.of(twoSpade, threeSpade));
        player.dealInit(playerCards);
        dealer.dealInit(dealerCards);

        final boolean compare = dealer.compare(player);
        assertThat(compare).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 작은 경우를 확인한다.")
    @Test
    void compare_false() {
        List<Card> playerCards = new ArrayList<>(List.of(twoSpade, threeSpade, threeSpade));
        List<Card> dealerCards = new ArrayList<>(List.of(twoSpade, threeSpade));
        player.dealInit(playerCards);
        dealer.dealInit(dealerCards);

        final boolean compare = dealer.compare(player);
        assertThat(compare).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 16 이하일 경우 카드를 더 받을 수 있는지 확인한다.")
    @Test
    void isReceived_true() {
        List<Card> dealerCards = new ArrayList<>(List.of(threeSpade, threeSpade, queenSpade));
        dealer.dealInit(dealerCards);

        final boolean received = dealer.isPossibleToDraw();
        assertThat(received).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 16 초과일 경우 카드를 받을 수 없는 것을 확인한다.")
    @Test
    void isReceived_false() {
        List<Card> dealerCards = new ArrayList<>(List.of(twoSpade, twoSpade, threeSpade, queenSpade));
        dealer.dealInit(dealerCards);

        final boolean received = dealer.isPossibleToDraw();
        assertThat(received).isFalse();
    }
}
