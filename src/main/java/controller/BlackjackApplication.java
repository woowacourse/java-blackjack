package controller;

import static view.AnswerType.*;

import domain.Players;
import domain.Referee;
import domain.CardGiver;
import domain.Cards;
import domain.Dealer;
import domain.GameResult;
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
        final Dealer dealer = Dealer.createEmpty();;
        final Referee referee = new Referee();
        final Players players = initializePlayers();

        initializeParticipantCards(dealer, players);

        askForAdditionalCard(players);

        decideAdditionalCardForDealer(dealer);

        calculateResult(dealer, players, referee);
    }

    private Players initializePlayers() {
        List<String> playerNames = inputView.requestPlayerNames();
        List<Player> players = createPlayers(playerNames);

        return new Players(players);
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
        if(dealer.isUnderDrawLimit()) {
            dealer.addCard(cardGiver.giveOne());
            outputView.printDealerDraw();
            return;
        }
        outputView.printDealerNoDraw();
    }

    private void calculateResult(Dealer dealer, Players players, Referee referee) {
        outputView.printCardsResult(dealer, players);
        GameResult gameResult = referee.judge(dealer, players);
        outputView.printGameResults(gameResult);
    }

    private List<Player> createPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, Cards.createEmpty()))
                .toList();
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
        if (answerType.isEqualTo(NO)) {
            outputView.printCurrentCard(player);
        }
    }

    private boolean isPossibleRequest(Player player, AnswerType answerType) {
        return answerType.isEqualTo(YES) && !player.hasBustCards();
    }
}
