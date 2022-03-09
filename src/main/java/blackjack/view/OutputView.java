package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.GameResponse;

import java.util.List;

public class OutputView {

    public static void announceStartGame(List<String> playerNames) {
        System.out.println(String.join(", ", playerNames) + "에게 2장의 카드를 각각 나누었습니다.");
    }

    public static void announcePresentCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            StringBuilder sb = new StringBuilder();
            sb.append(gameResponse.getName()).append(" 카드: ");
            if (gameResponse.getName().equals("딜러")) {
                for (Card card : gameResponse.getDeck().getCards()) {
                    sb.append(card.getRank().getValue()).append(card.getSuit().getName()).append(", ");
                    break;
                }
                sb.deleteCharAt(sb.length()-2);
                System.out.println(sb);
                continue;
            }
            for (Card card : gameResponse.getDeck().getCards()) {
                sb.append(card.getRank().getValue()).append(card.getSuit().getName()).append(", ");
            }
            sb.deleteCharAt(sb.length()-2);
            System.out.println(sb);
        }
    }
}
