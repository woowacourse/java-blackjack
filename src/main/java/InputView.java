import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public List<String> readPlayerName() {
        Scanner scanner = new Scanner(System.in);
        List<String> playerNames = new ArrayList<>();
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String s = scanner.nextLine();
        System.out.println();

        if (s == null || s.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(s.split(","))
                .map(String::trim) // split된 항목 앞 뒤 공백 제거
                .filter(c -> !c.isEmpty()) // 콤마가 두번 겹치는 경우 필터링
                .collect(Collectors.toList());
    }

    public String readCommand(String playerName) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            String input = sc.nextLine();
            if (input.equals("y") || input.equals("n")) {
                return input;
            }
            System.out.println("y 혹은 n만 입력할 수 있습니다.");
        }
    }
}
