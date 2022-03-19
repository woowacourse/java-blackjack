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
}
