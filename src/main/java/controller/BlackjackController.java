package controller;

import domain.Card;
import domain.Deck;
import domain.Dealer;
import domain.Player;
import domain.PlayerBet;
import domain.Players;
import domain.PlayerBets;
import infra.RandomCardShuffler;
import java.math.BigDecimal;
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
        Deck deck = getDeck();
        Dealer dealer = Dealer.of(deck.drawInitialHand());
        Players players = getPlayers();

        PlayerBets playerBets = new PlayerBets();
        createPlayersBetFromInput(players, playerBets);

        addPlayerInitialCard(players, deck);

        printInitialCards(players, dealer);
        addCard(players, deck, dealer);
        printFinalCards(dealer, players);
        printGameResult(dealer, players, playerBets);
    }

    private List<String> getUserNames() {
        String rawUserNames = inputView.askUserInputWithMessage(InputMessage.ASK_USER_NAME.getMessage());
        return InputParser.splitByDelimiter(rawUserNames);
    }

    private static Deck getDeck() {
        return Deck.of(new RandomCardShuffler());
    }

    private Players getPlayers() {
        List<String> userNames = getUserNames();
        return Players.of(userNames);
    }

    private void createPlayersBetFromInput(Players players, PlayerBets playersBet) {
        outputView.separatorLine();
        for (Player player : players.getPlayers()) {
            playersBet.add(createPlayerBet(player));
        }
    }

    private PlayerBet createPlayerBet(Player player) {
        String rawBettingAmount = inputView.askUserInputWithMessage(
                InputMessage.ASK_PLAYER_BETTING_AMOUNT.format(player.getName()));
        int bettingAmount = InputParser.parseIntStrict(rawBettingAmount);
        return PlayerBet.of(player, new BigDecimal(bettingAmount));
    }

    private static void addPlayerInitialCard(Players players, Deck deck) {
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

    private void addCard(Players players, Deck deck, Dealer dealer) {
        outputView.separatorLine();
        askPlayersAddCard(players, deck);
        addDealerCard(dealer, deck);
    }

    private void askPlayersAddCard(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            askAddCard(player, deck);
        }
    }

    private void askAddCard(Player player, Deck deck) {
        if (player.isBlackjack()) return;

        String answer = InputMessage.USER_INPUT_YES.getMessage();
        int initialHandSize = player.getCards().size();
        while (!player.isBust() && answer.equals(InputMessage.USER_INPUT_YES.getMessage())) {
            outputView.printfList(InputMessage.ASK_ADD_CARD.getMessage(), player.getName());
            outputView.separatorLine();
            answer = inputView.askUserInput();
            printPlayerCardsIfStand(player, deck, answer, initialHandSize);
        }
    }

    private void printPlayerCardsIfStand(Player player, Deck deck, String answer, int initialHandSize) {
        if (answer.equals(InputMessage.USER_INPUT_NO.getMessage())) {
            printPlayerCardsIfStand(player, initialHandSize);
        }
        if (answer.equals(InputMessage.USER_INPUT_YES.getMessage())) {
            player.addCard(deck.draw());
            outputView.println(getPlayerCardsInfo(player.getName(), getCardsInfo(player.getCards())));
        }
    }

    private void printPlayerCardsIfStand(Player player, int initialHandSize) {
        if (player.getCards().size() == initialHandSize) {
            outputView.println(getPlayerCardsInfo(player.getName(), getCardsInfo(player.getCards())));
        }
    }

    private String getPlayerCardsInfo(String playerName, List<String> cardsInfo) {
        String playerCardsFormat = OutputMessage.DELIMITER.join(cardsInfo);
        return OutputMessage.PLAYER_CARD_INFO.format(playerName, playerCardsFormat);
    }

    private void addDealerCard(Dealer dealer, Deck deck) {
        outputView.separatorLine();

        while (dealer.isHit()) {
            dealer.addCard(deck.draw());
            outputView.println(OutputMessage.DEALER_DRAW_CARD.getMessage());
        }
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

    private void printGameResult(Dealer dealer, Players players, PlayerBets playerBets) {
        outputView.separatorLine();
        playerBets.applyGameResult(dealer);

        outputView.println(OutputMessage.FINAL_MESSAGE.getMessage());

        printDealerProfitResult(playerBets);

        printPlayerProfitResult(players, playerBets);
    }

    private void printDealerProfitResult(PlayerBets playerBets) {
        BigDecimal dealerBettingAmount = BigDecimal.ZERO;
        for (BigDecimal bettingAmount : playerBets.bettingAmounts()) {
            dealerBettingAmount = dealerBettingAmount.add(bettingAmount.negate());
        }
        outputView.println(OutputMessage.DEALER_PROFIT_RESULT_FORMAT.format(dealerBettingAmount.stripTrailingZeros().toPlainString()));
    }

    private void printPlayerProfitResult(Players players, PlayerBets playerBets) {
        for (int i = 0; i < players.getPlayers().size(); i++) {
            String userName = players.getUserNames().get(i);
            BigDecimal bettingAmount = playerBets.bettingAmounts().get(i);
            outputView.println(OutputMessage.PLAYER_RESULT_FORMAT.format(userName, bettingAmount.stripTrailingZeros().toPlainString()));
        }
    }

//    private void printGameResult(Dealer dealer, Players players) {
//        outputView.separatorLine();
//        BlackjackResult blackjackResult = BlackjackResult.of(dealer, players);
//
//        outputView.println(OutputMessage.FINAL_MESSAGE.getMessage());
//
//        outputView.println(
//                OutputMessage.DEALER_RESULT_FORMAT.format(blackjackResult.countDealerWinOrLoseReversePlayerResult(
//                                GameResult.LOSE),
//                        blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.DRAW),
//                        blackjackResult.countDealerWinOrLoseReversePlayerResult(
//                                GameResult.WIN)));
//
//        printPlayersResult(players, blackjackResult);
//    }
//
//    private void printPlayersResult(Players players, BlackjackResult blackjackResult) {
//        List<GameResult> playerResult = blackjackResult.getPlayersResult();
//        for (int i = 0; i < players.getPlayers().size(); i++) {
//            outputView.println(OutputMessage.PLAYER_RESULT_FORMAT.format(players.getUserNames().get(i),
//                    playerResult.get(i).getMessage()));
//        }
//    }
}
