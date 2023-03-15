package view;

import domain.player.Name;
import domain.player.participant.Participant;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String YES_COMMAND = "y";
    private static final String NO_COMMAND = "n";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readParticipantsName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();

        return Arrays.stream(input.split(","))
                     .collect(Collectors.toList());
    }

    public static boolean readMoreCard(final Participant participant) {
        System.out.println(participant.name().value() + "는 한장의 카드를 더 받으시겠습니다?(예는 y, 아니오는 n)");

        final String input = scanner.nextLine();

        if (validateIneligibleCommand(input)) {
            return readMoreCard(participant);
        }

        return input.equals(YES_COMMAND);
    }

    private static boolean validateIneligibleCommand(final String input) {
        try {
            if (ineligibleCommand(input)) {
                throw new IllegalArgumentException(input + " 은 명령어가 아닙니다.");
            }
        } catch (IllegalArgumentException exception) {
            return true;
        }
        return false;
    }

    private static boolean ineligibleCommand(final String input) {
        return !input.equals(YES_COMMAND) && !input.equals(NO_COMMAND);
    }

    public static int readBettingMoney(final Name name) {
        System.out.println(name.value() + "의 배팅 금액은?");

        return Integer.parseInt(scanner.nextLine());
    }
}
