package view;

import static util.Keyword.NO;
import static util.Keyword.YES;

public class AskView {

    public static void askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
    }

    public static void askWantMoreCard(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 " + YES.getValue() + ", 아니오는 " + NO.getValue() + ")");
    }
}
