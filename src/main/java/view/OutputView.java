package view;

import card.Card;
import card.CardNumber;
import card.CardShape;
import game.Playable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import participant.Dealer;
import participant.Player;
import participant.Players;
import participant.Profit;

public class OutputView {

    private static final String NICKNAME_SEPARATOR = ", ";
    private static final Map<CardShape, String> CARD_SHAPES = Map.of(
            CardShape.CLOVER, "클로버",
            CardShape.DIAMOND, "다이아몬드",
            CardShape.SPADE, "스페이드",
            CardShape.HEART, "하트"
    );
    private static final Map<CardNumber, String> CARD_NUMBERS = Map.of(
            CardNumber.ACE, "A",
            CardNumber.JACK, "J",
            CardNumber.QUEEN, "Q",
            CardNumber.KING, "K"
    );

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

        for (Entry<Playable, Profit> entry : participantGameResults.entrySet()) {
            println(String.format("%s: %d", entry.getKey().getNickname(),
                    participantGameResults.get(entry.getKey()).getAmount()));
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
        return CARD_NUMBERS.entrySet().stream()
                .filter(entry -> card.isSameCardNumber(entry.getKey()))
                .map(Entry::getValue)
                .findFirst()
                .orElse(String.valueOf(card.getCardNumberValue()));
    }

    private String cardShapeToString(Card card) {
        return CARD_SHAPES.entrySet().stream()
                .filter(entry -> card.isSameCardShape(entry.getKey()))
                .map(Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 카드 모양입니다."));
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
