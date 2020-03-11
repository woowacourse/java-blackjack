package view;

import domain.card.Card;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printEnterPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printFirstCardDealt(Dealer dealer, Players players) {
        List<String> playerNames = players.getNames();
        System.out.println(
            "딜러와 " + String.join(", ", playerNames) + "에게 "
                + Deck.NUMBER_OF_FIRST_DEAL_CARDS + "장의 카드를 나누었습니다."
        );

        System.out.println("딜러: " + dealer.getCards().get(0).getSymbol() + dealer.getCards().get(0)
            .getTypeKorean());
        for (String playerName : playerNames) {
            System.out.println(
                playerName + "카드 : " + formatCardStatus(players.getCardsByName(playerName)));
        }
    }

    private static String formatCardStatus(List<Card> cards) {
        List<String> cardState = new ArrayList<>();
        for (Card card : cards) {
            cardState.add(card.getSymbol() + card.getTypeKorean());
        }
        return String.join(", ", cardState);
    }
}
