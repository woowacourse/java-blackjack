package controller;

import static view.AnswerType.*;

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

public class BlackjackApplication {

    private final ConsoleView consoleView;
    private final CardGiver cardGiver;

    public BlackjackApplication(ConsoleView consoleView, CardGiver cardGiver) {
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
        consoleView.printDealerDraw(dealer.isPossibleDraw());
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
