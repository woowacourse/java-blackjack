package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";

    public List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = SCANNER.nextLine();
        return List.of(names.split(NAME_DELIMITER, -1));
    }

    //TODO 네이밍 진짜 꼭 변경\
    public boolean isAddCard(String name) {
        return false;
    }
}
