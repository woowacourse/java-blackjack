package model.casino;

import controller.dto.BettingInfos;
import controller.dto.DealerFaceUpResult;
import controller.dto.PlayerFaceUpResult;
import java.util.List;
import model.card.Card;
import model.participant.Dealer;
import model.participant.Entrant;
import model.participant.Player;
import util.ResultMapper;

public class Casino {
    public static final int DEALER_PROFIT_CONVERTER = -1;

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
        return entrant.isDealerHitAllowed();
    }

    public PlayerFaceUpResult getNextPlayerFaceUpInfo() {
        return ResultMapper.toPlayerFaceUpResult(entrant.getNextAvailablePlayer());
    }

    public DealerFaceUpResult getDealerFaceUpResult() {
        return ResultMapper.toDealerFaceUpResult(entrant.getDealer());
    }

    public List<PlayerFaceUpResult> getPlayerFaceUpResult() {
        return entrant.getPlayers()
                .stream()
                .map(ResultMapper::toPlayerFaceUpResult)
                .toList();
    }

    public void aggregateBettingResult() {
        entrant.aggregateBettingResult();
    }

    public BettingInfos calculatePlayerBettingResults() {
        return ResultMapper.toPlayerBettingResult(entrant.getPlayers());
    }

    public long calculateDealerEarningResults() {
        return DEALER_PROFIT_CONVERTER * entrant.getPlayers()
                .stream()
                .map(Player::calculateEarning)
                .reduce(0L, Long::sum);
    }
}
