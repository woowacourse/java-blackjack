package view;

import domain.state.HitStand;
import domain.participant.Player;
import dto.domain.PlayerNameAndBettingDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import message.IOMessage;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String getPlayerNames() {
        System.out.println(IOMessage.ASK_GAME_PARTICIPANT.message());
        return scanner.nextLine();
    }

    public boolean askHitOrStand(Player player) {
        while (true) {
            System.out.println(player.getName() + IOMessage.ASK_HIT_OR_STAND.message());
            final String input = scanner.nextLine().trim().toLowerCase();

            if(HitStand.validate(input)){
                return HitStand.isHit(input);
            }

            System.out.println(IOMessage.INVALID_HIT_OR_STAND_DECISION.message());
        }
    }

    public List<PlayerNameAndBettingDto> getPlayerBetting(List<String> playerNames){
        return playerNames.stream()
                .map(this::readPlayerBetting)
                .toList();
    }

    private PlayerNameAndBettingDto readPlayerBetting(String name) {
        while (true) {
            System.out.println(name + "의 배팅 금액은?");
            final String input = scanner.nextLine().trim();
            try {
                return new PlayerNameAndBettingDto(name, Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
}
