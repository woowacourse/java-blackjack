package view;

import dto.response.AllPlayerWinningInfoResponse;
import dto.response.AllPlayersNameAndCardsResponse;
import dto.response.DealerWinningStatisticsResponse;
import dto.response.NameAndCardsResponse;
import dto.response.PlayedGameResultResponse;
import dto.response.PlayerGameResultsResponse;
import dto.request.PlayerNamesResponse;
import dto.response.PlayerWinningInfoResponse;
import java.util.List;

public final class OutputView {

    private static final String JOINER = ", ";
    private static final String CONDITION_JOINER = " ";

    private OutputView() {

    }

    public static void distributeCards(PlayerNamesResponse response) {
        String players = String.join(JOINER, response.names());
        System.out.println(OutputMessage.DISTRIBUTE.description(players));
    }

    public static void initDealerCardInfos(NameAndCardsResponse response) {
        String cards = response.cardInfos().getFirst();
        System.out.println(OutputMessage.NAME_AND_CARDS.description(response.name(), cards));
    }

    public static void initAllPlayerCardInfos(AllPlayersNameAndCardsResponse response) {
        List<NameAndCardsResponse> infos = response.allInfos();
        infos.forEach(OutputView::playerCardInfos);
    }

    public static void playerCardInfos(NameAndCardsResponse response) {
        System.out.println(nameAndAllCards(response.name(), response.cardInfos()));
    }

    private static String nameAndAllCards(String name, List<String> cardInfos) {
        String cards = String.join(JOINER, cardInfos);
        return OutputMessage.NAME_AND_CARDS.description(name, cards);
    }

    public static void dealerDrawCard() {
        System.out.println(OutputMessage.DEALER_DRAW_CARD.description());
    }

    public static void participantResult(PlayedGameResultResponse response) {
        String nameAndCards = nameAndAllCards(response.name(), response.cardInfos());
        int score = response.scoreSum();

        System.out.println(OutputMessage.PARTICIPANTS_RESULT.description(nameAndCards, score));
    }

    public static void playerResults(PlayerGameResultsResponse playerGameResultsResponse) {
        playerGameResultsResponse.results()
                .forEach(OutputView::participantResult);
    }

    public static void winningConditionsHeader() {
        winningConditionHeader();
    }

    public static void dealerWinningStatistics(DealerWinningStatisticsResponse response) {
        StringBuilder buffer = new StringBuilder();

        appendWin(response.winCount(), buffer);
        appendDraw(response.drawCount(), buffer);
        appendLose(response.loseCount(), buffer);

        System.out.println(OutputMessage.DEALER_WINNING_CONDITION.description(buffer));
    }

    private static void appendWin(int winCount, StringBuilder statistics) {
        if (winCount > 0) {
            statistics.append(OutputMessage.WIN.description(winCount)).append(CONDITION_JOINER);
        }
    }

    private static void appendDraw(int drawCount, StringBuilder statistics) {
        if (drawCount > 0) {
            statistics.append(OutputMessage.DRAW.description(drawCount)).append(CONDITION_JOINER);
        }
    }

    private static void appendLose(int loseCount, StringBuilder statistics) {
        if (loseCount > 0) {
            statistics.append(OutputMessage.LOSE.description(loseCount));
        }
    }

    public static void playerWinningConditions(AllPlayerWinningInfoResponse response) {
        List<PlayerWinningInfoResponse> winningInfos = response.playerWinningInfoResponses();

        winningInfos.forEach(info -> {
            System.out.println(OutputMessage.PLAYER_WINNING_CONDITION.description(info.name(), info.winningCondition()));
        });
    }

    private static void winningConditionHeader() {
        System.out.println(OutputMessage.RESULT_HEADER.description());
    }

    public static void printTaskDivider() {
        System.out.println();
    }
}
