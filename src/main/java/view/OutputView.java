package view;

import dto.CardDto;
import java.util.List;

public class OutputView {

    public static final String LINE = System.lineSeparator();

    public void printPlayers(final List<String> playerNames) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다." + LINE, joinByDelimiter(playerNames));
    }

    public void printCardsWithName(final String name, final List<CardDto> cardDtos) {
        List<String> cardOutputs = cardDtos.stream()
                .map(this::concatCardDto)
                .toList();
        System.out.printf("%s카드: %s" + LINE, name, joinByDelimiter(cardOutputs));
    }

    public void printDealerDrawn(int drawCount) {
        System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다." + LINE, drawCount);
    }

    public void printDealerFirstCard(final CardDto firstCard) {
        System.out.printf("딜러카드: %s" + LINE, concatCardDto(firstCard));
    }

    public void printNewLine() {
        System.out.println();
    }

    private String joinByDelimiter(final List<String> values) {
        return String.join(", ", values);
    }

    private String concatCardDto(final CardDto cardDto) {
        return cardDto.number() + cardDto.shape();
    }

    public void printCardsWithNameAndResult(final String name, final List<CardDto> cardDtos, final int result) {
        List<String> cardOutputs = cardDtos.stream()
                .map(this::concatCardDto)
                .toList();
        System.out.printf("%s카드: %s - 결과: %d" + LINE, name, joinByDelimiter(cardOutputs), result);
    }

    public void printGameResultHeader() {
        System.out.println("## 최종 수익");
    }

    public void printProfitWithName(final String name, final int profit) {
        System.out.printf("%s: %d" + LINE, name, profit);
    }
}
