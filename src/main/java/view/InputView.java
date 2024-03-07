package view;

import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    // TODO: 뷰 로직 수정 필요
    public static final String DELIMITER = ",";
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readNames() {
        System.out.println("이름을 입력해 주세요");
        return Arrays.asList(scanner.nextLine().split(DELIMITER));
    }

    public String readYesOrNo(Player player) {
        System.out.println(player.getName() + "님의 차례입니다.");
        System.out.println("y/n 입력해 주세요");
        return scanner.nextLine();
    }
}
