package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.user.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("초기 state가 true인지 확인하는 테스트")
    @Test
    void isHitTest() {
        assertThat(Dealer.generate().isHit()).isTrue();
    }

    @DisplayName("state가 stay로 설정되었을때 false인지 확인하는 테스트")
    @Test
    void isHitTest2() {
        Dealer dealer = Dealer.generate();
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.TEN), Card.generate(Suit.DIAMOND, Denomination.NINE)));
        dealer.drawInitialCards(deck);
        assertThat(dealer.isHit()).isFalse();
    }

    @DisplayName("블랙잭 조건 만족했을때 true인지 확인하는 테스트")
    @Test
    void isBlackjackTest() {
        Dealer dealer = Dealer.generate();
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.ACE), Card.generate(Suit.DIAMOND, Denomination.TEN)));
        dealer.drawInitialCards(deck);
        assertThat(dealer.isBlackjack()).isTrue();
    }

    @DisplayName("합은 21이지만 카트가 3장일때 false인지 확인하는 테스트")
    @Test
    void isBlackjackTest2() {
        Dealer dealer = Dealer.generate();
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.EIGHT),
                        Card.generate(Suit.DIAMOND, Denomination.SEVEN),
                        Card.generate(Suit.DIAMOND, Denomination.SIX)));
        dealer.drawInitialCards(deck);
        dealer.drawAdditionalCard(deck);
        assertThat(dealer.isBlackjack()).isFalse();
    }

    @Test
    void isBurstTest_true() {
        Dealer dealer = Dealer.generate();
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.EIGHT),
                        Card.generate(Suit.DIAMOND, Denomination.NINE),
                        Card.generate(Suit.DIAMOND, Denomination.TEN)));
        dealer.drawInitialCards(deck);
        dealer.drawAdditionalCard(deck);
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    void isBurstTest_false() {
        Dealer dealer = Dealer.generate();
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.TWO),
                        Card.generate(Suit.DIAMOND, Denomination.THREE),
                        Card.generate(Suit.DIAMOND, Denomination.FOUR)));
        dealer.drawInitialCards(deck);
        dealer.drawAdditionalCard(deck);
        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    void pickOpenCardsTest() {
        Dealer dealer = Dealer.generate();
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.TWO),
                        Card.generate(Suit.DIAMOND, Denomination.THREE)));
        dealer.drawInitialCards(deck);
        assertThat(dealer.pickOpenCards().numberOfCards()).isEqualTo(1);
    }
}
