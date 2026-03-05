package view;

import dto.CardDto;
import dto.InitialDto;
import dto.PlayerDeckDto;

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
            String cardDtoStrings = playerDeckDto.cardDtos().stream()
                    .map(CardDto::toString)
                    .collect(Collectors.joining(", "));
            stringBuilder.append(cardDtoStrings);
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
