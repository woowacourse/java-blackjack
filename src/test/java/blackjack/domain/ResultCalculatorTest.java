package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hands;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultCalculatorTest {

    @DisplayName("승리자 판단 : 성공")
    @Test
    void decideWinner() {

        Player player = new Player("joanne", 1000 ,createValidPlayerHands());
        Dealer dealer = new Dealer(createValidDealerHands());

        assertThat(ResultCalculator.decideWinner(player, dealer)).isEqualTo(ResultType.WIN);
    }

    private Hands createValidPlayerHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Suit.SPADE, Denomination.ACE));
        cards.add(Card.of(Suit.CLUB, Denomination.ACE));
        Hands hands = new Hands(cards);
        hands.addCard(Card.of(Suit.HEART, Denomination.ACE));
        hands.addCard(Card.of(Suit.CLUB, Denomination.QUEEN)); //13
        System.out.println(hands.calculate());
        return hands;
    }

    private Hands createValidDealerHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Suit.HEART, Denomination.TEN));
        cards.add(Card.of(Suit.HEART, Denomination.THREE));
        Hands hands = new Hands(cards);
        hands.addCard(Card.of(Suit.SPADE, Denomination.ACE));
        hands.addCard(Card.of(Suit.CLUB, Denomination.ACE));
        hands.addCard(Card.of(Suit.HEART, Denomination.ACE));
        hands.addCard(Card.of(Suit.CLUB, Denomination.QUEEN)); //26
        System.out.println(hands.calculate());
        return hands;
    }
}