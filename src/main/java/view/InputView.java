package view;

import domain.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<Player> getPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        return Arrays.stream(input.split(","))
            .map(String::trim)
            .map(Player::new)
            .toList();
    }
//pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
    public boolean getYesOrNo(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("y")) {
            return true;
        }
        if(input.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException("y또는 n만 입력가능합니다.");
    }
}
