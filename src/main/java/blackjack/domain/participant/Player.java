package blackjack.domain.participant;

import blackjack.domain.Game;

public class Player extends Participant {
    private final Name name;

    public Player(String inputName) {
        name = new Name(inputName);
    }

    public boolean isBlackJack() {
        return calculateCardsScoreResult() == Game.BLACKJACK_NUMBER;
    }

    @Override
    public String getName() {
        return name.toString();
    }

}