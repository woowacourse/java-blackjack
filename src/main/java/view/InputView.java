package view;

import domain.state.HitStand;
import domain.participant.Player;
import dto.domain.PlayerNameAndBettingDto;
import error.ErrorMessage;
import java.util.List;
import java.util.Scanner;
import message.IOMessage;
import util.NameParser;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> getPlayerNames() {
        while (true) {
            System.out.println(IOMessage.ASK_GAME_PARTICIPANT.message());
            final List<String> playerNames = NameParser.makeNameList(scanner.nextLine());
            if (isValidPlayerNames(playerNames)) {
                return playerNames;
            }
        }
    }

    public boolean askHitOrStand(Player player) {
        while (true) {
            System.out.println(player.getName() + IOMessage.ASK_HIT_OR_STAND.message());
            final String input = scanner.nextLine().trim().toLowerCase();

            if(HitStand.validate(input)){
                return HitStand.isHit(input);
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
        while (true) {
            System.out.println(name + IOMessage.ASK_PLAYER_BETTING.message());
            final String input = scanner.nextLine().trim();
            try {
                final int betting = Integer.parseInt(input);
                if (betting < 1) {
                    System.out.println(ErrorMessage.INVALID_BETTING_AMOUNT.message());
                    continue;
                }
                return new PlayerNameAndBettingDto(name, betting);
            } catch (NumberFormatException e) {
                System.out.println(ErrorMessage.INVALID_BETTING_FORMAT.message());
            }
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
