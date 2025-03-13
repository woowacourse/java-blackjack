package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardRank;
import domain.card.CardSymbol;
import fixture.CardsInitializerFixture;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 16 이하라면 카드를 뽑는다")
    @Test
    void canHit() {
        //given
        Card card1 = new Card(CardSymbol.COLVER, CardRank.ACE);
        Card card2 = new Card(CardSymbol.HEART, CardRank.TWO);
        Card card3 = new Card(CardSymbol.SPADE, CardRank.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        CardDeck deck = new CardDeck(new CardsInitializerFixture(cards));

        Dealer dealer = new Dealer();
        dealer.prepareGame(deck);

        int initialPoint = dealer.getHand().calculateTotalPoint();

        //when
        dealer.hit(deck);
        int actual = dealer.getHand().calculateTotalPoint();

        //then
        assertThat(actual).isNotEqualTo(initialPoint);
    }

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 17 이상이라면 카드를 뽑지 않는다")
    @Test
    void cannotHit() {
        //given
        Card card1 = new Card(CardSymbol.COLVER, CardRank.JACK);
        Card card2 = new Card(CardSymbol.HEART, CardRank.NINE);
        Card card3 = new Card(CardSymbol.SPADE, CardRank.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        CardDeck deck = new CardDeck(new CardsInitializerFixture(cards));

        Dealer dealer = new Dealer();
        dealer.prepareGame(deck);

        //when //then
        assertThatCode(() -> dealer.hit(deck))
                .isInstanceOf(IllegalStateException.class);

    }

}
