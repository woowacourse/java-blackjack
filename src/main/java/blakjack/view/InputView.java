package blakjack.view;

import blakjack.domain.Chip;
import blakjack.domain.PlayerName;
import blakjack.domain.participant.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_DELIMITER = ",";
    private static final String DUPLICATE_NAME_MESSAGE = "중복된 이름은 사용할 수 없습니다.";
    private static final String BLANK_INPUT_MESSAGE = "값을 입력해주세요.";
    private static final String NOT_NUMBER_MESSAGE = "숫자를 입력하세요.";
    private static final String DO_HIT = "Y";
    private static final String NO_HIT = "N";
    private static final String INVALID_HIT_REQUEST_MESSAGE = "y 또는 n을 입력하세요.";

    private InputView() {
    }

    public static List<PlayerName> inputPlayerNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        return checkDuplication(convertToPlayerName(readLine()));
    }

    private static String readLine() {
        final String input = SCANNER.nextLine();
        if (input.isBlank()) {
            System.out.println(BLANK_INPUT_MESSAGE);
            return readLine();
        }
        return input;
    }

    private static List<PlayerName> convertToPlayerName(final String rawInput) {
        return Arrays.stream(rawInput.split(NAME_DELIMITER))
                .map(String::trim)
                .map(PlayerName::new)
                .collect(Collectors.toList());
    }

    private static List<PlayerName> checkDuplication(final List<PlayerName> names) {
        final var before = names.size();
        final var after = (int) names.stream()
                .distinct()
                .count();
        if (before == after) {
            return names;
        }
        System.out.println(DUPLICATE_NAME_MESSAGE);
        return inputPlayerNames();
    }

    public static List<Chip> inputBettingMoney(final List<PlayerName> playerNames) {
        final List<Chip> chips = new ArrayList<>();
        for (final PlayerName playerName : playerNames) {
            System.out.printf("%s의 배팅 금액은?%n", playerName.getValue());
            chips.add(createChip());
        }
        return chips;
    }

    private static Chip createChip() {
        final var raw = readLine();
        try {
            final var number = Integer.parseInt(raw);
            return new Chip(number);
        } catch (NumberFormatException e) {
            System.out.println(NOT_NUMBER_MESSAGE);
            return createChip();
        }
    }

    public static boolean inputHitRequest(final Participant player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getName());
        final String raw = readLine();
        if (isYorN(raw)) {
            return DO_HIT.equalsIgnoreCase(raw);
        }
        System.out.println(INVALID_HIT_REQUEST_MESSAGE);
        return inputHitRequest(player);
    }

    private static boolean isYorN(final String raw) {
        return DO_HIT.equalsIgnoreCase(raw) || NO_HIT.equalsIgnoreCase(raw);
    }
}
