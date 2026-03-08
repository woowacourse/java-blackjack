package view;

import common.Constants;
import dto.AllPlayersNameAndCardsResponse;
import dto.GameResult;
import dto.GameStatus;
import dto.NameAndCardsResponse;
import dto.PlayerNamesResponse;
import java.util.List;

public class OutputView {

//    public static void divideCards(List<String> participants) {
//        String players = String.join(Constants.CARD_JOINER, participants);
//        System.out.println(OutputMessage.DIVIDE.description(players));
//    }

    public static void distributeCards(PlayerNamesResponse response) {
        String players = String.join(Constants.JOINER, response.names());

        System.out.println(OutputMessage.DISTRIBUTE.description(players));
    }

    public static void initDealerCardInfos(NameAndCardsResponse response){
        String cards = response.cardInfos().getFirst();
        System.out.println(OutputMessage.NAME_AND_CARDS.description(response.name(), cards));
    }

    public static void initAllPlayerCardInfos(AllPlayersNameAndCardsResponse response){
        List<NameAndCardsResponse> infos = response.allInfos();
        infos.forEach(OutputView::playerCardInfos);
    }

    public static void playerCardInfos(NameAndCardsResponse response){
        System.out.println(nameAndAllCards(response));
    }

    private static String nameAndAllCards(NameAndCardsResponse response) {
        String cards = String.join(Constants.JOINER, response.cardInfos());
        return OutputMessage.NAME_AND_CARDS.description(response.name(),cards);
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
        long playersWin = gameResults.stream().filter(cond -> cond.winningCondition().equals(Constants.WIN)).count();
        System.out.println(
                OutputMessage.DEALER_WINNING_CONDITION.description(gameResults.size() - playersWin, playersWin));
    }

    public static void printTaskDivider() {
        System.out.println();
    }

    private static String getInitGameLog(GameStatus gameStatuses) {
        if (gameStatuses.name().equals(Constants.DEALER_NAME)) {
            return String.format(OutputMessage.NAME_AND_CARDS.description(), gameStatuses.name(),
                    gameStatuses.cards().getFirst());
        }
        return String.format(getGameLog(gameStatuses));
    }

    private static String getGameLog(GameStatus gameStatuses) {
        return OutputMessage.NAME_AND_CARDS.description(gameStatuses.name(), handInfo(gameStatuses));
    }

    private static String handInfo(GameStatus gameStatuses) {
        return String.join(Constants.JOINER, gameStatuses.cards());
    }
}
