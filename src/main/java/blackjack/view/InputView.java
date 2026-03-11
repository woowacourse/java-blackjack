package blackjack.view;

import blackjack.utils.Console;
import blackjack.utils.Parser;

public class InputView {

    public static String readNicknames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Console.readLine();
    }
    
    public static int readBetAmount(final String nickname) {
        System.out.printf("%s의 배팅 금액은?\n", nickname);
        return Parser.parseInteger(Console.readLine());
    }

    public static String readAnswer(String nickname) {
        System.out.println(nickname + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return Console.readLine();
    }

}
