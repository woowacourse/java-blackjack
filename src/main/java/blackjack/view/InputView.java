package blackjack.view;

import blackjack.domain.value.BettingAmount;
import blackjack.domain.value.Nickname;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public List<Nickname> readNicknames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String nicknames = scanner.nextLine();
        System.out.println();
        List<String> rawNicknames = List.of(nicknames.split(","));
        return rawNicknames.stream().map(Nickname::new).toList();
    }

    public BettingAmount readBettingAmount(Nickname nickname) {
        String content = String.format("%s의 배팅 금액은?", nickname.getValue());
        System.out.println(content);
        String input = scanner.nextLine();
        validateNonNumericInput(input);
        return new BettingAmount(Integer.parseInt(input));
    }

    public boolean readWannaHit(Nickname nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 나머지는 아니오)%n", nickname.getValue());
        String wannaHit = scanner.nextLine().toUpperCase();
        return wannaHit.equals("Y");
    }

    private void validateNonNumericInput(String input) {
        try {
            int parsed = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("올바르지 않은 형식의 입력입니다.");
        }
    }
}
