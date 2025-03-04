package domain;

public class Card {
    CardScore number;
    CardType type;

    Card(CardScore number, domain. CardType type) {
        this.number = number;
        this.type = type;
    }

    public CardScore getNumber() {
        return number;
    }

    public CardType getType() {
        return type;
    }

    public int calculateAceScore(final int score, final int limit) {
        int aceScore = 1;
        if (score == 10) {
            aceScore =  11;
        }
        return aceScore;
    }
}
