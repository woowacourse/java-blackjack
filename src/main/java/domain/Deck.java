package domain;

public class Deck {
    private final Cards cards;

    // TODO: 주입하는 건 Card한테 Deck을 의존하는 상황. -> 직접 주입을 통해 card 의존성을 해결
    public Deck() {
        Cards deck = Cards.createCards();
        Cards.shuffleCards(deck);
        this.cards = deck;
    }

    // TODO: Deck에 카즈가 있으니까. - 0장이 되었을 때 예외처리
    // TODO: 카드를 보관하고 섞고, 한 장씩 나눠주는 역할을 한다.
    public Card distributeCard() {
        Card card = cards.removeFirst();
        return card;
    }
}
