package view;

import dto.CardDto;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class OutputView {

    public static final String LINE = System.lineSeparator();

    public void printPlayers(final Set<String> playerNames) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다." + LINE, joinByDelimiter(playerNames));
    }

    public void printCardsWithName(final String name, final List<CardDto> cardDtos) {
        List<String> cardOutputs = cardDtos.stream()
                .map(this::concatCardDto)
                .toList();
        System.out.printf("%s카드: %s" + LINE, name, joinByDelimiter(cardOutputs));
    }

    public void printDealerDrawn() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerFirstCard(final CardDto firstCard) {
        System.out.printf("딜러카드: %s" + LINE, concatCardDto(firstCard));
    }

    public void printNewLine() {
        System.out.println();
    }

    private String joinByDelimiter(final Collection<String> values) {
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
        System.out.println("## 최종 승패");
    }

    public void printDealerGameResult(final int winCount, final int drawCount, final int loseCount) {
        System.out.printf("딜러: %d승 %d무 %d패" + LINE, winCount, drawCount, loseCount);
    }

    public void printPlayerGameResult(final String name, final String result) {
        System.out.printf("%s: %s" + LINE, name, result);
    }
}
