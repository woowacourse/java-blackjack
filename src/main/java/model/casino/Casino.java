package model.casino;

import model.participant.Entrant;

public class Casino {
    private final Entrant entrant;
    private final CardDispenser cardDispenser;

    public Casino(Entrant entrant, CardShuffleMachine cardShuffleMachine) {
        this.entrant = entrant;
        this.cardDispenser = new CardDispenser(cardShuffleMachine);
    }

    public void initializeGame(){
        int playerSize = entrant.getPlayerSize();
        for (int i = 0; i < playerSize; i++) {
            hitCardToPlayer();
            hitCardToPlayer();
        }
        hitCardToDealer();
        hitCardToDealer();
    }

    private void hitCardToPlayer() {
        entrant.hitPlayer(cardDispenser.dispenseCard());
    }

    private void hitCardToDealer(){
        entrant.hitDealer(cardDispenser.dispenseCard());
    }


}
