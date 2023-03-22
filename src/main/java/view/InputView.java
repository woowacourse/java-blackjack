package view;

import domain.Name;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> getPlayer() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public boolean addOrStop(final String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        validateYOrN(input);
        return input.equals("y");
    }

    private void validateYOrN(final String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("y 혹은 n 만 입력가능합니다. 입력한 문자 : " +input);
        }
    }

    public List<Integer> inputBettingMoneys(List<Name> names){
        List<Integer> bettingMoneys = new ArrayList<>();
        for (Name name : names) {
            System.out.println(name.getName() + "의 베팅 금액은?");
            bettingMoneys.add(Integer.parseInt(scanner.nextLine()));
        }
        return bettingMoneys;
    }
}
