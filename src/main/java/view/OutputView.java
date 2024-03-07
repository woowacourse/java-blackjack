package view;

import card.Card;

import java.util.List;

public class OutputView {

    public void printNamesRequest() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printInitializeBlackJack(List<String> names) {
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public void printDealerFirstCard(Card card) {
        System.out.println("딜러: " + convertCard(card));
    }

    public void printPlayerCards(String name, List<Card> cards) {
        List<String> convertedCards = cards.stream()
                .map(this::convertCard)
                .toList();
        System.out.println(name + "카드: " + String.join(", ", convertedCards));
    }

    public void printDrawMoreCardRequest(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printDealerDrawCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerCardsWithResult(List<Card> cards, int score) {
        List<String> convertedCards = cards.stream()
                .map(this::convertCard)
                .toList();
        System.out.println("딜러 카드: " + String.join(", ", convertedCards) + " - 결과: " + score + "점");
    }

    public void printPlayerCardsWithResult(String name, List<Card> cards, int score) {
        List<String> convertedCards = cards.stream()
                .map(this::convertCard)
                .toList();
        System.out.println(name + " 카드: " + String.join(", ", convertedCards) + " - 결과: " + score + "점");
    }

    public void printResultStart() {
        System.out.println("## 최종 승패");
    }

    public void printDealerResult(int winCount, int loseCount) {
        System.out.println("딜러: " + winCount + "승 " + loseCount + "패");
    }

    public void printPlayerResult(String name, boolean isWin) {
        if (isWin) {
            System.out.println(name + ": 승");
            return;
        }
        System.out.println(name + ": 패");
    }

    public void printNewLine() {
        System.out.println();
    }

    public String convertCard(Card card) {
        String convertedNumber = CardNumberDisplay.fromNumber(card.getNumber());
        String convertedShape = CardShapeDisplay.fromShape(card.getShape());
        return convertedNumber + convertedShape;
    }
}
