package domain;

public final class Player {
    private final Name name;
    private final CardList cardList;
    private final Score score;
    private int resultScore;
    private int aceCount;
    private Outcome outcome;

    public Player(String name) {
        this.name = new Name(name);
        this.cardList = new CardList();
        this.score = new Score();
        this.aceCount = 0;
        this.resultScore = 0;
    }

    public void drawCard() {
        Card card = cardList.draw();
        while (isDuplicated(card)) {
            card = cardList.draw();
        }
        saveDrawnCard(card);
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome playerOutCome) {
        outcome = playerOutCome;
    }

    public int getResultScore() {
        return resultScore;
    }

    public void setResultScore(int number) {
        resultScore = number;
    }

    public String getName() {
        return name.getName();
    }

    public CardList getCardList() {
        return cardList;
    }

    public Score getScore() {
        return score;
    }

    public int getResult() {
        return score.getScore();
    }

    public boolean checkBust() {
        while (canLowerAceScore()) {
            score.subScore(10);
            aceCount--;
        }
        return score.isBust();
    }

    private boolean isDuplicated(Card card) {
        return DuplicationSet.duplicationCards.contains(card.getCard());
    }

    private void saveDrawnCard(Card card) {
        DuplicationSet.duplicationCards.add(card.getCard());
        score.addScore(CardSuitMap.getScore(card.getCard()));
        cardList.add(card);
        if (card.checkAce()) {
            aceCount += 1;
        }
    }

    private boolean canLowerAceScore() {
        return score.getScore() > 21 && aceCount > 0;
    }
}
