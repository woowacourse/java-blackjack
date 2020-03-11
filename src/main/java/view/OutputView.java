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
        System.out.printf("딜러와 %s 에게 %d장의 카드를 나누어주었습니다.\n",
            String.join(", ", playerNames),
            Deck.NUMBER_OF_FIRST_DEAL_CARDS
        );
        printAnyDealerCard(dealer);
        printPlayersCards(players, playerNames);
    }

    private static void printPlayersCards(Players players, List<String> playerNames) {
        for (String playerName : playerNames) {
            System.out.println(
                playerName + "카드 : " + formatCardStatus(players.getCardsByName(playerName)));
        }
    }

    private static void printAnyDealerCard(Dealer dealer) {
        Card anyDealerCard = dealer.getAnyCard();
        System.out.println("딜러: " + anyDealerCard.getSymbolName() + anyDealerCard.getTypeKorean());
    }

    private static String formatCardStatus(List<Card> cards) {
        List<String> cardState = new ArrayList<>();
        for (Card card : cards) {
            cardState.add(card.getSymbolName() + card.getTypeKorean());
        }
        return String.join(", ", cardState);
    }

    public static void printAskWantMoreCard(String name) {
        System.out.println(name + "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }
}
