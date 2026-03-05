package domain.model;

public class Dealer implements Person {

    // TODO: 딜러에 대한 정보 추가 필요
    private Deck deck;

    private Dealer(Deck deck) {
        this.deck = deck;
    }

    public static Dealer of(Deck deck) {
        return new Dealer(deck);
    }

    @Override
    public int getDeckSum() {
        return 0;
    }

    @Override
    public int getDeckSize() {
        return deck.getSize();
    }
}
