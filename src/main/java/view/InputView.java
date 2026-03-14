package view;

import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import util.Console;

public class InputView {
    private static final int NAME_MIN_LENGTH = 1;
    private static final int NAME_MAX_LENGTH = 10;
    private static final String BLANK_NAME_NOT_ALLOWED = "[ERROR] 빈 값을 입력할 수 없습니다.";
    private static final String NAME_OUT_OF_RANGE = String.format("[ERROR] 이름은 %d ~ %d자 내여야 합니다.", NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    private static final String INVALID_NUMBER_FORMAT = "[ERROR] 배팅 금액은 숫자만 입력할 수 있습니다.";
    private static final String INVALID_HIT_STAND_RESPONSE = "[ERROR] 입력은 y 또는 n으로만 입력해야 합니다.";
    private static final String DUPLICATE_NAME_NOT_ALLOWED = "[ERROR] 닉네임은 중복될 수 없습니다.";

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = Console.readLine();
        List<String> names = Arrays.stream(input.split(","))
                .map(String::strip)
                .toList();

        names.forEach(this::validate);
        validateIsUnique(names);
        return names;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_NOT_ALLOWED);
        }
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_OUT_OF_RANGE);
        }
    }

    private void validateIsUnique(List<String> names) {
        long uniqueNames = names.stream()
                .distinct()
                .count();

        if (uniqueNames != names.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_NOT_ALLOWED);
        }
    }

    public List<String> readBetMoney(List<String> names) {
        printEmptyLine();
        return names.stream()
                .map(this::readBetMoney)
                .toList();
    }

    private String readBetMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");
        String input = Console.readLine();

        validateIsNumber(input);
        return input;
    }

    private void validateIsNumber(String input) {
        for (char c : input.toCharArray()) {
            validateIsDigit(c);
        }
    }

    private static void validateIsDigit(char c) {
        if (!Character.isDigit(c)) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT);
        }
    }

    private void printEmptyLine() {
        System.out.println();
    }

    public boolean readHitStand(Player player) {
        System.out.println(player.getName().getValue() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = Console.readLine();
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_HIT_STAND_RESPONSE);
    }

}
