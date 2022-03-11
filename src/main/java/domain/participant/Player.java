package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.result.Versus;

public class Player extends Participant {

    public Player(Name name, List<Card> hand) {
        super(name, hand);
    }

    public Versus compareAtDealerBlackJack() {
        boolean isPlayerBlackJack = isBlackJack();
        if (isPlayerBlackJack) {
            return Versus.DRAW;
        }
        return Versus.LOSE;
    }

    public Versus compareAtFinal(Participant other) {
        if (isBust()) {
            return Versus.LOSE;
        }
        if (other.isBust()) {
            return Versus.WIN;
        }
        return judgeVersus(other.getBestScore());
    }

    private Versus judgeVersus(int otherScore) {
        int playerScore = getBestScore();
        if (playerScore > otherScore) {
            return Versus.WIN;
        }
        if (playerScore < otherScore) {
            return Versus.LOSE;
        }
        return Versus.DRAW;
    }

    public boolean isNameMatch(Name name) {
        return this.name.equals(name);
    }
}
