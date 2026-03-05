package view;

import java.util.List;

public class OutputView {
    public static void printCardOpen(List<String> names) {
        String format = "딜러와 %s에게 2장을 나누었습니다.";
        String formatedNames = String.format(format, String.join(", ", names));
        System.out.println(formatedNames);
    }
}
