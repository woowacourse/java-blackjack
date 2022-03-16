package blackjack.view;

import static java.util.stream.Collectors.toList;

import blackjack.dto.PlayerDto;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SCAN_PLAYER_NAMES_INSTRUCTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_NAMES_EMPTY_MESSAGE = "이름에 빈값을 포함할 수 없습니다.";
    private static final int NAME_COUNT_MIN = 1;
    private static final String NAME_COUNT_SUFFIX = "개 이상 입력하세요.";
    private static final String PLAYER_NAMES_DUPLICATE_MESSAGE = "입력값이 중복될 수 없습니다.";
    private static final String SPLIT_UNIT = ",";
    private static final int SPLIT_LIMIT_NUMBER = -1;
    private static final String PLAYER_NAMES_NOT_NULL_MESSAGE = "플레이어 이름은 null일 수 없습니다.";
    private static final String SCAN_BET_MONEY_INSTRUCTION = "의 배팅 금액은?";
    private static final String HIT_OR_STAY_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String BET_MONEY_NOT_NULL_MESSAGE = "배팅금액은 null일 수 없습니다.";
    private static final String BET_MONEY_EMPTY_MESSAGE = "배팅 금액에 빈칸 입력은 허용하지 않는다.";
    private static final String BET_MONEY_FORMAT_MESSAGE = "입력한 값이 숫자의 형태가 아닙니다.";

    public List<String> scanPlayerNames() {
        System.out.println(SCAN_PLAYER_NAMES_INSTRUCTION);
        final List<String> playerNames = getPlayerNames();
        validateNames(playerNames);
        return playerNames;
    }

    private void validateNames(final List<String> playerNames) {
        checkNamesEmpty(playerNames);
        checkNamesCount(playerNames);
        checkNamesDuplicate(playerNames);
    }

    private void checkNamesEmpty(final List<String> playerNames) {
        if (playerNames.stream()
            .anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(PLAYER_NAMES_EMPTY_MESSAGE);
        }
    }

    private void checkNamesCount(final List<String> playerNames) {
        if (playerNames.size() < NAME_COUNT_MIN) {
            throw new IllegalArgumentException(NAME_COUNT_MIN + NAME_COUNT_SUFFIX);
        }
    }

    public void checkNamesDuplicate(List<String> values) {
        if (isDuplicate(values)) {
            throw new IllegalArgumentException(PLAYER_NAMES_DUPLICATE_MESSAGE);
        }
    }

    private boolean isDuplicate(final List<String> names) {
        return names.size() != names.stream()
            .distinct()
            .count();
    }

    private List<String> getPlayerNames() {
        final String inputNames = SCANNER.nextLine();
        Objects.requireNonNull(inputNames, PLAYER_NAMES_NOT_NULL_MESSAGE);
        return Arrays.stream(inputNames
                .split(SPLIT_UNIT, SPLIT_LIMIT_NUMBER))
            .map(String::trim)
            .collect(toList());
    }

    public String scanBetMoney(final String name) {
        System.out.println();
        System.out.println(name + SCAN_BET_MONEY_INSTRUCTION);
        final String betMoney = SCANNER.nextLine();
        validateBetMoney(betMoney);
        return betMoney;
    }

    private void validateBetMoney(final String betMoney) {
        Objects.requireNonNull(betMoney, BET_MONEY_NOT_NULL_MESSAGE);
        checkBetMoneyEmpty(betMoney);
        checkBetMoneyFormat(betMoney);
    }

    private void checkBetMoneyEmpty(final String betMoney) {
        if (betMoney == null || betMoney.trim().isEmpty()) {
            throw new IllegalArgumentException(BET_MONEY_EMPTY_MESSAGE);
        }
    }

    private void checkBetMoneyFormat(final String betMoney) {
        if (!(betMoney.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException(BET_MONEY_FORMAT_MESSAGE);
        }
    }

    public String scanHitOrStay(final PlayerDto playerDto) {
        System.out.printf(HIT_OR_STAY_MESSAGE, playerDto.getName());
        return SCANNER.nextLine();
    }
}
