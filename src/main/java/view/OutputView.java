package view;

import domain.card.Card;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    public void printInputPlayerNameMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printSetUpResult(HashMap<String, List<Card>> setUpResult) {
        printSetUpCompleteMessage(setUpResult);
        printUserCards(setUpResult);
    }

    private void printSetUpCompleteMessage(HashMap<String, List<Card>> setUpResult) {
        String playerNames = String.join(", ",
                new ArrayList<>(setUpResult.keySet()).subList(1, setUpResult.size()));

        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", playerNames);
    }

    private void printUserCards(HashMap<String, List<Card>> setUpResult) {
        for (Entry<String, List<Card>> cardsByUser : setUpResult.entrySet()) {
            String name = cardsByUser.getKey();
            String cards = cardsByUser.getValue().stream()
                    .map(Card::getSymbol)
                    .collect(Collectors.joining(", "));

            System.out.println(name + "카드 : " + cards);
        }
    }
}
