package blackjack.view;

public class OutputView {

    public void outputFirstCardDistributionResult() {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine(String.format("딜러와 %s에게 2장을 나누었습니다."));
    }

    public String outputPlayerCardStatus() {
        return String.format("%s카드: %s");
    }

    public void outputDealerGetCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void outputDealerCardFinish() {
        System.out.println("딜러는 17이상이라 더이상 카드를 받을 수 없습니다.");
    }

    public void outputFinalResult() {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.appendLine("## 최종 승패");
        customStringBuilder.appendLine(String.format("딜러: %d승 %d패"));
        customStringBuilder.appendLine(String.format("%s: %s"));
    }
}
