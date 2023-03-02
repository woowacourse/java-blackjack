package view;

import dto.DrawnCardsInfo;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";

    public void printCardSplitMessage(final List<DrawnCardsInfo> infos) {
        String names = infos.stream()
                .filter(info -> !info.getName().equals("딜러"))
                .map(info -> info.getName())
                .collect(Collectors.joining(DELIMITER));

        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");

        infos.stream()
                .forEach(info -> System.out.println(info.getName() + ": " + getCardsInfo(info)));
    }

    private String getCardsInfo(final DrawnCardsInfo info) {
        List<String> drawnCards = info.getDrawnCards();

        return drawnCards.stream()
                .collect(Collectors.joining(DELIMITER));
    }

}
