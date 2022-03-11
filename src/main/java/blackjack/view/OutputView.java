package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GamerCardsDto;
import blackjack.dto.GamerCardsResultDto;
import blackjack.dto.PlayerResultDto;
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

    public static void printDealerCardMessage(int cardsCount) {
        System.out.println();
        if (printNonDealerCardMessage(cardsCount)) {
            return;
        }

        printDealerAddCardMessage(cardsCount);
    }

    private static boolean printNonDealerCardMessage(int addedCardsCount) {
        if (addedCardsCount == 0) {
            System.out.println("딜러는 17이상이어서 카드를 받지 못했습니다.");
            return true;
        }
        return false;
    }

    private static void printDealerAddCardMessage(int addedCardsCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < addedCardsCount; i++) {
            stringBuilder.append("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            stringBuilder.append(System.lineSeparator());
        }
        System.out.print(stringBuilder);
    }

    public static void printGamersCardAndSum(List<GamerCardsResultDto> gamersCardsResults) {
        System.out.println();
        for (GamerCardsResultDto gamerCardsResult : gamersCardsResults) {
            printGamerCardAndSum(gamerCardsResult);
        }
        System.out.println();
    }

    private static void printGamerCardAndSum(GamerCardsResultDto gamerCardsResultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gamerCardsResultDto.getGamerCardsDto().getName()).append(": ");
        stringBuilder.append(String.join(", ", getGamerCards(gamerCardsResultDto.getGamerCardsDto())));
        stringBuilder.append(" - 결과: ").append(gamerCardsResultDto.getSum());
        System.out.println(stringBuilder);
    }

    private static List<String> getGamerCards(GamerCardsDto gamerCardsDto) {
        return gamerCardsDto.getCards()
                .stream()
                .map(CardDto::getCard)
                .collect(Collectors.toList());
    }

    public static void printGameResult(DealerResultDto dealerResult, List<PlayerResultDto> playersResult) {
        StringBuilder stringBuilder = new StringBuilder();
        appendDealerResultToStringBuilder(dealerResult, stringBuilder);

        for (PlayerResultDto playerResult : playersResult) {
            appendPlayerResultToStringBuilder(stringBuilder, playerResult);
        }
        System.out.println(stringBuilder);
    }

    private static void appendDealerResultToStringBuilder(DealerResultDto dealerResult, StringBuilder stringBuilder) {
        stringBuilder.append("## 최종 승패").append(System.lineSeparator());
        stringBuilder.append(dealerResult.getName()).append(": ")
                .append(dealerResult.getWinCount()).append("승 ")
                .append(dealerResult.getLoseCount()).append("패 ")
                .append(dealerResult.getDrawCount()).append("무 ")
                .append(System.lineSeparator());
    }

    private static void appendPlayerResultToStringBuilder(StringBuilder stringBuilder, PlayerResultDto playerResult) {
        stringBuilder.append(playerResult.getName())
                .append(": ")
                .append(playerResult.getResult())
                .append(System.lineSeparator());
    }
}
