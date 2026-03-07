package view;

import dto.GameResult;
import dto.GameStatus;
import java.util.List;

public class OutputView {

    public static void divideCards(List<String> participants) {
        String players = String.join(", ", participants);
        System.out.printf(OutputMessage.DIVIDE.description() + System.lineSeparator(), players);
    }

    public static void initCardStatus(List<GameStatus> gameStatuses) {
        gameStatuses.forEach(game -> {
            String log = getInitGameLog(game);
            System.out.println(log);
        });
        System.out.println();
    }

    public static void dealerStay() {
        System.out.println(OutputMessage.DEALER_DRAW.description());
    }

    public static void participantsResults(List<GameStatus> gameStatuses) {
        for (GameStatus gameStatus : gameStatuses) {
            System.out.printf(OutputMessage.PARTICIPANTS_RESULT.description()+System.lineSeparator(), getGameLog(gameStatus), gameStatus.scoreSum());
        }
        System.out.println();
    }

    public static void gameResult(List<GameResult> gameResults) {
        System.out.println(OutputMessage.RESULT_HEADER.description());

        long playersWin = gameResults.stream().filter(cond -> cond.winningCondition().equals("승")).count();
        System.out.printf(OutputMessage.DEALER_WINNING_CONDITION.description() + System.lineSeparator(), gameResults.size() - playersWin, playersWin);
        gameResults.forEach(c -> System.out.printf(OutputMessage.PLAYER_WINNING_CONDITION.description(), c.name(), c.winningCondition() + System.lineSeparator()));
    }


    public static void printGameLog(GameStatus gameStatuses) {
        System.out.printf(getGameLog(gameStatuses) + System.lineSeparator());
    }

    public static void printTaskDivider() {
        System.out.println();
    }

    private static String getGameLog(GameStatus gameStatuses) {
        return String.format(OutputMessage.GAME_LOG.description(), gameStatuses.name(), String.join(", ", gameStatuses.cards()));
    }

    private static String getInitGameLog(GameStatus gameStatuses) {
        if(gameStatuses.name().equals("딜러")) {
            return String.format(OutputMessage.GAME_LOG.description(), gameStatuses.name(), gameStatuses.cards().getFirst());
        }
        return String.format(OutputMessage.GAME_LOG.description(), gameStatuses.name(), String.join(", ", gameStatuses.cards()));
    }
}
