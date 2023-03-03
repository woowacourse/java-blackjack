package view;

import java.util.List;
import java.util.Scanner;

import player.Name;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        return List.of(scanner.nextLine().split(","));
    }

    public String readHitCommand(Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n) %n", name.getValue());
        return scanner.nextLine();
    }
}
