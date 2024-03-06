package domain;

public class Gamer {
    private final String name;
    private final HoldingCards holdingCards;

    public Gamer(String name, HoldingCards holdingCards) {
        this.name = name;
        this.holdingCards = holdingCards;
    }

    public void draw(Deck deck, CardDrawStrategy cardDrawStrategy, SummationCardPoint thresholdPoint) {
        validateCanDraw(thresholdPoint);
        holdingCards.add(deck.draw(cardDrawStrategy));
    }

    private void validateCanDraw(SummationCardPoint thresholdPoint) {
        if (getSummationCardPoint().isBiggerThan(thresholdPoint)) {
            throw new IllegalStateException("카드를 더이상 뽑을 수 없습니다.");
        }
    }

    public SummationCardPoint getSummationCardPoint() {
        return holdingCards.calculateTotalPoint();
    }

    public GameResult getGameResult(Gamer other) {
        if (this.getSummationCardPoint().isBiggerThan(other.getSummationCardPoint())) {
            return GameResult.WIN;
        }
        if (this.getSummationCardPoint().equals(other.getSummationCardPoint())) {
            return GameResult.TIE;
        }
        return GameResult.LOSE;
    }

    public String getName() {
        return name;
    }
}
