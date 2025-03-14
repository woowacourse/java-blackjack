import static view.AnswerType.*;

import card.Deck;
import participant.Players;
import participant.Dealer;
import participant.Player;
import view.AnswerType;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackApplication(
            InputView inputView,
            OutputView outputView
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        final Dealer dealer = Dealer.createEmpty();

        final Players players = initializePlayers();

        final Deck deck = new Deck();

        initializeParticipantCards(deck, dealer, players);

        askForAdditionalCard(deck, players);

        decideAdditionalCardForDealer(deck, dealer);

        calculateResult(dealer, players);
    }

    private Players initializePlayers() {
//        return Players.createByNames(inputView.requestPlayerNames());
        return null;
    }

    private void initializeParticipantCards(Deck deck, Dealer dealer, Players players) {
        dealer.initializeHand(deck.drawInitialHand());
        players.initializeHand(deck);

        outputView.printInitialStatus(dealer.openInitialHand(), players.openNameAndInitialHand());
    }

    private void askForAdditionalCard(Deck deck, Players players) {
        players.getPlayers()
                .forEach(player -> processPlayerCardRequest(deck, player));
    }

    private void decideAdditionalCardForDealer(Deck deck, Dealer dealer) {
        if (dealer.shouldDrawCard()) {
            dealer.addCard(deck.draw());
            outputView.printDealerDraw();
            return;
        }
        outputView.printDealerNoDraw();
    }

    private void calculateResult(Dealer dealer, Players players) {
        outputView.printCardsResult(dealer, players);
//        PlayerResult playerResult = players.judgeAgainstDealer(dealer);
//        outputView.printGameResults(playerResult);
    }

    private void processPlayerCardRequest(Deck deck, Player player) {
        AnswerType answerType = inputView.requestAdditionalCard(player);
        while (isPossibleRequest(player, answerType)) {
            player.addCard(deck.draw());
            outputView.printCurrentCard(player);
            answerType = inputView.requestAdditionalCard(player);
        }
        showMessageIfBust(player);
        showCurrentCardIfNo(player, answerType);
    }

    private void showMessageIfBust(Player player) {
        if (player.isBust()) {
            outputView.printBustMessage();
        }
    }

    private void showCurrentCardIfNo(Player player, AnswerType answerType) {
        if (answerType.isEqualTo(NO)) {
            outputView.printCurrentCard(player);
        }
    }

    private boolean isPossibleRequest(Player player, AnswerType answerType) {
        return answerType.isEqualTo(YES) && !player.isBust();
    }
}
