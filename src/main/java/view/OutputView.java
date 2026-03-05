package view;

import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {
    public static void displayCardDistribution(List<String> names) {
        String nameContent = String.join(", ", names);
        System.out.printf("딜러가 %s에게 2장을 나누었습니다.\n", nameContent);
    }
}
