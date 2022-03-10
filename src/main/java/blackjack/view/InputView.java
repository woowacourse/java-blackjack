package blackjack.view;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import blackjack.dto.UserDto;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return stream(scanner.nextLine().split(DELIMITER))
                .map(String::trim)
                .collect(toList());
    }

    public boolean inputWhetherToDrawCard(UserDto userDto) {
        System.out.println(userDto.getUserName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();

        validateYN(input);

        return input.equalsIgnoreCase("y");
    }

    private void validateYN(String input) {
        if (!("y".equalsIgnoreCase(input) || "n".equalsIgnoreCase(input))) {
            throw new IllegalArgumentException("y or n need");
        }
    }
}
