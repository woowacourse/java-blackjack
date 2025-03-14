import static client.AnswerType.*;

import card.Deck;
import java.util.ArrayList;
import java.util.List;
import participant.Players;
import participant.Dealer;
import participant.Player;
import client.AnswerType;
import client.InputProcessor;
import client.OutputPrinter;
import participant.value.Money;
import result.AllPlayerResult;
import result.GameResult;
import result.GameStatus;
import result.PlayerResult;

public class BlackjackGame {
    private final InputProcessor inputProcessor;
    private final OutputPrinter outputPrinter;

    public BlackjackGame(
            InputProcessor inputProcessor,
            OutputPrinter outputPrinter
    ) {
        this.inputProcessor = inputProcessor;
        this.outputPrinter = outputPrinter;
    }

    public void execute() {
        final Dealer dealer = Dealer.createEmptyHand();

        final Players players = initializePlayers();

        final Deck deck = new Deck();

        initializeParticipantCards(deck, dealer, players);

        askForAdditionalCard(deck, players);

        decideAdditionalCardForDealer(deck, dealer);

        calculateProfit(dealer, players);
    }

    private Players initializePlayers() {
        Players playersOnlyName = Players.createByNames(inputProcessor.requestPlayerNames());
        List<Player> playersWithBettingPrice = new ArrayList<>();
        for (Player player : playersOnlyName.getPlayers()) {
            int price = inputProcessor.requestBettingPrice(player);
            playersWithBettingPrice.add(new Player(player.getName(), Money.bet(price)));
        }
        return Players.createByNamesAndPrices(playersWithBettingPrice);
    }

    private void initializeParticipantCards(Deck deck, Dealer dealer, Players players) {
        dealer.initializeHand(deck.drawInitialHand());
        players.initializeHand(deck);

        outputPrinter.printInitialStatus(dealer.openInitialHand(), players.openNameAndInitialHand());
    }

    private void askForAdditionalCard(Deck deck, Players players) {
        players.getPlayers()
                .forEach(player -> processPlayerCardRequest(deck, player));
    }

    private void decideAdditionalCardForDealer(Deck deck, Dealer dealer) {
        if (dealer.shouldDrawCard()) {
            dealer.addCard(deck.draw());
            outputPrinter.printDealerDraw();
            return;
        }
        outputPrinter.printDealerNoDraw();
    }

    private void calculateProfit(Dealer dealer, Players players) {
        outputPrinter.printCardsResult(dealer, players);
        GameResult gameResult = new GameResult();
        List<PlayerResult> allPlayerResultInfo = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            GameStatus gameStatus = gameResult.calculate(player, dealer);
            PlayerResult playerResult = new PlayerResult(player, gameStatus);
            allPlayerResultInfo.add(playerResult);
        }
        outputPrinter.printGameResults(AllPlayerResult.from(allPlayerResultInfo));
    }

    private void processPlayerCardRequest(Deck deck, Player player) {
        AnswerType answerType = inputProcessor.requestAdditionalCard(player);
        while (isPossibleRequest(player, answerType)) {
            player.addCard(deck.draw());
            outputPrinter.printCurrentCard(player);
            answerType = inputProcessor.requestAdditionalCard(player);
        }
        showMessageIfBust(player);
        showCurrentCardIfNo(player, answerType);
    }

    private void showMessageIfBust(Player player) {
        if (player.isBust()) {
            outputPrinter.printBustMessage();
        }
    }

    private void showCurrentCardIfNo(Player player, AnswerType answerType) {
        if (answerType.isEqualTo(NO)) {
            outputPrinter.printCurrentCard(player);
        }
    }

    private boolean isPossibleRequest(Player player, AnswerType answerType) {
        return answerType.isEqualTo(YES) && !player.isBust();
    }
}
