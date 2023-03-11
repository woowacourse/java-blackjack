package view;

import domain.card.Card;
import domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void printAskOneMoreCardMessage(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
    }

    public void printPlayerDrawResult(String name, List<Card> cards) {
        String cardSymbols = cards.stream()
                .map(Card::getSymbol)
                .collect(Collectors.joining(", "));

        System.out.println(name + "카드 : " + cardSymbols);
    }

    private void printSetUpCompleteMessage(HashMap<String, List<Card>> setUpResult) {
        String playerNames = String.join(", ",
                new ArrayList<>(setUpResult.keySet()).subList(1, setUpResult.size()));

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", playerNames);
    }

    private void printUserCards(HashMap<String, List<Card>> setUpResult) {
        for (Entry<String, List<Card>> cardsByUser : setUpResult.entrySet()) {
            String name = cardsByUser.getKey();
            String cards = cardsByUser.getValue().stream()
                    .map(Card::getSymbol)
                    .collect(Collectors.joining(", "));

            System.out.println(name + "카드 : " + cards);
        }
        System.out.println();
    }

    public void printDealerDrawResult(int dealerDrawCount) {
        System.out.printf("\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n", dealerDrawCount);
    }

    public void printUsersCardResult(Map<User, List<Card>> userResult) {
        for (Entry<User, List<Card>> cardsByUser : userResult.entrySet()) {
            String name = cardsByUser.getKey().getName();
            String cards = cardsByUser.getValue().stream()
                    .map(Card::getSymbol)
                    .collect(Collectors.joining(", "));
            // TODO: 도메인에 의존하는 부분 제거하기
            int score = cardsByUser.getKey().getScore().getScore();

            System.out.println(name + "카드 : " + cards + " - 결과: " + score);
        }
        System.out.println();
    }

    public void printFinalResult(Map<Boolean, Integer> dealerResult, Map<String, Boolean> userResult) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패\n", dealerResult.get(true), dealerResult.get(false));
        for (Entry<String, Boolean> userFinalResult : userResult.entrySet()) {
            if (userFinalResult.getValue()) {
                System.out.println(userFinalResult.getKey() + ": 승");
                continue;
            }
            System.out.println(userFinalResult.getKey() + ": 패");
        }
    }
}
