package blackjack.fixture;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import java.util.ArrayDeque;
import java.util.List;

public class CardFixture {
    public static Cards 카드_목록_생성(List<CardValue> cardValues) {
        return new Cards(cardValues.stream()
                                   .map(cardValue -> new Card(cardValue, CardSymbol.DIAMOND))
                                   .toList());
    }

    public static Deck 카드_덱_생성() {
        ArrayDeque<Card> cards = new ArrayDeque<>();
        Card card1 = new Card(CardValue.ACE, CardSymbol.DIAMOND);
        Card card2 = new Card(CardValue.FOUR, CardSymbol.HEART);
        Card card3 = new Card(CardValue.ACE, CardSymbol.HEART);
        Card card4 = new Card(CardValue.TWO, CardSymbol.DIAMOND);
        Card card5 = new Card(CardValue.FOUR, CardSymbol.DIAMOND);
        Card card6 = new Card(CardValue.FOUR, CardSymbol.SPADE);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        return new Deck(cards);
    }
}
