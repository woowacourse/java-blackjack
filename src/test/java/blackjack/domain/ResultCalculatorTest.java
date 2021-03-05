package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
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

        Player player = new Player("joanne", createValidPlayerHands());
        Dealer dealer = new Dealer(createValidDealerHands());

        assertThat(ResultCalculator.decideWinner(player, dealer)).isEqualTo(ResultType.WIN);
    }

    private Hands createValidPlayerHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(CardSymbol.SPADE, CardValue.ACE));
        cards.add(Card.create(CardSymbol.CLUB, CardValue.ACE));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(CardSymbol.HEART, CardValue.ACE));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.QUEEN)); //13
        System.out.println(hands.calculate());
        return hands;
    }

    private Hands createValidDealerHands() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.create(CardSymbol.HEART, CardValue.TEN));
        cards.add(Card.create(CardSymbol.HEART, CardValue.THREE));
        Hands hands = new Hands(cards);
        hands.addCard(Card.create(CardSymbol.SPADE, CardValue.ACE));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.ACE));
        hands.addCard(Card.create(CardSymbol.HEART, CardValue.ACE));
        hands.addCard(Card.create(CardSymbol.CLUB, CardValue.QUEEN)); //26
        System.out.println(hands.calculate());
        return hands;
    }
}