package blackjack.view;

import blackjack.util.Console;

public class InputView {

    public String readUserName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Console.readLine();
    }

    public String readMoreCard(String userName) {
        System.out.println(userName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return Console.readLine();
    }

}
