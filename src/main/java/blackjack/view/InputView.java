package blackjack.view;

import blackjack.domain.player.Choice;
import blackjack.domain.player.Participant;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static List<String> getParticipantNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return trim(split(input));
    }

    private static String[] split(final String input) {
        return input.split(DELIMITER);
    }

    private static List<String> trim(final String[] numbers) {
        return Arrays.stream(numbers)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Choice getChoice(Participant participant) {
        System.out.println(participant.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return Choice.from(scanner.nextLine());
    }
}
