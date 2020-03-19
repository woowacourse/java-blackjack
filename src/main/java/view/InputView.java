package view;

import common.PlayerDto;
import common.PlayersDto;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static String inputWantToHit(String playerName) {
        System.out.println(String.format("%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n", playerName));
        return scanner.nextLine();
    }

    public static PlayersDto inputPlayers() {
        List<PlayerDto> playerDtos = new ArrayList<>();
        List<String> playerNames = inputPlayerNames();
        for (String playerName : playerNames) {
            int bettingMoney = inputBettingMoney(playerName);
            PlayerDto playerDto = PlayerDto.of(playerName, bettingMoney);
            playerDtos.add(playerDto);
        }

        return PlayersDto.of(playerDtos);
    }

    private static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return StringUtils.parseWithDelimeter(input, DELIMITER);
    }

    private static int inputBettingMoney(String playerName) {
        System.out.println(String.format("%s의 베팅 금액은?", playerName));
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }
}
