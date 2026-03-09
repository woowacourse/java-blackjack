package view;

import domain.ParticipantsRole;
import dto.GameResult;
import dto.GameStatus;
import java.util.List;

public class OutputView {
    private static final String WIN = "승";
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
                    OutputMessage.PARTICIPANTS_RESULT.description(getGameLog(gameStatus), gameStatus.scoreSum()));
        }
        printTaskDivider();
    }

    public static void gameResult(List<GameResult> gameResults) {
        System.out.println(OutputMessage.RESULT_HEADER.description());

        statisticDealer(gameResults);
        playersWinningLog(gameResults);
    }

    private static void playersWinningLog(List<GameResult> gameResults) {
        gameResults.forEach(c -> System.out.println(
                OutputMessage.PLAYER_WINNING_CONDITION.description(c.name(), c.winningCondition())));
    }

    private static void statisticDealer(List<GameResult> gameResults) {
        long playersWin = gameResults.stream().filter(cond -> cond.winningCondition().equals(WIN)).count();
        System.out.println(
                OutputMessage.DEALER_WINNING_CONDITION.description(gameResults.size() - playersWin, playersWin));
    }

    public static void printTaskDivider() {
        System.out.println();
    }

    private static String getInitGameLog(GameStatus gameStatuses) {
        if (gameStatuses.role().equals(ParticipantsRole.DEALER)) {
            return String.format(OutputMessage.GAME_LOG.description(), gameStatuses.name(),
                    gameStatuses.cards().getFirst());
        }
        return String.format(getGameLog(gameStatuses));
    }

    private static String getGameLog(GameStatus gameStatuses) {
        return OutputMessage.GAME_LOG.description(gameStatuses.name(), handInfo(gameStatuses));
    }

    private static String handInfo(GameStatus gameStatuses) {
        return String.join(CARD_JOINER, gameStatuses.cards());
    }
}
