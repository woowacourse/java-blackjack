package controller;

import controller.dto.BettingRequest;
import controller.dto.CardsResultResponse;
import controller.dto.InitialDealResponse;
import controller.dto.PlayerHitResponse;
import controller.dto.ProfitResultResponse;
import domain.BettingMoney;
import domain.Participants;
import domain.Player;
import java.util.List;

public class BlackjackController {

    private final Participants participants;

    public BlackjackController(Participants participants) {
        this.participants = participants;
    }

    public void registerBettingPlayers(List<BettingRequest> bettingRequests) {
        List<Player> players = bettingRequests.stream()
                .map(request -> {
                    BettingMoney bettingMoney = new BettingMoney(request.bettingMoney());
                    return new Player(request.playerName(), bettingMoney);
                })
                .toList();
        participants.registerPlayers(players);
    }

    public InitialDealResponse initialDeal() {
        return null;
    }

    public PlayerHitResponse hitPlayer(String playerName, boolean isRequestHit) {
        return null;
    }

    public List<String> findAllPlayerNames() {
        return null;
    }

    public boolean drawDealer() {
        return true;
    }

    public List<CardsResultResponse> extractAllCardsResult() {
        return null;
    }

    public List<ProfitResultResponse> calculateAllProfitResult() {
        return null;
    }
}
