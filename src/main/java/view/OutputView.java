package view;

import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final Map<Number, String> NUMBER_SYMBOL_MAP = new HashMap<>(
            Map.of(ACE, "A", QUEEN, "Q", JACK, "J", KING, "K"));
    private static final String COMMA_DELIMITER = ", ";
    private static final String HIT_CARDS = "딜러와 %s에게 2장을 나누었습니다.%n";
    private static final String PLAYER_CARDS = "%s카드: ";
    private static final String DEALER_CARDS = "딜러카드: ";
    private static final String HIT_DEALER_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private static final String SCORE = " - 결과: %d";
    private static final String RESULT_INTRO = "## 최종 승패";
    private static final String PLAYER_RESULT = "%s: %s";
    private static final String DEALER_RESULT = "딜러: ";

    public void printParticipant(final Players players, final Dealer dealer) {
        printHitNotice(players);
        printDealerDeckWithHidden(dealer);

        for (Player player : players.getPlayers()) {
            printPlayerDeck(player);
        }
        printNewLine();
    }

    public void printPlayerDeck(final Player player) {
        System.out.println(resultMaker(player));
    }

    public String printDealerDeck(final Dealer dealer) {
        System.out.printf(DEALER_CARDS);
        List<String> cardSymbols = new ArrayList<>();
        for (Card card : dealer.getHand().getCards()) {
            cardSymbols.add(toSymbol(card));
        }
        return String.join(COMMA_DELIMITER, cardSymbols);
    }

    public void printDrawDealer(final Dealer dealer) {
        if (dealer.isUnderThreshold()) {
            printNewLine();
            System.out.print(HIT_DEALER_CARD);
        }
    }

    public void printScore(final Players players, final Dealer dealer) {
        printNewLine();
        System.out.print(printDealerDeck(dealer));
        System.out.printf(SCORE, dealer.calculateSum());
        printNewLine();

        for (Player player : players.getPlayers()) {
            System.out.print(resultMaker(player));
            System.out.printf(SCORE, player.calculateSum());
            printNewLine();
        }
    }

    public void printResult(final Map<Player, MatchResult> playerMatchResult) {
        printNewLine();
        System.out.println(RESULT_INTRO);

        printDealerResult(playerMatchResult);

        printNewLine();
        playerMatchResult.forEach((player, matchResult) -> {
            System.out.printf(PLAYER_RESULT, player.getName(), matchResult.getValue());
            printNewLine();
        });
    }

    private void printDealerDeckWithHidden(final Dealer dealer) {
        System.out.println(DEALER_CARDS + toSymbol(dealer.getHand().getCards().getFirst()));
    }

    private void printHitNotice(final Players players) {
        List<String> playersName = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            playersName.add(player.getName());
        }
        printNewLine();
        System.out.printf(HIT_CARDS, String.join(COMMA_DELIMITER, playersName));
    }

    private String resultMaker(final Player player) {
        System.out.printf(PLAYER_CARDS, player.getName());
        List<String> cardSymbols = new ArrayList<>();
        for (Card card : player.getHand().getCards()) {
            cardSymbols.add(toSymbol(card));
        }
        return String.join(COMMA_DELIMITER, cardSymbols);
    }

    private String toSymbol(final Card card) {
        Number number = card.getNumber();
        Shape shape = card.getShape();
        return NUMBER_SYMBOL_MAP.getOrDefault(number, String.valueOf(number.getScore())) + shape.getShape();
    }

    private void printDealerResult(Map<Player, MatchResult> playerMatchResult) {
        StringBuilder stringBuilder = new StringBuilder(DEALER_RESULT);
        System.out.print(appendMatchResult(stringBuilder, MatchResult.calculateDealerResult(playerMatchResult)));
    }

    private StringBuilder appendMatchResult(StringBuilder stringBuilder, Map<MatchResult, Integer> dealerResult) {
        for (Entry<MatchResult, Integer> map : dealerResult.entrySet()) {
            appendMatchResultIfNotZero(stringBuilder, map);
        }
        return stringBuilder;
    }

    private static void appendMatchResultIfNotZero(StringBuilder stringBuilder, Entry<MatchResult, Integer> map) {
        if (map.getValue() == 0) {
            return;
        }
        stringBuilder.append(map.getValue()).append(map.getKey().getValue()).append(" ");
    }

    private void printNewLine() {
        System.out.print(System.lineSeparator());
    }

}
