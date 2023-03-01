package view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public void printPlayerNames(List<String> names) {
        System.out.print(names.get(0) + "와 ");
        String nameMessage = IntStream.range(1, names.size()).mapToObj(i -> names.get(i) + ", ")
                .collect(Collectors.joining());
        System.out.println(nameMessage + "에게 2장을 나누었습니다.");
    }


    public void printCardsPerPlayer(final List<String> namesCopy, final List<List<String>> cardsCardsCopy) {
        for (int i = 0; i < namesCopy.size(); i++) {
            System.out.println(namesCopy.get(i) + " : " + cardsCardsCopy.get(i));
        }
    }
}
