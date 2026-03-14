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

    public void printErrorMessage(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public void printNamePrompt() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printBetMoneyPrompt(String playerName) {
        System.out.println();
        System.out.printf("%s의 배팅 금액은?\n", playerName);
    }

    public void printInitialCardShare(List<ParticipantDto> playerDtos) {
        List<String> playerNames = new ArrayList<>();
        for (ParticipantDto participantDto : playerDtos) {
            playerNames.add(participantDto.name());
        }
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", String.join(DELIMITER, playerNames));
    }

    public void printInitialCardShareDetail(ParticipantDto dealerDto, List<ParticipantDto> playersDto) {
        String dealerCardInfo = consistParticipantCardInfo(dealerDto);
        System.out.println(dealerCardInfo);

        for (ParticipantDto playerDto : playersDto) {
            String playerCardInfo = consistParticipantCardInfo(playerDto);
            System.out.println(playerCardInfo);
        }
    }

    public void printHitOrStandPrompt(ParticipantDto playerDto) {
        System.out.println();
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerDto.name());
    }

    public void printCardShareDetail(ParticipantDto participantDto) {
        String participantCardInfo = consistParticipantCardInfo(participantDto);
        System.out.println(participantCardInfo);
    }

    public void printAdditionalCardForDealerDescription() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardInfosWithSum(List<ParticipantDto> participantDtos) {
        System.out.println();
        for (ParticipantDto participantDto : participantDtos) {
            String participantCardInfoWithSum = consistParticipantCardInfoWithSum(participantDto);
            System.out.println(participantCardInfoWithSum);
        }
    }

    private String consistParticipantCardInfoWithSum(ParticipantDto participantDto) {
        String participantCardInfo = consistParticipantCardInfo(participantDto);
        return String.format("%s - 결과: %d", participantCardInfo, participantDto.score());
    }

    private String consistParticipantCardInfo(ParticipantDto participantDto) {
        List<String> cardInfos = getCardInfos(participantDto);
        return String.format("%s카드: %s", participantDto.name(), String.join(DELIMITER, cardInfos));
    }

    private List<String> getCardInfos(ParticipantDto participantDto) {
        List<String> cardInfos = new ArrayList<>();
        for (CardDto card : participantDto.cards()) {
            String cardInfo = card.cardContentNumber() + card.cardShape();
            cardInfos.add(cardInfo);
        }
        return cardInfos;
    }

    public void printWinTieLossResult(GameResultDto gameResultDto) {
        System.out.println();
        System.out.println("## 최종 수익");

        String dealerWinTieLossResult = consistDealerWinTieLossResult(gameResultDto.dealerWinTieLossResult());
        System.out.println(dealerWinTieLossResult);

        List<String> playerWinTieLossResults = consistPlayerWinTieLossResults(gameResultDto);
        for (String playerWinTieLossResult : playerWinTieLossResults) {
            System.out.println(playerWinTieLossResult);
        }
    }

    private String consistDealerWinTieLossResult(Long dealerWinTieLossResult) {
        return String.format("딜러: %d", dealerWinTieLossResult);
    }

    private List<String> consistPlayerWinTieLossResults(GameResultDto gameResultDto) {
        List<String> playerResult = new ArrayList<>();
        Map<String, Long> playerWinLossResults = gameResultDto.playerWinTieLossResults();
        for (Entry<String, Long> result : playerWinLossResults.entrySet()) {
            String name = result.getKey();
            Long winTieLoss = result.getValue();
            String resultInFormat = String.format("%s: %d", name, winTieLoss);
            playerResult.add(resultInFormat);
        }
        return playerResult;
    }
}
