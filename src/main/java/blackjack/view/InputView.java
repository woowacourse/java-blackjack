package blackjack.view;

import blackjack.domain.participant.Command;
import blackjack.domain.participant.Name;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";

    private InputView() {

    }

    public static List<Name> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        List<Name> names = toNameList(Arrays.asList(input.split(NAME_DELIMITER, -1)));
        checkDistinct(names);
        return names;
    }

    private static List<Name> toNameList(List<String> names) {
        return names.stream()
                .map(name -> new Name(name.trim()))
                .collect(Collectors.toUnmodifiableList());
    }

    private static void checkDistinct(List<Name> names) {
        long distinctCount = names.stream()
                .distinct()
                .count();

        if (distinctCount != names.size()) {
            throw new IllegalArgumentException("중복 이름은 불가능합니다.");
        }
    }

    public static Command inputWantDraw(String name) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n"
                , name, Command.HIT.getValue(), Command.STAY.getValue());
        String input = scanner.nextLine().toLowerCase().trim();
        return Command.findCommand(input);
    }
}
