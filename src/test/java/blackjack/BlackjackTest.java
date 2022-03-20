package blackjack;

import static org.assertj.core.api.Assertions.*;

import blackjack.user.Dealer;
import blackjack.user.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    void distributeInitCardsTest() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.TWO), Card.generate(Suit.DIAMOND, Denomination.THREE),
                Card.generate(Suit.DIAMOND, Denomination.FOUR), Card.generate(Suit.DIAMOND, Denomination.FIVE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        blackjack.distributeInitCards(dealer, players);
        assertThat(dealer.getCards().numberOfCards()).isEqualTo(2);
    }

    @DisplayName("더 받을 수 있으면 TRUE리턴하는지 테스트")
    @Test
    void isPossibleToGetCardTest() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.TWO), Card.generate(Suit.DIAMOND, Denomination.THREE),
                Card.generate(Suit.DIAMOND, Denomination.FOUR), Card.generate(Suit.DIAMOND, Denomination.FIVE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        blackjack.distributeInitCards(dealer, players);
        assertThat(blackjack.isPossibleToGetCard(dealer)).isTrue();
    }

    @DisplayName("더 받을 수 없으면 false리턴하는지 테스트")
    @Test
    void isPossibleToGetCardTest2() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.TEN), Card.generate(Suit.DIAMOND, Denomination.ACE),
                Card.generate(Suit.HEART, Denomination.ACE), Card.generate(Suit.HEART, Denomination.TEN)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        blackjack.distributeInitCards(dealer, players);
        assertThat(blackjack.isPossibleToGetCard(dealer)).isFalse();
    }

    @Test
    void distributeAdditionalCardTest() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.TWO), Card.generate(Suit.DIAMOND, Denomination.THREE),
                Card.generate(Suit.DIAMOND, Denomination.FOUR), Card.generate(Suit.DIAMOND, Denomination.FIVE),
                Card.generate(Suit.SPADE, Denomination.ACE), Card.generate(Suit.SPADE, Denomination.TEN)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        blackjack.distributeInitCards(dealer, players);
        blackjack.distributeAdditionalCard(dealer);
        assertThat(dealer.getCards().numberOfCards()).isEqualTo(3);
    }

    @DisplayName("딜러가 블랙잭일때 게임 종료 컨디션 true인지 테스트")
    @Test
    void gameOverByBlackjackTest() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.ACE), Card.generate(Suit.DIAMOND, Denomination.J),
                Card.generate(Suit.DIAMOND, Denomination.Q), Card.generate(Suit.HEART, Denomination.ACE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        blackjack.distributeInitCards(dealer, players);
        assertThat(blackjack.gameOverByBlackjack(dealer)).isTrue();
    }

    @DisplayName("딜러가 블랙잭이고 플레이어도 블랙잭일때 플레이어 수익률 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.ACE), Card.generate(Suit.DIAMOND, Denomination.J),
                Card.generate(Suit.DIAMOND, Denomination.Q), Card.generate(Suit.HEART, Denomination.ACE),
                Card.generate(Suit.DIAMOND, Denomination.TWO), Card.generate(Suit.HEART, Denomination.THREE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(0);
    }

    @DisplayName("딜러가 블랙잭이고 플레이어는 21이하일때 플레이어 수익률 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest2() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.ACE), Card.generate(Suit.DIAMOND, Denomination.J),
                Card.generate(Suit.DIAMOND, Denomination.TWO), Card.generate(Suit.HEART, Denomination.THREE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(-10000);
    }

    @DisplayName("플레이어가 블랙잭으로 이겼을때 플레이어 수익률 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest3() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.TWO), Card.generate(Suit.HEART, Denomination.THREE),
                Card.generate(Suit.DIAMOND, Denomination.ACE), Card.generate(Suit.DIAMOND, Denomination.J)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(15000);
    }

    @DisplayName("버스트로 비겼을때 플레이어 수익율 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest4() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.SEVEN), Card.generate(Suit.HEART, Denomination.EIGHT),
                Card.generate(Suit.DIAMOND, Denomination.K), Card.generate(Suit.DIAMOND, Denomination.TEN),
                Card.generate(Suit.DIAMOND, Denomination.NINE), Card.generate(Suit.HEART, Denomination.THREE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        blackjack.distributeAdditionalCard(dealer);
        blackjack.distributeAdditionalCard(pobi);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(0);
    }

    @DisplayName("플레이어가 버스트로 졌을때 플레이어 수익율 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest5() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.Q), Card.generate(Suit.HEART, Denomination.FOUR),
                Card.generate(Suit.DIAMOND, Denomination.K), Card.generate(Suit.DIAMOND, Denomination.TEN),
                Card.generate(Suit.DIAMOND, Denomination.TWO), Card.generate(Suit.HEART, Denomination.THREE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        blackjack.distributeAdditionalCard(dealer);
        blackjack.distributeAdditionalCard(pobi);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(-10000);
    }

    @DisplayName("딜러가 버스트로 졌을때 플레이어 수익율 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest6() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.SEVEN), Card.generate(Suit.HEART, Denomination.EIGHT),
                Card.generate(Suit.DIAMOND, Denomination.K), Card.generate(Suit.DIAMOND, Denomination.FOUR),
                Card.generate(Suit.DIAMOND, Denomination.NINE), Card.generate(Suit.HEART, Denomination.THREE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        blackjack.distributeAdditionalCard(dealer);
        blackjack.distributeAdditionalCard(pobi);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(10000);
    }

    @DisplayName("플레이어가 이겼을때 플레이어 수익율 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest7() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.NINE), Card.generate(Suit.HEART, Denomination.NINE),
                Card.generate(Suit.DIAMOND, Denomination.K), Card.generate(Suit.DIAMOND, Denomination.J)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        blackjack.distributeAdditionalCard(dealer);
        pobi.setStateStayIfSatisfied(true);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(10000);
    }

    @DisplayName("비겼을때 플레이어 수익율 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest8() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.NINE), Card.generate(Suit.HEART, Denomination.NINE),
                Card.generate(Suit.SPADE, Denomination.NINE), Card.generate(Suit.CLOVER, Denomination.NINE)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        blackjack.distributeAdditionalCard(dealer);
        pobi.setStateStayIfSatisfied(true);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(0);
    }

    @DisplayName("플레이어가 졌을때 플레이어 수익율 정확히 계산하는지 테스트")
    @Test
    void calculateYieldTest9() {
        Deck deck = Deck.makeIntendedShuffledDeck(List.of(
                Card.generate(Suit.DIAMOND, Denomination.NINE), Card.generate(Suit.HEART, Denomination.NINE),
                Card.generate(Suit.SPADE, Denomination.EIGHT), Card.generate(Suit.CLOVER, Denomination.EIGHT)));
        Blackjack blackjack = Blackjack.generateWithDeck(deck);
        Players players = Players.generateWithNames(List.of("pobi"));
        Dealer dealer = Dealer.generate();
        Player pobi = null;
        for (Object player : players) {
            pobi = (Player) player;
        }
        blackjack.betting(pobi, 10000);
        blackjack.distributeInitCards(dealer, players);
        blackjack.distributeAdditionalCard(dealer);
        pobi.setStateStayIfSatisfied(true);
        assertThat(blackjack.calculateYield(dealer, players).get(pobi).money()).isEqualTo(-10000);
    }
}
