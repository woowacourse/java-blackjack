package controller;

import controller.dto.BettingRequest;
import controller.dto.CardsResultResponse;
import controller.dto.InitialDealResponse;
import controller.dto.InitialDealResponse.DealerDealResult;
import controller.dto.InitialDealResponse.PlayerDealResult;
import controller.dto.PlayerHitResponse;
import controller.dto.ProfitResultResponse;
import domain.BettingMoney;
import domain.BlackjackParticipants;
import domain.CardGiver;
import domain.Dealer;
import domain.Hand;
import domain.Participant;
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
        return toInitialDealResponse(blackjackParticipants.getDealer(), blackjackParticipants.getPlayers());
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
        Dealer dealer = blackjackParticipants.getDealer();
        cardGiver.draw(dealer);
        return dealer.isPossibleDraw();
    }

    public List<CardsResultResponse> extractAllCardsResult() {
        List<Participant> participants = blackjackParticipants.getParticipants();
        return toCardsResultResponses(participants);
    }

    public List<ProfitResultResponse> calculateAllProfitResult() {
        return null;
    }

    private List<Player> toPlayers(List<BettingRequest> bettingRequests) {
        return bettingRequests.stream()
                .map(request -> new Player(request.playerName(), new BettingMoney(request.bettingMoney())))
                .toList();
    }

    private InitialDealResponse toInitialDealResponse(Dealer dealer, List<Player> players) {
        DealerDealResult dealerDealResult = new DealerDealResult(dealer.getName(), dealer.getDealCard());
        List<PlayerDealResult> playerDealResults = players.stream()
                .map(player -> new PlayerDealResult(player.getName(), player.getDealCards()))
                .toList();
        return new InitialDealResponse(dealerDealResult, playerDealResults);
    }

    private PlayerHitResponse toPlayerHitResponse(Player player) {
        return new PlayerHitResponse(player.getName(), player.getHand().getCards());
    }

    private List<CardsResultResponse> toCardsResultResponses(List<Participant> participants) {
        return participants.stream()
                .map(participant -> {
                    Hand hand = participant.getHand();
                    return new CardsResultResponse(participant.getName(), hand.getCards(), hand.calculateSum());
                })
                .toList();
    }
}
