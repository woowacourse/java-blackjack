package blackjack.domain.participant;

import blackjack.domain.Game;

public class Player extends Participant {

    public Player(String inputName) {
        super(inputName);
    }

    public boolean isBlackJack() {
        return calculateCardsScoreResult() == Game.BLACKJACK_NUMBER;
    }

    @Override
    public String getName() {
        return name.toString();
    }

}