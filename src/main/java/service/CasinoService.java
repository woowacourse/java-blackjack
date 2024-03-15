package service;

import java.util.List;
import model.Choice;
import model.casino.CardDispenser;
import model.casino.CardShuffleMachine;
import model.participant.Dealer;
import model.participant.Entrant;
import model.participant.Names;
import model.participant.Player;
import service.dto.DealerFaceUpResult;
import service.dto.DealerMatchResult;
import service.dto.FaceUpResult;
import service.dto.PlayerMatchResult;

public class CasinoService {
    private final Entrant entrant;
    private final CardDispenser cardDispenser;

    public CasinoService(Names names, CardShuffleMachine cardShuffleMachine) {
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

    public List<FaceUpResult> getPlayerFaceUpResult() {
        return entrant.getPlayerFaceUpResults();
    }

    public FaceUpResult getNextPlayerFaceUpInfo() {
        return entrant.getNextAvailablePlayerName();
    }

    public List<PlayerMatchResult> calculatePlayerMatchResults() {
        return entrant.calculatePlayerMatchResults();
    }

    public DealerMatchResult calculateDealerMatchResult() {
        return new DealerMatchResult(entrant.calculateDealerMatchResult());
    }
}
