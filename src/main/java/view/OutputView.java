package view;

import dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void outputInitialMessage(InitialDto initialDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ");
        List<String> playerNames = initialDto.playerDeckDtos().stream()
                .map(PlayerDeckDto::playerName)
                .toList();

        stringBuilder.append(String.join(", ", playerNames));
        stringBuilder.append("에게 2장을 나누었습니다.").append("\n");

        stringBuilder.append("딜러카드: ").append(initialDto.dealerCard().toString()).append("\n");
        for (PlayerDeckDto playerDeckDto : initialDto.playerDeckDtos()) {
            stringBuilder.append(playerDeckDto.playerName()).append(": ");
            String cardDtoStrings = getCardDtoStrings(playerDeckDto.cardDtos());
            stringBuilder.append(cardDtoStrings);
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    public void playerResultMessage(ResultDto resultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n").append("딜러카드: ");
        String dealerDtoStrings = getCardDtoStrings(resultDto.dealerResultDto().cardDtos());
        stringBuilder.append(dealerDtoStrings);
        stringBuilder.append(" - 결과: ").append(resultDto.dealerResultDto().sum()).append("\n");

        for (PlayerResultDto playerResult : resultDto.playerResultDtos()) {
            stringBuilder.append(playerResult.playerName()).append("카드: ");
            String cardDtoStrings = getCardDtoStrings(playerResult.cardDtos());
            stringBuilder.append(cardDtoStrings);
            stringBuilder.append(" - 결과: ").append(playerResult.sum()).append("\n");
        }

        stringBuilder.append("\n").append("## 최종 수익").append("\n");
        stringBuilder.append("딜러: ").append(resultDto.dealerFinalMoney()).append("\n");
        for (PlayerResultDto playerResultDto : resultDto.playerResultDtos()) {
            stringBuilder.append(playerResultDto.playerName()).append(": ").append(playerResultDto.finalMoney()).append("\n");
        }
        System.out.println(stringBuilder);
    }

    //pobi카드: 2하트, 8스페이드, A클로버
    public void outputPlayerDeckDtos(PlayerResultDto playerResultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(playerResultDto.playerName()).append("카드: ");
        stringBuilder.append(getCardDtoStrings(playerResultDto.cardDtos())).append("\n");
        System.out.println(stringBuilder);
    }

    private String getCardDtoStrings(List<CardDto> cardDtos) {
        return cardDtos.stream()
                .map(CardDto::toString)
                .collect(Collectors.joining(", "));
    }

    public void outputDealerAdditionCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
