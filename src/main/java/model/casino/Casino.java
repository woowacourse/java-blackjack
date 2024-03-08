package model.casino;

import static java.util.Collections.*;
import static model.dto.Victory.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import model.Choice;
import model.dto.DealerScoreResult;
import model.dto.FaceUpResult;
import model.dto.PlayerScoreResult;
import model.dto.Victory;
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

    public FaceUpResult getDealerFaceUpResult() {
        return entrant.getDealerFaceUpResult();
    }

    public List<FaceUpResult> getPlayerFaceUpResult() {
        return entrant.getPlayerFaceUpResults();
    }

    public FaceUpResult getNextPlayerFaceUpInfo() {
        return entrant.getNextAvailablePlayerName();
    }

    public List<PlayerScoreResult> calculatePlayerResults() {
        int dealerHand = entrant.getDealerFaceUpResult()
                .hand();
        return entrant.getPlayerFaceUpResults()
                .stream()
                .map(result -> new PlayerScoreResult(result.name(), of(dealerHand, result.hand())))
                .toList();
    }

    public DealerScoreResult calculateDealerResult() {
        int dealerHand = entrant.getDealerFaceUpResult().hand();
        EnumMap<Victory, Integer> dealerScoreBoard = new EnumMap<>(Victory.class);
        List<Victory> dealerScores = entrant.getPlayerFaceUpResults()
                .stream()
                .map(player -> Victory.of(player.hand(), dealerHand))
                .toList();
        for (Victory victory : values()) {
            dealerScoreBoard.put(victory, frequency(dealerScores, victory));
        }
        return new DealerScoreResult(dealerScoreBoard);
    }

}
