package view;

import domain.state.HitStand;
import dto.domain.PlayerNameAndBettingDto;
import error.ErrorMessage;
import java.util.List;
import java.util.Scanner;
import message.IOMessage;
import util.NameParser;

public class InputView {
    private static final int MIN_BETTING_AMOUNT = 10;

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
            System.out.println(ErrorMessage.INVALID_HIT_OR_STAND.message());
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
            System.out.println(ErrorMessage.INVALID_BETTING_FORMAT.message());
            return readPlayerBetting(name);
        }
        if (betting < MIN_BETTING_AMOUNT || betting % MIN_BETTING_AMOUNT != 0) {
            System.out.println(ErrorMessage.INVALID_BETTING_AMOUNT.message());
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
            System.out.println(ErrorMessage.EMPTY_PLAYER_NAMES.message());
            return false;
        }

        if (playerNames.stream().anyMatch(String::isBlank)) {
            System.out.println(ErrorMessage.INVALID_PLAYER_NAME.message());
            return false;
        }

        final long distinctCount = playerNames.stream().distinct().count();
        if (distinctCount != playerNames.size()) {
            System.out.println(ErrorMessage.DUPLICATED_PLAYER_NAME.message());
            return false;
        }

        return true;
    }
}
