package model.casino;

import java.util.List;
import model.card.Card;
import controller.dto.BettingInfos;
import model.participant.Dealer;
import model.participant.Entrant;
import model.participant.Player;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;
import model.participant.dto.PlayerMatchResult;
import util.ResultMapper;

public class Casino {
    private final Entrant entrant;
    private final CardDispenser cardDispenser;

    public Casino(BettingInfos bettingInfos, CardShuffleMachine cardShuffleMachine) {
        this.cardDispenser = new CardDispenser(cardShuffleMachine);
        this.entrant = generateEntrant(bettingInfos);
    }

    private Entrant generateEntrant(BettingInfos bettingInfos) {
        List<Player> players = bettingInfos.getBettingInfos()
                .stream()
                .map(info -> new Player(info.name(), info.bettingAmount(), getDispensedCard(), getDispensedCard()))
                .toList();
        Dealer dealer = new Dealer(getDispensedCard(), getDispensedCard());
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
        entrant.hitPlayer(getDispensedCard());
    }

    public void hitCardToDealer() {
        entrant.hitDealer(getDispensedCard());
    }

    private Card getDispensedCard() {
        return cardDispenser.dispenseCard();
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
