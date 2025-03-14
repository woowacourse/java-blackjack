package blackjack.view;

import blackjack.domain.game.Participant;
import blackjack.domain.game.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = scanner.nextLine();

        return parseNames(rawNames);
    }

    public static Confirmation askToGetMoreCard(Participant participant) {
        if (participant.canDecideToTakeMoreCard()) {
            Player player = (Player) participant;
            System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            return Confirmation.find(scanner.nextLine());
        }
        throw new IllegalArgumentException("카드를 더 받을지의 여부를 선택할 수 없는 참가자입니다.");
    }

    private static List<String> parseNames(String rawNames) {
        if (rawNames == null || rawNames.isBlank()) {
            throw new IllegalArgumentException();
        }

        return Arrays.stream(rawNames.split(",")).toList();
    }

}
