package domain;

public class User extends Player {
    private String name;

    public User(String name, Card... cards) {
        super(cards);
        this.name = name;
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

    public String getName() {
        return name;
    }
}
