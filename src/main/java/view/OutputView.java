package view;

import dto.CardDto;
import dto.GameResultDto;
import dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String PARTICIPANT_CARD_INFO_FORMAT = "%s카드: %s";
    private static final String PARTICIPANT_CARD_INFO_FORMAT_LINE = "%s카드: %s\n";
    private static final String WIN_LOSS_RESULT_FORMAT = "%s: %s\n";

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printNamePrompt() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printInitialCardShareDetail(ParticipantDto dealerDto, List<ParticipantDto> players) {
        List<String> playerNames = new ArrayList<>();
        StringBuilder initialCardShareDetail = new StringBuilder();

        String dealerCardInfo = consistCardInfo(PARTICIPANT_CARD_INFO_FORMAT_LINE, dealerDto);
        initialCardShareDetail.append(dealerCardInfo);

        for (ParticipantDto playerDto : players) {
            playerNames.add(playerDto.name());
            String playerCardInfo = consistCardInfo(PARTICIPANT_CARD_INFO_FORMAT_LINE, playerDto);
            initialCardShareDetail.append(playerCardInfo);
        }

        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", String.join(DELIMITER, playerNames));
        System.out.println(initialCardShareDetail);
    }

    public void printHitOrStandPrompt(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
    }

    public void printUserCardInfo(ParticipantDto participantDto) {
        String userCardInfo = consistCardInfo(PARTICIPANT_CARD_INFO_FORMAT_LINE, participantDto);
        System.out.println(userCardInfo);
    }

    public void printAdditionalCardForDealerDescription() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardInfoWithSum(GameResultDto gameResultDto) {
        StringBuilder cardInfoWithSum = new StringBuilder();

        ParticipantDto dealerDto = gameResultDto.dealerDto();
        String dealerCardInfoWithSum = consistCardInfoWithSum(dealerDto);
        cardInfoWithSum.append(dealerCardInfoWithSum);

        for (ParticipantDto playerDto : gameResultDto.playerDtos()) {
            String playerCardInfoWithSum = consistCardInfoWithSum(playerDto);
            cardInfoWithSum.append(playerCardInfoWithSum);
        }

        System.out.println();
        System.out.println(cardInfoWithSum);
    }

    public void printWinLossResult(GameResultDto gameResultDto) {
        StringBuilder winLossResult = new StringBuilder();
        winLossResult.append("## 최종 승패\n");

        String dealerResult = consistDealerResult(gameResultDto);
        winLossResult.append(dealerResult);

        String playerResults = consistPlayerResults(gameResultDto);
        winLossResult.append(playerResults);

        System.out.println(winLossResult);
    }

    private String consistCardInfo(String infoFormat, ParticipantDto participantDto) {
        List<String> cardInfos = new ArrayList<>();
        for (CardDto card : participantDto.cards()) {
            cardInfos.add(card.cardContentNumber() + card.cardShape());
        }
        return String.format(infoFormat, participantDto.name(),
                String.join(DELIMITER, cardInfos));
    }

    private String consistCardInfoWithSum(ParticipantDto participantDto) {
        return String.format("%s - 결과: %d\n",
                consistCardInfo(PARTICIPANT_CARD_INFO_FORMAT, participantDto), participantDto.score());
    }

    private String consistDealerResult(GameResultDto gameResultDto) {
        String dealerName = gameResultDto.dealerDto().name();
        Map<String, Integer> dealerWinLossResults = gameResultDto.dealerWinLossResults();
        StringBuilder result = new StringBuilder();
        for (Entry<String, Integer> dealerResult : dealerWinLossResults.entrySet()) {
            result.append(dealerResult.getValue());
            result.append(dealerResult.getKey());
            result.append(" ");
        }
        result.deleteCharAt(result.length() - 1);
        return String.format(WIN_LOSS_RESULT_FORMAT, dealerName, result);
    }

    private String consistPlayerResults(GameResultDto gameResultDto) {
        Map<String, String> playerWinLossResults = gameResultDto.playerWinLossResults();
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> playerResult : playerWinLossResults.entrySet()) {
            String playerName = playerResult.getKey();
            String playerWinLossResult = playerResult.getValue();
            result.append(String.format(WIN_LOSS_RESULT_FORMAT, playerName, playerWinLossResult));
        }
        return result.toString();
    }
}
