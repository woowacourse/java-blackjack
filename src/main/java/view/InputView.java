package view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.participant.Player;
import model.PlayerChoice;
import model.participant.Players;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Map<Player, Integer> inputBettingPrice(Players players) {
        Map<Player, Integer> bettingResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + "의 배팅 금액은?");
            int bettingPrice = validateInteger(SCANNER.nextLine());
            bettingResult.put(player, bettingPrice);
        }
        return bettingResult;
    }

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        String[] names = SCANNER.nextLine().split(",", -1);
        validateDelimiter(names);
        return Arrays.stream(names).toList();
    }

    public static PlayerChoice readChoice(Player player) {
        OutputView.printHitOrStand(player);
        String choice = SCANNER.nextLine();
        validateChoice(choice);
        return PlayerChoice.findPlayerChoice(choice);
    }

    public static int inputAdditionalBet() {
        System.out.println("추가 베팅 금액을 입력해주세요");
        String additionalBet = SCANNER.nextLine();
        return validateInteger(additionalBet);
    }

    private static int validateInteger(String inputPrice) {
        if (inputPrice.chars().allMatch(Character::isDigit)) {
            return Integer.parseInt(inputPrice);
        }
        throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요 입력값:" + inputPrice);
    }

    private static void validateDelimiter(String[] names) {
        String regex = "^[a-zA-Zㄱ-ㅎ가-힣]+$";

        for (String name : names) {
            validateInputName(name, regex);
        }
    }

    private static void validateInputName(String name, String regex) {
        if (!Pattern.matches(regex, name)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 이름 형식입니다. 입력값 : " + name);
        }
    }

    private static void validateChoice(String choice) {
        boolean check = Arrays.stream(PlayerChoice.values())
                .anyMatch(value-> value.getChoiceName().equals(choice));
        if (!check) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 입력값 : " + choice);
        }
    }
}
