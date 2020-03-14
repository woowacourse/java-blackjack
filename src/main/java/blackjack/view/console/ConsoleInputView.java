package blackjack.view.console;

import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.domain.rule.PlayerAnswer;
import blackjack.view.InputView;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String ASK_MORE_CARD_FORMAT = "%s는 카드를 더 받으시겠습니까?";

    @Override
    public NamesRequestDto inputPlayerNames() {
        System.out.println("유저 이름을 입력하세요. 이름은 콤마(,)로 구분합니다.");
        String namesInput = scanner.nextLine();
        List<String> names = StringParser.splitWithComma(namesInput);
        return new NamesRequestDto(names);
    }

    @Override
    public PlayerAnswer inputPlayerAnswer(String name) {
        System.out.println();
        System.out.println(String.format(ASK_MORE_CARD_FORMAT, name));
        String answer = scanner.nextLine();
        return PlayerAnswer.of(answer);
    }
}
