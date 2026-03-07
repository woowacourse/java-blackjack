package controller;

import domain.BlackjackResult;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.List;
import meesage.InputMessage;
import meesage.OutputMessage;
import utils.InputParser;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> userNames = getUserNames();

        Cards deck = getDeck();
        Dealer dealer = Dealer.of(deck.drawInitialHand());
        Players players = Players.of(deck, userNames);

        printInitialCards(players, dealer);
        addCard(players, deck, dealer);
        printFinalCards(dealer, players);
        printGameResult(dealer, players);
    }

    private List<String> getUserNames() {
        String rawUserNames = inputView.askUserInputWithMessage(InputMessage.ASK_USER_NAME.getMessage());
        return InputParser.splitByDelimiter(rawUserNames);
    }

    private static Cards getDeck() {
        Cards deck = Cards.of();
        cardShuffle(deck);
        return deck;
    }

    private static void cardShuffle(Cards deck) {
        deck.shuffle();
    }

    private void printInitialCards(Players players, Dealer dealer) {
        outputView.separatorLine();
        outputView.printfList(OutputMessage.DEAL_INITIAL_CARDS.getMessage(),
                OutputMessage.format(players.getUserNames()));
        outputView.separatorLine();
        outputView.println(dealer.getDealerInitialInfo());
        outputView.printList(players.getPlayerInfo());

    }

    private void addCard(Players players, Cards deck, Dealer dealer) {
        askPlayersAddCard(players, deck);

        addDealerCard(dealer, deck);
    }

    private void askPlayersAddCard(Players players, Cards deck) {
        for (Player player : players.getPlayers()) {
            askAddCard(player, deck);
        }
    }

    private void askAddCard(Player player, Cards deck) {
        String answer = "y";
        while (!player.isBust(player.calculateScore()) && answer.equals("y")) {
            outputView.printfList(InputMessage.ASK_ADD_CARD.getMessage(), player.getName());
            outputView.separatorLine();
            answer = inputView.askUserInput();
            printInitialHandIfAnswerNo(player, answer);
            printInitialHandIfAnswerYes(player, deck, answer);
        }
    }

    private void printInitialHandIfAnswerNo(Player player, String answer) {
        if (answer.equals("n")) {
            if (player.getCardsInfo().size() == 2) {
                outputView.println(player.getPlayerInfo());
            }
        }
    }

    private void printInitialHandIfAnswerYes(Player player, Cards deck, String answer) {
        if (answer.equals("y")) {
            player.addCard(deck.draw());
            outputView.println(player.getPlayerInfo());
        }
    }

    private void addDealerCard(Dealer dealer, Cards deck) {
        while (dealer.shouldHit()) {
            outputView.println(OutputMessage.DEALER_DRAW_CARD.getMessage());
            dealer.addCard(deck.draw());
        }
    }

    private void printFinalCards(Dealer dealer, Players players) {
        outputView.separatorLine();
        outputView.println(dealer.getDealerScoreInfo());
        outputView.printList(players.getPlayerScoreInfo());
    }

    private void printGameResult(Dealer dealer, Players players) {
        BlackjackResult blackjackResult = BlackjackResult.of(dealer, players);

        outputView.println(OutputMessage.FINAL_MESSAGE.getMessage());
        outputView.println(blackjackResult.getDealerResult());
        outputView.printList(blackjackResult.getPlayersResult());
    }
}
