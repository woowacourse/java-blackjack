package controller;

import domain.BlackjackResult;
import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.GameResult;
import infra.RandomCardShuffler;
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
        Cards deck = getDeck();
        Dealer dealer = Dealer.of(deck.drawInitialHand());
        Players players = getPlayers();

        addPlayerInitialCard(players, deck);

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
        return Cards.of(new RandomCardShuffler());
    }

    private Players getPlayers() {
        List<String> userNames = getUserNames();
        return Players.of(userNames);
    }

    private static void addPlayerInitialCard(Players players, Cards deck) {
        for (Player player : players.getPlayers()) {
            player.addInitialCards(deck.drawInitialHand());
        }
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
        while (!player.isBust() && answer.equals(InputMessage.USER_INPUT_YES.getMessage())) {
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
        if (player.hasInitialHand()) {
            outputView.println(getPlayerCardsInfo(player.getName(), getCardsInfo(player.getCards())));
        }
    }

    private String getPlayerCardsInfo(String playerName, List<String> cardsInfo) {
        String playerCardsFormat = OutputMessage.DELIMITER.join(cardsInfo);
        return OutputMessage.PLAYER_CARD_INFO.format(playerName, playerCardsFormat);
    }

    private void addDealerCard(Dealer dealer, Cards deck) {
        outputView.separatorLine();

        while (dealer.isHit()) {
            dealer.addCard(deck.draw());
        }

        outputView.printRepeated(OutputMessage.DEALER_DRAW_CARD.getMessage(), dealer.getAdditionalCardCount());
    }

    private void printFinalCards(Dealer dealer, Players players) {
        outputView.separatorLine();
        printDealerCardsAndScore(dealer);

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

        outputView.println(OutputMessage.DEALER_RESULT_FORMAT.format(blackjackResult.countDealerWinOrLoseReversePlayerResult(
                        GameResult.LOSE),
                blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.DRAW), blackjackResult.countDealerWinOrLoseReversePlayerResult(
                        GameResult.WIN)));

        printPlayersResult(players, blackjackResult);
    }

    private void printPlayersResult(Players players, BlackjackResult blackjackResult) {
        List<GameResult> playerResult = blackjackResult.getPlayersResult();
        for (int i = 0; i < players.getPlayers().size(); i++) {
            outputView.println(OutputMessage.PLAYER_RESULT_FORMAT.format(players.getUserNames().get(i),
                    playerResult.get(i).getMessage()));
        }
    }
}
