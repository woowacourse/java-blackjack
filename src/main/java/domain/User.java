package domain;

public class User extends Player {
    private boolean isWin;

    public User(Card... cards) {
        super(cards);
        this.isWin = false;
    }

    @Override
    public void insertCard(Cards cards) {
        this.cards.add(cards.pop());
    }

    public void insertCard(Cards cards, AnswerType answerType) {
        if (AnswerType.YES.equals(answerType)) {
            insertCard(cards);
        }
    }
}
