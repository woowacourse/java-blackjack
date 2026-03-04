package view;

import java.util.List;

public class OutputView {

    public void printPlayers(List<String> names) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", String.join(", ", names));
    }
}
