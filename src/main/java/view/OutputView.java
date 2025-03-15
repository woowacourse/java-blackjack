package view;

import card.Card;
import card.CardNumber;
import card.CardShape;
import game.Playable;
import java.util.List;
import java.util.Map;
import participant.Dealer;
import participant.Player;
import participant.Players;
import participant.Profit;

public class OutputView {

    private static final String NICKNAME_SEPARATOR = ", ";
    private static final String CLOVER = "클로버";
    private static final String SPADE = "스페이드";
    private static final String DIAMOND = "다이아몬드";
    private static final String HEART = "하트";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";

    public void printDistributionCards(Dealer dealer, Players players) {
        printNewLine();
        print("딜러와 ");
        print(String.join(NICKNAME_SEPARATOR, players.getPlayers().stream()
                .map(Playable::getNickname)
                .toList())
        );
        println("에게 2장을 나누었습니다.");

        println(String.format("딜러 카드: %s", cardToString(dealer.getLastCard())));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
        printNewLine();
    }

    public void printPlayerCards(Player player) {
        println(String.format("%s: %s", player.getNickname(), allCardToString(player.getCards())));
    }

    public void printDealerReceivedCard() {
        printNewLine();
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printAllCardAndScore(Players players, Dealer dealer) {
        printNewLine();
        printPlayerCardsWithScore(dealer);

        for (Player player : players.getPlayers()) {
            printPlayerCardsWithScore(player);
        }
        printNewLine();
    }

    public void printResult(Map<Playable, Profit> participantGameResults) {
        println("## 최종 수익");

        for (Playable participant : participantGameResults.keySet()) {
            if (participant instanceof Dealer) {
                println(String.format("딜러: %d", participantGameResults.get(participant).getAmount()));
                break;
            }
        }

        for (Playable participant : participantGameResults.keySet()) {
            if (participant instanceof Player) {
                println(String.format("%s: %d", participant.getNickname(),
                        participantGameResults.get(participant).getAmount()));
            }
        }
    }

    private void printPlayerCardsWithScore(Playable participant) {
        print(String.format("%s: %s", participant.getNickname(), allCardToString(participant.getCards())));
        println(String.format(" - 결과: %s", participant.score()));
    }

    private String allCardToString(List<Card> cards) {
        return String.join(NICKNAME_SEPARATOR, cards.stream()
                .map(this::cardToString)
                .toList());
    }

    private String cardToString(Card card) {
        return cardNumberToString(card) + cardShapeToString(card);
    }

    private String cardNumberToString(Card card) {
        if (card.getCardNumber() == CardNumber.ACE) {
            return ACE;
        }
        if (card.getCardNumber() == CardNumber.QUEEN) {
            return QUEEN;
        }
        if (card.getCardNumber() == CardNumber.JACK) {
            return JACK;
        }
        if (card.getCardNumber() == CardNumber.KING) {
            return KING;
        }
        return String.valueOf(card.getCardNumber().getValue());
    }

    private String cardShapeToString(Card card) {
        if (card.getCardShape() == CardShape.CLOVER) {
            return CLOVER;
        }
        if (card.getCardShape() == CardShape.DIAMOND) {
            return DIAMOND;
        }
        if (card.getCardShape() == CardShape.SPADE) {
            return SPADE;
        }
        return HEART;
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void println(String message) {
        System.out.println(message);
    }

    private void printNewLine() {
        System.out.println();
    }
}
