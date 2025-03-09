package blackjack.view;

import blackjack.domain.user.Nickname;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public List<Nickname> readNicknames() {
        System.out.printf("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)%n");
        String nicknames = scanner.nextLine();
        System.out.println();
        List<String> rawNicknames = List.of(nicknames.split(","));
        return rawNicknames.stream().map(Nickname::new).toList();
    }

    public boolean readWannaHit(String nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 나머지는 아니오)%n", nickname);
        String wannaHit = scanner.nextLine().toUpperCase();
        return wannaHit.equals("Y");
    }
}
