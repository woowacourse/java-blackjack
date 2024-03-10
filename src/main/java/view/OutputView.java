package view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static model.blackjackgame.ResultStatus.LOOSE;
import static model.blackjackgame.ResultStatus.PUSH;
import static model.blackjackgame.ResultStatus.WIN;

import java.util.List;
import java.util.Map;
import model.blackjackgame.BlackjackGame;
import model.blackjackgame.GameResult;
import model.blackjackgame.GameScore;
import model.blackjackgame.ResultStatus;
import model.card.Card;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String DELIMITER_GAME_RESULT = " ";
    private static final String DEALER_NAME = "딜러";
    private static final String INIT_CARDS_INTRO =
        NEWLINE + DEALER_NAME + "와 %s에게 2장을 나누었습니다." + NEWLINE;
    private static final String DEALER_CARD_FORMAT = DEALER_NAME + ": %s" + NEWLINE;
    private static final String CARDS_FORMAT = "%s카드: %s" + NEWLINE;
    private static final String DEALER_HIT = NEWLINE + DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_SCORE_FORMAT = NEWLINE + "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_INTRO = NEWLINE + NEWLINE + "## 최종 승패";
    private static final String GAMER_RESULT_FORMAT = NEWLINE + "%s: %s";
    private static final String EXCEPTION_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printInitCards(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        printInitCardsIntro(players);
        printDealerCard(dealer);
        printPlayersCards(players);
    }

    private static void printInitCardsIntro(Players players) {
        String playerNames = String.join(DELIMITER, players.names());
        System.out.printf(INIT_CARDS_INTRO, playerNames);
    }

    private static void printDealerCard(Dealer dealer) {
        List<Card> cards = dealer.getCards().getElements();
        System.out.printf(DEALER_CARD_FORMAT, cards.get(0));
    }

    private static void printPlayersCards(Players players) {
        players.getElements()
            .forEach(OutputView::printPlayerCard);
        System.out.println();
    }

    public static void printPlayerCard(Player player) {
        String playerCards = createCardsMessage(player.getCards());
        System.out.printf(CARDS_FORMAT, player.getName(), playerCards);
    }

    public static void printAfterDealerHit() {
        System.out.println(DEALER_HIT);
    }

    public static void printFinalScore(Dealer dealer, Players players, GameResult gameResult) {
        String dealerCards = createCardsMessage(dealer.getCards());
        System.out.printf(FINAL_SCORE_FORMAT, DEALER_NAME, dealerCards, gameResult.findDealerScore());
        for (Player player : players.getElements()) {
            String playerName = player.getName();
            String playerCards = createCardsMessage(player.getCards());
            System.out.printf(FINAL_SCORE_FORMAT, playerName, playerCards, gameResult.findPlayerScore(playerName));
        }
    }

    private static String createCardsMessage(Cards cards) {
        return cards.getElements()
            .stream()
            .map(Card::toString)
            .collect(collectingAndThen(toList(), result -> String.join(DELIMITER, result)));
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.print(GAME_RESULT_INTRO);
        printDealerGameResult(gameResult);
        printPlayersGameResult(gameResult);
    }

    private static void printDealerGameResult(GameResult gameResult) {
        Map<ResultStatus, Integer> dealerGameResult = gameResult.findDealerGameResult();
        String result = "";
        if (dealerGameResult.containsKey(WIN)) {
            result += dealerGameResult.get(WIN) + WIN.getDisplayName() + DELIMITER_GAME_RESULT;
        }
        if (dealerGameResult.containsKey(PUSH)) {
            result += dealerGameResult.get(PUSH) + PUSH.getDisplayName() + DELIMITER_GAME_RESULT;
        }
        if (dealerGameResult.containsKey(LOOSE)) {
            result += dealerGameResult.get(LOOSE) + LOOSE.getDisplayName();
        }
        System.out.printf(GAMER_RESULT_FORMAT, DEALER_NAME, result);
    }

    private static void printPlayersGameResult(GameResult gameResult) {
        List<GameScore> playersScore = gameResult.getPlayersScore();
        playersScore.forEach(playerScore -> printPlayerGameResult(playerScore, gameResult));

    }

    private static void printPlayerGameResult(GameScore playerScore, GameResult gameResult) {
        ResultStatus result = gameResult.findGameResult(playerScore, gameResult.getDealerScore());
        System.out.printf(GAMER_RESULT_FORMAT, playerScore.getName(), result.getDisplayName());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(EXCEPTION_PREFIX + message);
    }
}
