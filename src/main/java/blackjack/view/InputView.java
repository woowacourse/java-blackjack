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

    public String inputWhetherToDrawCard(UserDto userDto) {
        System.out.println(userDto.getUserName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
