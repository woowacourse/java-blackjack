package controller;

import static view.AnswerType.*;

import domain.Players;
import domain.Referee;
import domain.CardGiver;
import domain.Dealer;
import domain.GameResults;
import domain.Participant;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import view.AnswerType;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardGiver cardGiver;

    public BlackjackApplication(
            InputView inputView,
            OutputView outputView,
            CardGiver cardGiver
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardGiver = cardGiver;
    }

    public void execute() {
        final Dealer dealer = Dealer.createEmpty();
        final Players players = initializePlayers();

        initializeParticipantCards(dealer, players);

        askForAdditionalCard(players);

        decideAdditionalCardForDealer(dealer);

        calculateResult(dealer, players);
    }

    private Players initializePlayers() {
        List<String> playerNames = inputView.requestPlayerNames();
        return Players.createByNames(playerNames);
    }

    private void initializeParticipantCards(Dealer dealer, Players players) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(dealer);
        cardGiver.giveDefaultTo(participants);

        outputView.printInitialCards(dealer, players);
    }

    private void askForAdditionalCard(Players players) {
        players.getPlayers().forEach(this::processPlayerCardRequest);
    }

    private void decideAdditionalCardForDealer(Dealer dealer) {
        if (dealer.isPossibleDraw()) {
            dealer.addCard(cardGiver.giveOne());
            outputView.printDealerDraw();
            return;
        }
        outputView.printDealerNoDraw();
    }

    private void calculateResult(Dealer dealer, Players players) {
        final Referee referee = new Referee();

        outputView.printCardsResult(dealer, players);
        GameResults gameResults = referee.judge(dealer, players);
        outputView.printGameResults(gameResults);
    }

    private void processPlayerCardRequest(Player player) {
        AnswerType answerType = inputView.requestAdditionalCard(player);
        while (isPossibleRequest(player, answerType)) {
            cardGiver.giveAdditionalCard(player, answerType);
            outputView.printCurrentCard(player);
            answerType = inputView.requestAdditionalCard(player);
        }
        showMessageIfBust(player);
        showCurrentCardIfNo(player, answerType);
    }

    private void showMessageIfBust(Player player) {
        if (player.hasBustCards()) {
            outputView.printBustMessage();
        }
    }

    private void showCurrentCardIfNo(Player player, AnswerType answerType) {
        if (answerType == NO) {
            outputView.printCurrentCard(player);
        }
    }

    private boolean isPossibleRequest(Player player, AnswerType answerType) {
        return answerType == YES && player.hasNotBustCards();
    }
}
