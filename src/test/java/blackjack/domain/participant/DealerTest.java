package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.testutil.CustomDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private static Dealer createDealer(List<Number> numbers, List<Shape> shapes) {
        Deck deck = new CustomDeck(numbers, shapes);
        return new Dealer(deck);
    }

    @DisplayName("딜러는 처음에 한 장의 카드를 오픈한다.")
    @Test
    void dealerInitialOpenedCards() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.EIGHT);
        List<Shape> shapes = List.of(Shape.SPADE, Shape.CLOVER);
        Dealer dealer = createDealer(numbers, shapes);

        //when
        List<String> cardSignatures = dealer.getInitialOpenedCards()
                .stream()
                .map(card -> card.getSymbol() + card.getShape())
                .toList();

        //then
        assertThat(cardSignatures).containsExactly("A스페이드");
    }

    @DisplayName("패가 블랙잭인 딜러가 Hit을 할 수 있는지 확인한다.")
    @Test
    void isBlackjackDealerCanHit() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.JACK);
        Deck deck = new CustomDeck(numbers);
        Dealer dealer = new Dealer(deck);

        //when & then
        assertThat(dealer.canHit()).isEqualTo(false);
    }

    @DisplayName("패가 Bust인 딜러가 Hit을 할 수 있는지 확인한다.")
    @Test
    void isBustDealerCanHit() {
        //given
        List<Number> numbers = List.of(Number.FIVE, Number.NINE, Number.KING);
        Deck deck = new CustomDeck(numbers);
        Dealer dealer = new Dealer(deck);
        dealer.addCard(deck);

        //when & then
        assertThat(dealer.canHit()).isEqualTo(false);
    }

    @DisplayName("패가 16 이하인 딜러가 Hit을 할 수 있는지 확인한다.")
    @Test
    void isUnderDealerCanHit() {
        //given
        List<Number> numbers = List.of(Number.SEVEN, Number.NINE);
        Deck deck = new CustomDeck(numbers);
        Dealer dealer = new Dealer(deck);

        //when & then
        assertThat(dealer.canHit()).isEqualTo(true);
    }
}
