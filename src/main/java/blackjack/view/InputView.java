package blackjack.view;

import blackjack.domain.Nickname;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public List<String> readNicknames() {
        System.out.printf("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)%n");

        String nicknames = scanner.nextLine();
        System.out.println();
        return new ArrayList<>(List.of(nicknames.split(",")));

    }

    public boolean readWannaHit(Nickname nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 나머지는 아니오)%n", nickname.getValue());

        String wannaHit = scanner.nextLine().toUpperCase();
        return wannaHit.equals("Y");
    }
}
