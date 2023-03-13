package view;

import domain.card.Card;
import domain.user.Name;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    public void printInputPlayerNameMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printSetUpResult(HashMap<Name, List<Card>> setUpResult) {
        printSetUpCompleteMessage(setUpResult);
        printUserCards(setUpResult);
    }

    public void printAskOneMoreCardMessage(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
    }

    public void printPlayerDrawResult(String name, List<Card> cards) {
        String cardSymbols = cards.stream()
                .map(Card::getSymbol)
                .collect(Collectors.joining(", "));

        System.out.println(name + "카드 : " + cardSymbols);
    }

    public void printDealerDrawResult(int dealerDrawCount) {
        System.out.printf("\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n", dealerDrawCount);
    }

    public void printUserCardWithScore(String name, String cards, int score) {
        System.out.println(name + "카드 : " + cards + " - 결과: " + score);
    }

    private void printSetUpCompleteMessage(HashMap<Name, List<Card>> setUpResult) {
        List<String> playerNames = setUpResult.keySet().stream()
                .map(Name::getName)
                .collect(Collectors.toList());

        String playerNamesMessage = String.join(", ",
                playerNames.subList(1, setUpResult.size()));

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", playerNamesMessage);
    }

    private void printUserCards(HashMap<Name, List<Card>> setUpResult) {
        for (Entry<Name, List<Card>> cardsByUser : setUpResult.entrySet()) {
            Name name = cardsByUser.getKey();
            String cards = cardsByUser.getValue().stream()
                    .map(Card::getSymbol)
                    .collect(Collectors.joining(", "));

            System.out.println(name.getName() + "카드 : " + cards);
        }
        System.out.println();
    }
}
