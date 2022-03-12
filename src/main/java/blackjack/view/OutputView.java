package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.ResultType;
import blackjack.domain.participant.Player;
import blackjack.dto.InitialDistributionDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.ResultStatsDto;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String JOIN_DELIMITER = ", ";
    private static final String INITIAL_CARD_DISTRIBUTION_MESSAGE = NEW_LINE + "딜러와 %s에게 2장의 카드를 나누었습니다." + NEW_LINE;
    private static final String PLAYER_CARDS_FORMAT = "%s 카드: %s";
    private static final String DEALER_BLACKJACK_MESSAGE = NEW_LINE + "블랙잭! 게임을 종료합니다.";
    private static final String PARTICIPANT_CARDS_AND_SCORE_FORMAT = NEW_LINE + "%s 카드: %s - 결과: %d";
    private static final String PLAYER_BUST_MESSAGE = "버스트! 21을 초과하였습니다!";
    private static final String DEALER_EXTRA_CARD_MESSAGE = NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_ANNOUNCEMENT_MESSAGE = "## 최종 승패" + NEW_LINE;
    private static final String PARTICIPANT_RESULT_FORMAT = "%s: %s";

    public static void printInitialParticipantsCards(InitialDistributionDto dto) {
        String message = getParticipantsCardCountInfo(dto.getPlayerNames())
                + getAllParticipantCardInfos(dto.getParticipantsInfo())
                + NEW_LINE;

        print(message);
    }

    public static void printDealerBlackjackInfo(InitialDistributionDto dto) {
        String message = getParticipantsCardCountInfo(dto.getPlayerNames())
                + DEALER_BLACKJACK_MESSAGE;

        print(message);
    }

    private static String getParticipantsCardCountInfo(List<String> playerNames) {
        String joinedPlayerNames = String.join(JOIN_DELIMITER, playerNames);
        return String.format(INITIAL_CARD_DISTRIBUTION_MESSAGE, joinedPlayerNames);
    }

    private static String getAllParticipantCardInfos(List<ParticipantCardsDto> participantInfos) {
        return participantInfos.stream()
                .map(OutputView::getParticipantCardsInfo)
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String getParticipantCardsInfo(ParticipantCardsDto dto) {
        String cards = getCardsInfo(dto.getCards());
        return String.format(PLAYER_CARDS_FORMAT, dto.getName(), cards);
    }

    public static void printPlayerCardsInfo(Player player) {
        String playerCards = getCardsInfo(player.getCards());
        print(String.format(PLAYER_CARDS_FORMAT, player.getName(), playerCards));
    }

    public static void printPlayerBustInfo() {
        print(PLAYER_BUST_MESSAGE);
    }

    public static void printDealerExtraCardInfo() {
        print(DEALER_EXTRA_CARD_MESSAGE);
    }

    public static void printAllCardsAndScore(GameResultDto dto) {
        print(getJoinedCardsAndScores(dto.getResults()) + NEW_LINE);
    }

    private static String getJoinedCardsAndScores(List<ResultStatsDto> dtos) {
        return dtos.stream()
                .map(OutputView::getParticipantCardsAndScore)
                .collect(Collectors.joining());
    }

    private static String getParticipantCardsAndScore(ResultStatsDto dto) {
        String participantName = dto.getParticipantName();
        String cards = getCardsInfo(dto.getCards());
        int score = dto.getScore().toInt();

        return String.format(PARTICIPANT_CARDS_AND_SCORE_FORMAT, participantName, cards, score);
    }

    public static void printGameResult(GameResultDto dto) {
        String gameResultFullTexts = FINAL_RESULT_ANNOUNCEMENT_MESSAGE
                + getAllParticipantsGameResults(dto.getResults());

        print(gameResultFullTexts);
    }

    private static String getAllParticipantsGameResults(List<ResultStatsDto> dtos) {
        return dtos.stream()
                .map(OutputView::getParticipantGameResult)
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String getParticipantGameResult(ResultStatsDto dto) {
        String name = dto.getParticipantName();

        if (dto.hasSingleResultType()) {
            return getSingleResultTypeFormat(dto, name);
        }
        return getMultipleResultTypeFormat(dto, name);
    }

    private static String getSingleResultTypeFormat(ResultStatsDto dto, String name) {
        ResultType resultType = dto.getResultStatsTypes().get(0);

        if (dto.hasMultipleCountOf(resultType)) {
            return String.format(PARTICIPANT_RESULT_FORMAT, name, getMultipleCountResultTextOf(dto, resultType));
        }
        return String.format(PARTICIPANT_RESULT_FORMAT, name, resultType.getDisplayName());
    }

    private static String getMultipleResultTypeFormat(ResultStatsDto dto, String name) {
        String multipleResultTypesInfo = dto.getResultStatsTypes().stream()
                .map(resultType -> getMultipleCountResultTextOf(dto, resultType))
                .collect(Collectors.joining(" "));

        return String.format(PARTICIPANT_RESULT_FORMAT, name, multipleResultTypesInfo);
    }

    private static String getMultipleCountResultTextOf(ResultStatsDto dto, ResultType resultType) {
        int count = dto.getCountValueOf(resultType);
        String resultDisplayName = resultType.getDisplayName();

        return count + resultDisplayName;
    }

    private static String getCardsInfo(Set<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
