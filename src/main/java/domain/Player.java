package domain;

import static domain.BlackJackGame.BLACKJACK_SCORE;

import domain.constants.CardCommand;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean canPickCard(CardCommand cardCommand) {
        return CardCommand.HIT.equals(cardCommand) && isNotBusted();
    }


    public int calculateResultScore() {
        return hand.calculateResultScore();
    }


    public boolean cannotDraw() {
        return hand.calculateResultScore() > BLACKJACK_SCORE;
    }


    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

}
