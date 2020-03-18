package second.view;

import second.domain.answer.Choice;
import second.domain.player.Gamer;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";
    private static final String NO_SPACE = "";
    private static final String SPACE = " ";

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return Arrays.stream(SCANNER.nextLine().split(NAME_DELIMITER))
                .map(name -> name.replace(SPACE, NO_SPACE))
                .collect(Collectors.toList());
    }

    public static Choice choose(final Gamer player) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
        try {
            final String choice = SCANNER.nextLine();
            return Choice.of(choice);
        } catch (NoSuchElementException | IllegalStateException e) {
            throw new RuntimeException("입력 값을 다시 확인해주세요");
        }
    }

    private InputView() { }
}
