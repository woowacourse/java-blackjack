package view;

import domain.state.HitStand;
import dto.domain.PlayerNameAndBettingDto;
import java.util.List;
import java.util.Scanner;
import message.IOMessage;
import util.NameParser;

public class InputView {
    private static final int MIN_BETTING_AMOUNT = 10;
    private static final String INVALID_HIT_OR_STAND = "y 또는 n만 입력해주세요.";
    private static final String INVALID_BETTING_FORMAT = "숫자만 입력해주세요.";
    private static final String INVALID_BETTING_AMOUNT = "배팅 금액은 10 이상이며 10원 단위여야 합니다.";
    private static final String EMPTY_PLAYER_NAMES = "플레이어 이름을 한 명 이상 입력해주세요.";
    private static final String INVALID_PLAYER_NAME = "플레이어 이름은 비어 있을 수 없습니다.";
    private static final String DUPLICATED_PLAYER_NAME = "중복된 플레이어 이름은 허용되지 않습니다.";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> getPlayerNames() {
        System.out.println(IOMessage.ASK_GAME_PARTICIPANT.message());
        final List<String> playerNames = NameParser.makeNameList(scanner.nextLine());
        if (isValidPlayerNames(playerNames)) {
            return playerNames;
        }
        return getPlayerNames();
    }

    public HitStand askHitOrStand(final String playerName) {
        while (true) {
            System.out.println(playerName + IOMessage.ASK_HIT_OR_STAND.message());
            final String input = scanner.nextLine().trim().toLowerCase();
            if (HitStand.validate(input)) {
                return HitStand.from(input);
            }
            System.out.println(INVALID_HIT_OR_STAND);
        }
    }

    public List<PlayerNameAndBettingDto> getPlayerBetting(List<String> playerNames){
        return playerNames.stream()
                .map(this::readPlayerBetting)
                .toList();
    }

    private PlayerNameAndBettingDto readPlayerBetting(String name) {
        System.out.println(name + IOMessage.ASK_PLAYER_BETTING.message());
        final String input = scanner.nextLine().trim();
        final Integer betting = parseBetting(input);
        if (betting == null) {
            System.out.println(INVALID_BETTING_FORMAT);
            return readPlayerBetting(name);
        }
        if (betting < MIN_BETTING_AMOUNT || betting % MIN_BETTING_AMOUNT != 0) {
            System.out.println(INVALID_BETTING_AMOUNT);
            return readPlayerBetting(name);
        }
        return new PlayerNameAndBettingDto(name, betting);
    }

    private Integer parseBetting(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isValidPlayerNames(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            System.out.println(EMPTY_PLAYER_NAMES);
            return false;
        }

        if (playerNames.stream().anyMatch(String::isBlank)) {
            System.out.println(INVALID_PLAYER_NAME);
            return false;
        }

        final long distinctCount = playerNames.stream().distinct().count();
        if (distinctCount != playerNames.size()) {
            System.out.println(DUPLICATED_PLAYER_NAME);
            return false;
        }

        return true;
    }
}
