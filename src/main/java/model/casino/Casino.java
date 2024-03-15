package model.casino;

import java.util.List;
import model.Choice;
import model.participant.Dealer;
import model.participant.Entrant;
import model.participant.Names;
import model.participant.Player;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;
import model.participant.dto.PlayerMatchResult;

public class Casino {
    private final Entrant entrant;
    private final CardDispenser cardDispenser;

    public Casino(Names names, CardShuffleMachine cardShuffleMachine) {
        this.cardDispenser = new CardDispenser(cardShuffleMachine);
        this.entrant = generateEntrant(names);
    }

    private Entrant generateEntrant(Names names) {
        List<Player> players = names.getPlayerNames()
                .stream()
                .map(name -> new Player(name, cardDispenser.dispenseCard(), cardDispenser.dispenseCard()))
                .toList();
        Dealer dealer = new Dealer(cardDispenser.dispenseCard(), cardDispenser.dispenseCard());
        return new Entrant(dealer, players);
    }

    public void distinctPlayerChoice(Choice choice) {
        if (choice.isHit()) {
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

    public DealerFaceUpResult getDealerFaceUpResult() {
        return entrant.getDealerFaceUpResult();
    }

    public List<PlayerFaceUpResult> getPlayerFaceUpResult() {
        return entrant.getPlayerFaceUpResults();
    }

    public PlayerFaceUpResult getNextPlayerFaceUpInfo() {
        return entrant.getNextAvailablePlayerName();
    }

    public List<PlayerMatchResult> calculatePlayerMatchResults() {
        return entrant.determineFinalPlayerMatchResults();
    }
}
