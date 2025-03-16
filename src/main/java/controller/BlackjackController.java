package controller;

import controller.dto.BettingRequest;
import controller.dto.CardsResultResponse;
import controller.dto.InitialDealResponse;
import controller.dto.InitialDealResponse.DealerDealResult;
import controller.dto.InitialDealResponse.PlayerDealResult;
import controller.dto.PlayerHitResponse;
import controller.dto.ProfitResultResponse;
import domain.BettingMoney;
import domain.CardGiver;
import domain.Dealer;
import domain.Participant;
import domain.BlackjackParticipants;
import domain.Player;
import java.util.List;

public class BlackjackController {

    private final BlackjackParticipants blackjackParticipants;
    private final CardGiver cardGiver;

    public BlackjackController(BlackjackParticipants blackjackParticipants, CardGiver cardGiver) {
        this.blackjackParticipants = blackjackParticipants;
        this.cardGiver = cardGiver;
    }

    public void registerBettingPlayers(List<BettingRequest> bettingRequests) {
        List<Player> players = toPlayers(bettingRequests);
        blackjackParticipants.registerPlayers(players);
    }

    public InitialDealResponse initialDeal() {
        List<Participant> participants = blackjackParticipants.getParticipants();
        cardGiver.giveDefaultTo(participants);
        return toInitialDealResponse();
    }

    public PlayerHitResponse hitPlayer(String playerName, boolean isRequestHit) {
        Player player = blackjackParticipants.findPlayerByName(playerName);
        cardGiver.hit(player, isRequestHit);
        return toPlayerHitResponse(player);
    }

    public List<String> findAllPlayerNames() {
        return blackjackParticipants.findAllPlayerNames();
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

    private List<Player> toPlayers(List<BettingRequest> bettingRequests) {
        return bettingRequests.stream()
                .map(request -> new Player(request.playerName(), new BettingMoney(request.bettingMoney())))
                .toList();
    }

    private InitialDealResponse toInitialDealResponse() {
        Dealer dealer = blackjackParticipants.getDealer();
        DealerDealResult dealerDealResult = new DealerDealResult(dealer.getName(), dealer.getDealCard());
        List<Player> players = blackjackParticipants.getPlayers();
        List<PlayerDealResult> playerDealResults = players.stream()
                .map(player -> new PlayerDealResult(player.getName(), player.getDealCards()))
                .toList();
        return new InitialDealResponse(dealerDealResult, playerDealResults);
    }

    private static PlayerHitResponse toPlayerHitResponse(Player player) {
        return new PlayerHitResponse(player.getName(), player.getHand().getCards());
    }
}
