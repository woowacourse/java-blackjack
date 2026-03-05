package blackjack.view;

import blackjack.util.Console;

public class InputView {

    public String readUserName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Console.readLine();
    }
}
