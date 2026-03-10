package domain;

public class testDeck {
    private final Cards cards;

    public testDeck() {
        Cards deck = Cards.createCards();
        Cards.shuffleCards(deck);
        this.cards = deck;
    }

    // TODO: Deck에 카즈가 있으니까. - 0장이 되었을 때 예외처리
    // TODO: 카드를 보관하고 섞고, 한 장씩 나눠주는 역할을 한다.
//    public Card distributeCard() {
//        Card card = cards.removeFirst();
//        return card;
//    }
}
