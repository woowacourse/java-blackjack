package view;

import card.Card;
import card.CardNumber;
import card.CardType;
import game.GameResult;
import participant.Dealer;
import participant.Participant;
import participant.Player;
import participant.Players;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String CLOVER = "클로버";
    private static final String SPADE = "스페이드";
    private static final String DIAMOND = "다이아몬드";
    private static final String HEART = "하트";
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String DRAW = "무";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";
    private static final String NICKNAME_SEPARATOR = ", ";

    private static final String ALL_CARDS = "%s: %s";
    private static final String RESULT = " - 결과: %s";

    public void printDistributionCards(Dealer dealer, Players players) {
        printNewLine();
        print("딜러와 ");
        print(String.join(NICKNAME_SEPARATOR, players.getPlayers().stream()
                .map(Participant::getNickname)
                .toList())
        );
        println("에게 2장을 나누었습니다.");

        System.out.printf("딜러카드: %s%n", cardToString(dealer.getLastCard()));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
        printNewLine();
    }

    public void printPlayerCards(Participant player) {
        System.out.printf(ALL_CARDS, player.getNickname(), allCardToString(player.getCards()));
        printNewLine();
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

    private void printPlayerCardsWithScore(Participant player) {
        System.out.printf(ALL_CARDS, player.getNickname(), allCardToString(player.getCards()));
        System.out.printf(RESULT, player.score());
        printNewLine();
    }

    private String allCardToString(List<Card> cards) {
        return String.join(NICKNAME_SEPARATOR, cards.stream()
                .map(this::cardToString)
                .toList());
    }

    public void printResult(Map<GameResult, Integer> dealerResults, Map<Player, GameResult> playerResults) {
        println("## 최종 승패");
        print("딜러:");
        for (GameResult gameResult : GameResult.values()) {
            if (dealerResults.get(gameResult) != 0) {
                System.out.printf(" %d%s", dealerResults.get(gameResult), gameResultToString(gameResult));
            }
        }
        printNewLine();

        for (Player player : playerResults.keySet()) {
            System.out.printf(ALL_CARDS, player.getNickname(), gameResultToString(playerResults.get(player)));
            printNewLine();
        }
    }

    private String gameResultToString(GameResult gameResult) {
        if (gameResult.equals(GameResult.WIN)) {
            return WIN;
        }
        if (gameResult.equals(GameResult.LOSE)) {
            return LOSE;
        }
        return DRAW;
    }

    private String cardToString(Card card) {
        return cardNumberToString(card) + cardTypeToString(card);
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
        return String.valueOf(card.getCardNumber().getValues().get(0));
    }

    private String cardTypeToString(Card card) {
        if (card.getCardType() == CardType.CLOVER) {
            return CLOVER;
        }
        if (card.getCardType() == CardType.DIAMOND) {
            return DIAMOND;
        }
        if (card.getCardType() == CardType.SPADE) {
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
