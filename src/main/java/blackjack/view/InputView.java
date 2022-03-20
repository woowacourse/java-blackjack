package blackjack.view;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import blackjack.dto.UserDto;

public final class InputView {
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public Map<String, String> inputPlayersAndMoney() {
        return inputMoneyForPlayers(inputPlayerNames());
    }

    public List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return stream(scanner.nextLine().split(DELIMITER))
                .map(String::trim)
                .collect(toList());
    }

    public Map<String, String> inputMoneyForPlayers(List<String> inputNames) {
        return inputNames.stream()
            .collect(toMap(
                Function.identity(),
                this::inputMoney
            ));
    }

    private String inputMoney(String inputName) {
        System.out.println(inputName + "의 배팅 금액은?");
        return scanner.nextLine();
    }

    public boolean inputWhetherToDrawCard(UserDto userDto) {
        System.out.println(userDto.getUserName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        validateYesOrNo(input);
        return input.equalsIgnoreCase(YES);
    }

    private void validateYesOrNo(String input) {
        if (!(YES.equalsIgnoreCase(input) || NO.equalsIgnoreCase(input))) {
            throw new IllegalArgumentException("입력한 값이 유효하지 않습니다. y 또는 n을 입력해주세요.");
        }
    }
}
