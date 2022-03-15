package blackjack.view;

import blackjack.domain.game.ResultCount;
import blackjack.domain.game.ResultType;
import blackjack.dto.DealerMatchDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerMatchDto;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String JOIN_DELIMITER = ", ";
    private static final String COLON_SEPARATOR = ": ";
    private static final String WHITE_SPACE = " ";

    private static final String INITIAL_CARD_DISTRIBUTION_MESSAGE = NEW_LINE + "딜러와 %s에게 2장의 카드를 나누었습니다." + NEW_LINE;
    private static final String NAME_AND_HAND_FORMAT = "%s 카드: %s" + NEW_LINE;
    private static final String PLAYER_BUST_MESSAGE = "버스트! 21을 초과하였습니다!";
    private static final String DEALER_EXTRA_CARD_FORMAT = NEW_LINE + "딜러는 16이하라 %d장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String FINAL_RESULT_MESSAGE = NEW_LINE + "## 최종 승패";
    private static final String PLAYER_MATCH_RESULT_FORMAT = "%s: %s" + NEW_LINE;
    private static final String PARTICIPANT_HAND_AND_SCORE_FORMAT = "%s 카드: %s - 결과: %d" + NEW_LINE;
    private static final String EXCEPTION_MESSAGE_FORMAT = "[ERROR] %s" + NEW_LINE;

    public static void printInitialDistributionInfo(List<ParticipantDto> playerDtos) {
        String names = playerDtos.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));

        System.out.printf(INITIAL_CARD_DISTRIBUTION_MESSAGE, names);
    }

    public static void printInitialDealerHand(ParticipantDto dealerDto) {
        System.out.printf(NAME_AND_HAND_FORMAT, dealerDto.getName(), dealerDto.getHandDto().getFirstCard());
    }

    public static void printInitialPlayersHand(List<ParticipantDto> playerDtos) {
        playerDtos.forEach(OutputView::printParticipantHand);
        printNewLine();
    }

    public static void printParticipantHand(ParticipantDto dto) {
        String name = dto.getName();
        String cards = String.join(JOIN_DELIMITER, dto.getHandDto().getCards());

        System.out.printf(NAME_AND_HAND_FORMAT, name, cards);
    }

    public static void printPlayerBustInfo() {
        System.out.println(PLAYER_BUST_MESSAGE);
    }

    public static void printDealerExtraCardInfo(int count) {
        System.out.printf(DEALER_EXTRA_CARD_FORMAT, count);
    }

    public static void printHandAndScore(List<ParticipantDto> participantDtos) {
        participantDtos.forEach(OutputView::printSingleHandAndScore);
    }

    private static void printSingleHandAndScore(ParticipantDto dto) {
        String name = dto.getName();
        String cards = String.join(JOIN_DELIMITER, dto.getHandDto().getCards());
        int score = dto.getHandDto().getScore();

        System.out.printf(PARTICIPANT_HAND_AND_SCORE_FORMAT, name, cards, score);
    }

    public static void printDealerMatchResult(DealerMatchDto dealerMatchDto) {
        System.out.println(FINAL_RESULT_MESSAGE);
        System.out.print(dealerMatchDto.getName() + COLON_SEPARATOR);

        dealerMatchDto.getMatchResult()
                .entrySet()
                .forEach(OutputView::printSingleDealerMatchResult);

        printNewLine();
    }

    private static void printSingleDealerMatchResult(Entry<ResultType, ResultCount> entrySet) {
        int resultCount = entrySet.getValue().getValue();
        String resultTypeName = entrySet.getKey().getDisplayName();

        if (resultCount > 0) {
            System.out.print(resultCount + resultTypeName + WHITE_SPACE);
        }
    }

    public static void printPlayerMatchResults(Collection<PlayerMatchDto> playerMatchDtos) {
        playerMatchDtos.forEach(OutputView::printSinglePlayerMatchResult);
    }

    public static void printException(RuntimeException exception) {
        System.out.printf(EXCEPTION_MESSAGE_FORMAT, exception.getMessage());
    }

    private static void printSinglePlayerMatchResult(PlayerMatchDto playerMatchDto) {
        String name = playerMatchDto.getName();
        String matchResult = playerMatchDto.getMatchResult().getDisplayName();

        System.out.printf(PLAYER_MATCH_RESULT_FORMAT, name, matchResult);
    }

    private static void printNewLine() {
        System.out.println();
    }
}
