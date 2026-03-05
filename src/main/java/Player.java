public class Player {
    private final String name;

    private Cards drawnCards;

    public Player(String name) {
        this.name = name;
        this.drawnCards = new Cards();
    }

    public void receiveOneCard(Card card) {
        drawnCards.addCard(card);
    }

    public Cards getDrawnCards() {
        return drawnCards;
    }
}
