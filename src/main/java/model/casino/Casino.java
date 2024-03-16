package model.casino;

import java.util.List;
import model.participant.Dealer;
import model.participant.Entrant;
import model.participant.NameInfos;
import model.participant.Player;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;
import model.participant.dto.PlayerMatchResult;
import util.ResultMapper;

public class Casino {
    private final Entrant entrant;
    private final CardDispenser cardDispenser;

    public Casino(NameInfos nameInfos, CardShuffleMachine cardShuffleMachine) {
        this.cardDispenser = new CardDispenser(cardShuffleMachine);
        this.entrant = generateEntrant(nameInfos);
    }

    private Entrant generateEntrant(NameInfos nameInfos) {
        List<Player> players = nameInfos.getPlayerNames()
                .stream()
                .map(name -> new Player(name, nameInfos.getBettingAmount(name), cardDispenser.dispenseCard(), cardDispenser.dispenseCard()))
                .toList();
        Dealer dealer = new Dealer(cardDispenser.dispenseCard(), cardDispenser.dispenseCard());
        return new Entrant(dealer, players);
    }

    public void distinctPlayerChoice(boolean choice) {
        if (choice) {
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
        return ResultMapper.toPlayerFaceUpResult(entrant.getNextAvailablePlayer());
    }

    public List<PlayerMatchResult> calculatePlayerMatchResults() {
        return entrant.determineFinalPlayerMatchResults();
    }
}
