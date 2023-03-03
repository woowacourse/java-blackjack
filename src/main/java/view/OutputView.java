package view;

import java.util.List;

public class OutputView {

    public void printFirstDrawMessage(List<String> names) {
        String joinedNames = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", joinedNames);
    }

}
