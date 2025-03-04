package blackjack.view;

public class OutputView {
    public void displayDistributedCardStatus() {
//        딜러와 pobi, jason에게 2장을 나누었습니다.
//        딜러카드: 3다이아몬드
//        pobi카드: 2하트, 8스페이드
//        jason카드: 7클로버, K스페이드
    }

    public void displayUpdatedPlayerCardStatus() {
//        pobi카드: 2하트, 8스페이드, A클로버
    }

    public void displayExtraDealerCardStatus() {
//        딜러는 16이하라 한장의 카드를 더 받았습니다.
    }

    public void displayFinalCardStatus() {
//        딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
//        pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
//        jason카드: 7클로버, K스페이드 - 결과: 17
    }

    public void displayFinalResult() {
//     ## 최종 승패
//        딜러: 1승 1패
//        pobi: 승
//        jason: 패
    }
}
