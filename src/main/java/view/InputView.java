package view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return parsePlayerNames(readInput());
    }

    private List<String> parsePlayerNames(String playerNames) {
        try {
            return Arrays.stream(playerNames.split(",", -1))
                    .map(String::trim)
                    .toList();
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("플레이어 이름 입력 형식이 잘못되었습니다.");
        }
    }

    private String readInput() {
        return scanner.nextLine();
    }

    public boolean readProcessHit(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
        String input = readInput();

        return convertYesOrNo(input);
    }

    private boolean convertYesOrNo(String input) {
        if ("y".equals(input)) {
            return true;
        }

        if ("n".equals(input)) {
            return false;
        }

        throw new IllegalArgumentException("선택 입력 형식이 잘못되었습니다.");
    }

    public Map<String, Integer> readPlayerBetting(List<String> playerNames) {
        Map<String, Integer> map = new HashMap<>();
        for (String playerName : playerNames) {
            System.out.printf("%s의 배팅 금액은?", playerName);
            System.out.println();
            try {
                int money = Integer.parseInt(scanner.nextLine());
                map.put(playerName, money);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("잘못된 숫자 형식입니다.");
            }
        }
        return map;
    }
}
