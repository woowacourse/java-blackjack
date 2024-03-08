package model.casino;

import java.util.List;
import model.Choice;
import model.dto.IndividualFaceUpResult;
import model.participant.Entrant;

public class Casino {
    private final Entrant entrant;
    private final CardDispenser cardDispenser;

    public Casino(Entrant entrant, CardShuffleMachine cardShuffleMachine) {
        this.entrant = entrant;
        this.cardDispenser = new CardDispenser(cardShuffleMachine);
    }

    public void initializeGame() {
        int playerSize = entrant.getPlayerSize();
        for (int i = 0; i < playerSize; i++) {
            entrant.hitAndMoveToNextPlayer(cardDispenser.dispenseCard());
            entrant.hitAndMoveToNextPlayer(cardDispenser.dispenseCard());
        }
        hitCardToDealer();
        hitCardToDealer();
    }

    public void distinctPlayerChoice(Choice choice) {
        if (choice.isYes()) {
            hitCardToPlayer();
            return;
        }
        entrant.turnOverPlayer();
    }

    private void hitCardToPlayer() {
        entrant.hitPlayer(cardDispenser.dispenseCard());
    }

    public void hitCardToDealer() {
        entrant.hitDealer(cardDispenser.dispenseCard());
    }

    public boolean hasAvailablePlayer() {
        return entrant.hasAvailablePlayer();
    }

    public boolean isDealerHitAllowed() {
        return entrant.canHitDealer();
    }

    public IndividualFaceUpResult getDealerFaceUpResult() {
        return entrant.getDealerFaceUpResult();
    }

    public List<IndividualFaceUpResult> getPlayerFaceUpResult() {
        return entrant.getPlayerFaceUpResults();
    }

    public IndividualFaceUpResult getNextPlayerFaceUpInfo() {
        return entrant.getNextAvailablePlayerName();
    }

}
