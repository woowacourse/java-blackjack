package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.GamerCardsDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printGamersCards(List<GamerCardsDto> gamersCardsDto) {
        printGamerCardsHeader(gamersCardsDto);
        printGamerCardsBody(gamersCardsDto);
    }

    private static void printGamerCardsHeader(List<GamerCardsDto> gamersCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> gamerNames = getGamerNames(gamersCardsDto);
        stringBuilder.append(String.join(", ", gamerNames))
                .append("에게 2장씩 나누었습니다.");
        System.out.println(stringBuilder);
    }

    private static List<String> getGamerNames(List<GamerCardsDto> gamersCardsDto) {
        return gamersCardsDto.stream()
                .map(GamerCardsDto::getName)
                .collect(Collectors.toList());
    }

    private static void printGamerCardsBody(List<GamerCardsDto> gamersCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (GamerCardsDto gamerCardsDto : gamersCardsDto) {
            printGamerCard(gamerCardsDto);
        }
        System.out.println(stringBuilder);
    }

    public static void printGamerCard(GamerCardsDto gamerCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gamerCardsDto.getName()).append(": ");
        stringBuilder.append(String.join(", ", getGamerCards(gamerCardsDto)));
        System.out.println(stringBuilder);
    }

    private static List<String> getGamerCards(GamerCardsDto gamerCardsDto) {
        return gamerCardsDto.getCards()
                .stream()
                .map(CardDto::getCard)
                .collect(Collectors.toList());
    }

    public static void printDealCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
