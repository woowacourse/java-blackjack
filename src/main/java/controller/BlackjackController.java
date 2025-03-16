package controller;

import static view.AnswerType.*;

import controller.dto.BettingRequest;
import controller.dto.CardsResultResponse;
import controller.dto.InitialDealResponse;
import controller.dto.PlayerHitResponse;
import controller.dto.ProfitResultResponse;
import domain.Participant;
import domain.Players;
import domain.CardGiver;
import domain.Dealer;
import domain.GameResults;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import view.AnswerType;
import view.ConsoleView;

public class BlackjackController {

    private final ConsoleView consoleView;
    private final CardGiver cardGiver;

    public BlackjackController(ConsoleView consoleView, CardGiver cardGiver) {
        this.consoleView = consoleView;
        this.cardGiver = cardGiver;
    }

    public void execute() {
        final Dealer dealer = Dealer.createEmpty();
        final Players players = initializePlayers();

        initializeParticipantCards(dealer, players);

        askAdditionalCardForPlayers(players);

        decideAdditionalCardForDealer(dealer);

        calculateResult(dealer, players);
    }

    public void createGame(List<BettingRequest> bettingRequests) {

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

    private Players initializePlayers() {
        List<String> playerNames = consoleView.requestPlayerNames();
        return Players.createByNames(playerNames);
    }

    private void initializeParticipantCards(Dealer dealer, Players players) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(dealer);
        cardGiver.giveDefaultTo(participants);
        consoleView.printInitialCards(dealer, players);
    }

    private void askAdditionalCardForPlayers(Players players) {
        players.getPlayers().forEach(this::processPlayerCardRequest);
    }

    private void decideAdditionalCardForDealer(Dealer dealer) {
        cardGiver.giveOneTo(dealer);
        consoleView.printDealerDrawResult(dealer.isPossibleDraw());
    }

    private void calculateResult(Dealer dealer, Players players) {
        consoleView.printCardsResult(dealer, players);
        GameResults gameResults = players.calculateGameResult(dealer);
        consoleView.printGameResults(gameResults);
    }

    private void processPlayerCardRequest(Player player) {
        AnswerType answerType = YES;
        showCurrentCardIfNo(player, answerType);
        while (answerType == YES && player.isPossibleDraw()) {
            answerType = consoleView.requestAdditionalCard(player);
            cardGiver.giveOneTo(player);
            consoleView.printCurrentCard(player);
        }
        showMessageIfBust(player);
    }

    private void showMessageIfBust(Player player) {
        if (player.hasBustCards()) {
            consoleView.printBustMessage();
        }
    }

    private void showCurrentCardIfNo(Player player, AnswerType answerType) {
        if (answerType == NO) {
            consoleView.printCurrentCard(player);
        }
    }
}
