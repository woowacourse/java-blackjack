package view;

import dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
//    딜러와 pobi, jason에게 2장을 나누었습니다.
//    딜러카드: 3다이아몬드
//    pobi카드: 2하트, 8스페이드
//    jason카드: 7클로버, K스페이드

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
        stringBuilder.append("딜러카드: ");
        String dealerDtoStrings = getCardDtoStrings(resultDto.dealerResultDto().cardDtos());
        stringBuilder.append(dealerDtoStrings);
        stringBuilder.append(" - 결과: ").append(resultDto.dealerResultDto().sum()).append("\n");

        for (PlayerResultDto playerResult : resultDto.playerResultDtos()) {
            stringBuilder.append(playerResult.playerName()).append("카드: ");
            String cardDtoStrings = getCardDtoStrings(playerResult.cardDtos());
            stringBuilder.append(cardDtoStrings);
            stringBuilder.append(" - 결과: ").append(playerResult.sum()).append("\n");
        }
        System.out.println(stringBuilder);
    }

    private String getCardDtoStrings(List<CardDto> cardDtos) {
        return cardDtos.stream()
                .map(CardDto::toString)
                .collect(Collectors.joining(", "));
    }
}
