package blackjack.view;

import blackjack.model.Card;
import blackjack.model.TotalResult;
import java.util.List;

public class OutputView {

    public void printPlayerNamesInputPrompt() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printCardDistributionCompleted(List<String> names) {
        String joinedName = String.join(", ", names);
        System.out.println("딜러와 " + joinedName + "에게 2장을 나누었습니다.");
    }

    public void printParticipantCards(String name, List<Card> openedCards) {
        List<String> cardNames = openedCards.stream()
                .map(Card::toString)
                .toList();

        String joinedCardNames = String.join(", ", cardNames);
        System.out.println(name + "카드: " + joinedCardNames);
    }

    public void printParticipantCardsWithScore(String name, List<Card> cards, int score) {
        List<String> cardNames = cards.stream()
                .map(Card::toString)
                .toList();

        String joinedCardNames = String.join(", ", cardNames);
        System.out.println(name + "카드: " + joinedCardNames + " - 결과: " + score);
    }

    public void printMoreCardInputPrompt(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printDealerPicksCard() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void printDealerDoesNotPickCard() {
        System.out.println("딜러는 16초과라 더 이상 카드를 받지 않습니다.");
    }

    public void printResult(TotalResult totalResult) {
        System.out.println("## 최종 승패");
        printDealerResult(totalResult.getDealerResult());
        printAllPlayerResult(totalResult);
    }

    private void printDealerResult(String dealerResult) {
        System.out.println("딜러: " + dealerResult);
    }

    private void printAllPlayerResult(TotalResult totalResult) {
        List<String> playerResults = totalResult.playerResults();
        System.out.println(String.join("\n", playerResults));
    }
}
