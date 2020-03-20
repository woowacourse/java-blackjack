package blackjack.view.console;

import blackjack.controller.dto.request.BettingDto;
import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.controller.dto.request.PlayerAnswer;
import blackjack.view.InputView;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String COMMA = ",";
    private static final String ASK_MORE_CARD_FORMAT = "%s는 카드를 더 받으시겠습니까?";
    private static final String ASK_BETTING_MONEY_FORMAT = "%s는 얼마를 배팅하겠습니까?";

    @Override
    public NamesRequestDto askPlayerNames() {
        System.out.println("유저 이름을 입력하세요. 이름은 콤마(,)로 구분합니다.");
        String namesInput = scanner.nextLine();
        List<String> names = Arrays.asList(namesInput.split(COMMA));
        return new NamesRequestDto(names);
    }

    @Override
    public BettingDto askBettingMoney(List<String> names) {
        Map<String, String> bettingTable = new LinkedHashMap<>();
        for (String name : names) {
            System.out.println(String.format(ASK_BETTING_MONEY_FORMAT, name));
            String money = scanner.nextLine();
            bettingTable.put(name, money);
        }
        return new BettingDto(bettingTable);
    }

    @Override
    public PlayerAnswer askPlayerAnswer(String name) {
        System.out.println();
        System.out.println(String.format(ASK_MORE_CARD_FORMAT, name));
        String answer = scanner.nextLine();
        return PlayerAnswer.of(answer);
    }
}
