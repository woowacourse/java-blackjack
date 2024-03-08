package blackjack.model;

public class Player extends Participant {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean checkDrawCardState() {
        return !cards.isGreaterThanWinningScore();
    }

    public ResultStatus getResultStatus(Cards otherCards) {
        if (cards.isGreaterThanWinningScore()) {
            return ResultStatus.LOSE;
        }
        if (otherCards.isGreaterThanWinningScore()) {
            return ResultStatus.WIN;
        }
        return compareScore(otherCards);
    }

    private ResultStatus compareScore(Cards otherCards) {
        int calculatedScore = cards.getCardsScore();
        int otherScore = otherCards.getCardsScore();

        if (calculatedScore > otherScore) {
            return ResultStatus.WIN;
        }
        if (calculatedScore < otherScore) {
            return ResultStatus.LOSE;
        }
        return ResultStatus.PUSH;
    }

    public String getName() {
        return name;
    }
}
