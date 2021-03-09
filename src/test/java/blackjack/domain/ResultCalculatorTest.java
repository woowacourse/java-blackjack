//package blackjack.domain;
//
//import blackjack.domain.card.Card;
//import blackjack.domain.card.Denomination;
//import blackjack.domain.card.Suit;
//import blackjack.domain.gamer.Dealer;
//import blackjack.domain.gamer.Player;
//import blackjack.domain.money.Money;
//import blackjack.domain.utils.ResultCalculator;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//class ResultCalculatorTest {
//
//    @DisplayName("승리자 판단 : 성공")
//    @Test
//    void decideWinner() {
//        Player player = new Player("joanne", new Money(10000));
//        Dealer dealer = new Dealer();
//
//        player.initHands(createInitialCardOfPlayer());
//        dealer.initHands(createInitialCardOfDealer());
//
//        player.receiveCard(Card.of(Suit.HEART, Denomination.ACE));
//        player.receiveCard(Card.of(Suit.CLUB, Denomination.QUEEN));
//
//        dealer.receiveCard(Card.of(Suit.SPADE, Denomination.ACE));
//        dealer.receiveCard(Card.of(Suit.CLUB, Denomination.ACE));
//        dealer.receiveCard(Card.of(Suit.HEART, Denomination.ACE));
//        dealer.receiveCard(Card.of(Suit.CLUB, Denomination.QUEEN));
//
//        assertThat(ResultCalculator.decideWinner(player, dealer)).isEqualTo(ResultType.WIN);
//    }
//
//    private List<Card> createInitialCardOfPlayer() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.SPADE, Denomination.ACE));
//        cards.add(Card.of(Suit.CLUB, Denomination.ACE));
//        return cards;
//    }
//
//    private List<Card> createInitialCardOfDealer() {
//        List<Card> cards = new ArrayList<>();
//        cards.add(Card.of(Suit.HEART, Denomination.TEN));
//        cards.add(Card.of(Suit.HEART, Denomination.THREE));
//        return cards;
//    }
//}