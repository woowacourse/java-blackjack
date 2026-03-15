package view;

import domain.Bet;
import domain.Card;
import domain.ScoreBoard;
import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    private static final String CARD_JOINER = ", ";

    public static void divideCards(List<String> participants) {
        String players = String.join(CARD_JOINER, participants);
        System.out.println(OutputMessage.DIVIDE.description(players));
    }

    public static void initCardStatus(List<GameStatus> gameStatuses) {
        gameStatuses.forEach(game -> {
            String log = getInitGameLog(game);
            System.out.println(log);
        });
        System.out.println();
    }

    public static void printGameLog(GameStatus gameStatuses) {
        System.out.println(getGameLog(gameStatuses));
    }

    public static void dealerStay() {
        System.out.println(OutputMessage.DEALER_DRAW.description());
    }

    public static void participantsResults(List<GameStatus> gameStatuses) {
        for (GameStatus gameStatus : gameStatuses) {
            System.out.println(
                    OutputMessage.PARTICIPANTS_RESULT.description(getGameLog(gameStatus),
                            gameStatus.score()));
        }
        printTaskDivider();
    }

    public static void gameResult(List<GameResult> gameResults, Bet bet) {
        System.out.println(OutputMessage.RESULT_PROFIT_HEADER.description());

        statisticDealer(gameResults, bet);
        playersWinningLog(gameResults, bet);
    }

    private static void playersWinningLog(List<GameResult> gameResults, Bet bet) {
        for (GameResult gameResult : gameResults) {
            System.out.println(
                    OutputMessage.PLAYER_PROFIT.description(gameResult.name(),
                            ScoreBoard.calculateEarningPrize(gameResult, bet)));
        }
    }

    private static void statisticDealer(List<GameResult> gameResults, Bet bet) {
        System.out.printf(OutputMessage.DEALER_PROFIT.description() + System.lineSeparator(),
                ScoreBoard.calculateDealerProfit(gameResults, bet));
    }

    public static void printTaskDivider() {
        System.out.println();
    }

    private static String getInitGameLog(GameStatus gameStatus) {
        if (gameStatus.isDealer()) {
            return String.format(OutputMessage.GAME_LOG.description(), gameStatus.name(),
                    joinInfo(gameStatus.cards().getFirst()));
        }
        return String.format(getGameLog(gameStatus));
    }

    private static String getGameLog(GameStatus gameStatuses) {
        return OutputMessage.GAME_LOG.description(gameStatuses.name(), handInfo(gameStatuses));
    }

    private static String handInfo(GameStatus gameStatus) {
        List<String> cardList = new ArrayList<>();
        for (Card card : gameStatus.cards()) {
            cardList.add(joinInfo(card));
        }
        return String.join(CARD_JOINER, cardList);
    }

    private static String joinInfo(Card card) {
        return card.rank().label() + card.mark().description();
    }
}
