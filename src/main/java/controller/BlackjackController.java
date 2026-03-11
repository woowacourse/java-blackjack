package controller;

import domain.BlackjackResult;
import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.WinOrLose;
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
        return Cards.shuffled();
    }

    private List<String> getCardsInfo(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getRankName() + card.getSuitName())
                .toList();
    }

    private void printInitialCards(Players players, Dealer dealer) {
        outputView.separatorLine();
        outputView.printfList(OutputMessage.DEAL_INITIAL_CARDS.getMessage(),
                OutputMessage.DELIMITER.join(players.getUserNames()));

        printDealerFirstCardInfo(dealer);

        printPlayersCardInfo(players);
    }

    private void printDealerFirstCardInfo(Dealer dealer) {
        outputView.separatorLine();
        Card dealerFirstCard = dealer.getCards().getFirst();
        outputView.println(
                OutputMessage.DEALER_CARD_INFO.format(dealerFirstCard.getRankName() + dealerFirstCard.getSuitName()));
    }

    private void printPlayersCardInfo(Players players) {
        List<List<Card>> playersCardsInfo = players.getPlayersCardsInfo();
//        List<List<String>> playersCardsInfo = players.getPlayersCardsInfo();
        List<String> playerNames = players.getUserNames();

        for (int i = 0; i < playerNames.size(); i++) {
            outputView.println(getPlayerCardsInfo(playerNames.get(i), getCardsInfo(playersCardsInfo.get(i))));
        }
    }

    private void addCard(Players players, Cards deck, Dealer dealer) {
        outputView.separatorLine();
        askPlayersAddCard(players, deck);
        addDealerCard(dealer, deck);
    }

    private void askPlayersAddCard(Players players, Cards deck) {
        for (Player player : players.getPlayers()) {
            askAddCard(player, deck);
        }
    }

    private void askAddCard(Player player, Cards deck) {
        String answer = InputMessage.USER_INPUT_YES.getMessage();
        while (!player.isBust(player.calculateScore()) && answer.equals(InputMessage.USER_INPUT_YES.getMessage())) {
            outputView.printfList(InputMessage.ASK_ADD_CARD.getMessage(), player.getName());
            outputView.separatorLine();
            answer = inputView.askUserInput();
            printInitialHandIfAnswer(player, deck, answer);
        }
    }

    private void printInitialHandIfAnswer(Player player, Cards deck, String answer) {
        if (answer.equals(InputMessage.USER_INPUT_NO.getMessage())) {
            printIfNotPrintedPlayerCardsInfo(player);
        }
        if (answer.equals(InputMessage.USER_INPUT_YES.getMessage())) {
            player.addCard(deck.draw());
            outputView.println(getPlayerCardsInfo(player.getName(), getCardsInfo(player.getCards())));
        }
    }

    private void printIfNotPrintedPlayerCardsInfo(Player player) {
        if (player.getCards().size() == Cards.FIRST_DRAW_SIZE) {
            outputView.println(getPlayerCardsInfo(player.getName(), getCardsInfo(player.getCards())));
        }
    }

    private String getPlayerCardsInfo(String playerName, List<String> cardsInfo) {
        String playerCardsFormat = OutputMessage.DELIMITER.join(cardsInfo);
        return OutputMessage.PLAYER_CARD_INFO.format(playerName, playerCardsFormat);
    }

    private void addDealerCard(Dealer dealer, Cards deck) {
        outputView.separatorLine();
        dealer.drawUntilHit(deck);

        int countAddDealerCard = dealer.getCards().size() - Cards.FIRST_DRAW_SIZE;
        for (int i = 0; i < countAddDealerCard; i++) {
            outputView.println(OutputMessage.DEALER_DRAW_CARD.getMessage());
        }
    }

    private void printFinalCards(Dealer dealer, Players players) {
        outputView.separatorLine();
        printDealerCardsAndScore(dealer);

//        List<List<String>> playersCardsInfo = players.getPlayersCardsInfo();
        List<List<Card>> playersCardsInfo = players.getPlayersCardsInfo();
        List<String> playerNames = players.getUserNames();
        List<Integer> playersScoreInfo = players.getPlayerScoreInfo();

        for (int i = 0; i < playersCardsInfo.size(); i++) {
            String playerCardsInfo = getPlayerCardsInfo(playerNames.get(i), getCardsInfo(playersCardsInfo.get(i)));
            outputView.println(OutputMessage.RESULT_TEXT.format(playerCardsInfo, playersScoreInfo.get(i)));
        }
    }

    private void printDealerCardsAndScore(Dealer dealer) {
        String dealerCardsFormat = OutputMessage.DEALER_CARD_INFO.format(OutputMessage.DELIMITER.join(
                getCardsInfo(dealer.getCards())));
        outputView.println(OutputMessage.RESULT_TEXT.format(dealerCardsFormat, dealer.calculateScore()));
    }

    private void printGameResult(Dealer dealer, Players players) {
        outputView.separatorLine();
        BlackjackResult blackjackResult = BlackjackResult.of(dealer, players);

        outputView.println(OutputMessage.FINAL_MESSAGE.getMessage());

        outputView.println(OutputMessage.DEALER_RESULT_FORMAT.format(blackjackResult.countDealerWinOrLoseReversePlayerResult(WinOrLose.LOSE),
                blackjackResult.countDealerWinOrLoseReversePlayerResult(WinOrLose.DRAW), blackjackResult.countDealerWinOrLoseReversePlayerResult(WinOrLose.WIN)));

        printPlayersResult(players, blackjackResult);
    }

    private void printPlayersResult(Players players, BlackjackResult blackjackResult) {
        List<WinOrLose> playerResult = blackjackResult.getPlayersResult();
        for (int i = 0; i < players.getPlayers().size(); i++) {
            outputView.println(OutputMessage.PLAYER_RESULT_FORMAT.format(players.getUserNames().get(i),
                    playerResult.get(i).getMessage()));
        }
    }
}
