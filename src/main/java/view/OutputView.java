package view;

import java.util.List;

public class OutputView {
    private static final String NAME_PROMPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INITIAL_CARD_SHARE = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String HIT_OR_STAND_PROMPT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String ADDITIONAL_CARD_FOR_DEALER_DESCRIPTION = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public void printNamePrompt() {
        System.out.println(NAME_PROMPT);
    }

    public void printInitialCardShare(List<String> names) {
        System.out.printf(INITIAL_CARD_SHARE, String.join(", ", names));
    }

    public void printHitOrStandPrompt(String name) {
        System.out.printf(HIT_OR_STAND_PROMPT, name);
    }

    public void printAdditionalCardForDealerDescription() {
        System.out.println(ADDITIONAL_CARD_FOR_DEALER_DESCRIPTION);
    }
}

//게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
//pobi,jason
//
//        딜러와 pobi, jason에게 2장을 나누었습니다.
//딜러카드: 3다이아몬드
//pobi카드: 2하트, 8스페이드
//jason카드: 7클로버, K스페이드
//
//pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
//y
//pobi카드: 2하트, 8스페이드, A클로버
//pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
//n
//jason는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
//n
//jason카드: 7클로버, K스페이드
//
//딜러는 16이하라 한장의 카드를 더 받았습니다.
//
//        딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
//pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
//jason카드: 7클로버, K스페이드 - 결과: 17
//
//        ## 최종 승패
//딜러: 1승 1패
//pobi: 승
//jason: 패