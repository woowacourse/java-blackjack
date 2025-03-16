package object.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
        return List.of(input.split("\\s*,\\s*"));
    }

    public int askBetMoney(String nickname) {
        GameEffect.playSoundEffect("question.wav");
        System.out.printf("%s의 베팅 금액은?%n", nickname);
        try{
            String input = scanner.nextLine();
            GameEffect.playSoundEffect("bet.wav");
            GameEffect.delay(500);
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("베팅 금액은 숫자만 입력할 수 있습니다.");
        }
    }

    public String askDrawOneMore(String nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", nickname);
        return scanner.nextLine();
    }
}
