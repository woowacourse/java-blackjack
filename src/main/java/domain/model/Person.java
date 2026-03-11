package domain.model;

public abstract class Person {

    private Deck deck;

    public Person(Deck deck) {
        this.deck = deck;
    }

    public Person() {}

    // 덱 조회
    public Deck getDeck() {
        return deck;
    }

    // 덱의 총합 조회
    public int getDefaultDeckSum() {
        return deck.getSumWithAceDefaultValue();
    }

    // 최종 합산 계산
    public int getFinalDeckSum() {
        return deck.calculateFinalSum();
    }

    // 덱의 사이즈 조회
    public int getDeckSize() {
        return deck.getSize();
    }

    // 카드 부여
    public void assignDeck(Deck deck) {
        this.deck = deck;
    }

    // 카드 추가
    public void appendCard(Card card) {
        deck.append(card);
    }

    // 버스트인지 검사
    public boolean isBurst() {
        return deck.isBurst();
    }

    // 생존인지 검사
    public boolean isAlive() {
        return deck.isAlive();
    }
}
