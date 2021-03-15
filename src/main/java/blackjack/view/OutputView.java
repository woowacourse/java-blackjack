package blackjack.view;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameResult;
import blackjack.domain.user.OneGameResult;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String STRING_DELIMITER = ", ";
    private static final String INITIAL_CARD_MESSAGE = "딜러와 %s에게 카드를 2장씩 나누었습니다.";
    private static final String DEALER_CARD = "딜러 카드: %s";
    private static final String PLAYER_CARD = "%s 카드: %s";
    private static final String DEALER_RESULT = "딜러: %s";
    private static final String PLAYER_RESULT = "%s: %s";
    private static final String SCORE_RESULT = " - 결과: ";
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT = "## 최종 승패";
    private static final String BURST_MESSAGE = "BUST";
    private static final int DEALER_SHOW_CARD_INDEX = 1;


    private OutputView() {
    }

    public static void showInitiate(BlackJackGame blackJackGame) {
        System.out.println();
        Dealer dealer = blackJackGame.getDealer();
        Players players = blackJackGame.getPlayers();
        showName(players);
        showCards(dealer, players);
    }

    private static void showName(Players players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players.getRawPlayers()) {
            playerNames.add(player.getName());
        }
        String nameCollect = String.join(STRING_DELIMITER, playerNames);
        System.out.printf(INITIAL_CARD_MESSAGE + NEWLINE, nameCollect);
    }

    private static void showCards(Dealer dealer, Players players) {
        String dealerCard = dealer.getUserDeck()
            .getUserCards()
            .get(DEALER_SHOW_CARD_INDEX)
            .getCard();
        System.out.printf(DEALER_CARD + NEWLINE, dealerCard);
        for (Player player : players.getRawPlayers()) {
            showPlayerUnitCard(player);
        }
    }

    public static void showPlayerCard(BlackJackGame blackJackGame, int playerIndex) {
        Player player = blackJackGame.getPlayer(playerIndex);
        showPlayerUnitCard(player);
    }

    private static void showPlayerUnitCard(Player player) {
        String cards = combineAllCard(player);
        System.out.printf(PLAYER_CARD + NEWLINE, player.getName(), cards);
    }

    private static String combineAllCard(User user) {
        List<String> allCards = new ArrayList<>();
        for (Card card : user.getUserDeck().getUserCards()) {
            allCards.add(card.getCard());
        }
        return String.join(STRING_DELIMITER, allCards);
    }

    public static void showDealerDraw() {
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public static void showScoreResult(BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        Players players = blackJackGame.getPlayers();
        System.out.println();
        showDealerEntireCard(dealer);
        showPlayersEntireCard(players);
    }

    private static void showDealerEntireCard(Dealer dealer) {
        String dealerCards = String.format(DEALER_CARD, combineAllCard(dealer)) + SCORE_RESULT +
            getConventionScore(dealer);
        System.out.println(dealerCards);
    }

    private static void showPlayersEntireCard(Players players) {
        for (Player player : players.getRawPlayers()) {
            String dealerCards = String.format(PLAYER_CARD, player.getName(),
                combineAllCard(player)) + SCORE_RESULT + getConventionScore(player);
            System.out.println(dealerCards);
        }
    }

    private static String getConventionScore(User user) {
        int userScore = user.getPoint();
        if (userScore == 0) {
            return BURST_MESSAGE;
        }
        return Integer.toString(userScore);
    }

    public static void showDealerTable(GameResult gameResult) {
        System.out.println(NEWLINE + FINAL_RESULT);
        String conventionResult = getDealerResult(gameResult);
        String conventionResultMessage = String.format(DEALER_RESULT, conventionResult);
        System.out.println(conventionResultMessage);
    }

    private static String getDealerResult(GameResult gameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<OneGameResult, Integer> element : gameResult.getDealerResult().entrySet()) {
            stringBuilder.append(validValue(element));
        }
        return stringBuilder.toString();
    }

    private static String validValue(Map.Entry<OneGameResult, Integer> element) {
        if (element.getValue() > 0) {
            return element.getValue() + element.getKey().getResult();
        }
        return "";
    }

    public static void showIndividualTable(GameResult gameResult) {
        Map<Player, OneGameResult> gamePlayerResult = gameResult.getPlayersResult();
        for (Map.Entry<Player, OneGameResult> playerResult : gamePlayerResult.entrySet()) {
            showEachPlayerResult(playerResult);
        }
    }

    private static void showEachPlayerResult(Map.Entry<Player, OneGameResult> playerResult) {
        String playerName = playerResult.getKey()
            .getName();
        String gameIndividualResult = playerResult.getValue()
            .getResult();
        String individualResultMessage = String
            .format(PLAYER_RESULT, playerName, gameIndividualResult);
        System.out.println(individualResultMessage);
    }

    public static void showMoneyStatue(BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        Players players = blackJackGame.getPlayers();
        System.out.println("## 최종 수익");
        showDealerMoneyState(dealer);
        showPlayersMoneyState(players);
    }

    private static void showDealerMoneyState(Dealer dealer) {
        int rawMoneyValue = dealer.getMoney()
            .getValue();
        String rawMoneyValueMessage = String.valueOf(rawMoneyValue);
        System.out.printf(DEALER_RESULT, rawMoneyValueMessage);
        System.out.println();
    }

    private static void showPlayersMoneyState(Players players) {
        players.getRawPlayers()
            .forEach(OutputView::showPlayerMoneyState);
    }

    private static void showPlayerMoneyState(Player player) {
        int rawMoneyValue = player.getMoney()
            .getValue();
        String rawMoneyValueMessage = String.valueOf(rawMoneyValue);
        System.out.printf(PLAYER_RESULT, player.getName(), rawMoneyValueMessage);
        System.out.println();
    }
}
