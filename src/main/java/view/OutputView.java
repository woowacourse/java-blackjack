package view;

import static domain.MatchResult.DRAW;
import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private final Map<Number, String> NUMBER_SYMBOL_MAP = new HashMap<>(
            Map.of(ACE, "A", QUEEN, "Q", JACK, "J", KING, "K"));
    private final String COMMA_DELIMITER = ", ";
    private final String HIT_CARDS = "딜러와 %s에게 2장을 나누었습니다.%n";
    private final String PLAYER_CARDS = "%s카드: ";
    private final String DEALER_CARDS = "딜러카드: ";
    private final String HIT_DEALER_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private final String SCORE = " - 결과: %d";
    private final String RESULT_INTRO = "## 최종 승패";
    private final String DEALER_RESULT = "딜러: ";
    private final String PLAYER_RESULT = "%s: %s";
    private final int INIT_COUNT = 0;
    private final int ONE_MORE_COUNT = 1;

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
        System.out.printf(SCORE, dealer.sum());
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
        System.out.print(DEALER_RESULT);

        Map<MatchResult, Integer> dealerMatchResult = calculateDealerResult(playerMatchResult);
        for (MatchResult matchResult : dealerMatchResult.keySet()) {
            if (dealerMatchResult.get(matchResult) == INIT_COUNT) {
                continue;
            }
            System.out.printf(dealerMatchResult.get(matchResult) + matchResult.getValue() + " ");
        }

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

    private Map<MatchResult, Integer> calculateDealerResult(final Map<Player, MatchResult> playerMatchResult) {
        Map<MatchResult, Integer> dealerMatchResult = initDealerMatchResult();

        for (MatchResult matchResult : playerMatchResult.values()) {
            dealerMatchResult.merge(matchResult, ONE_MORE_COUNT, Integer::sum);
        }

        swap(dealerMatchResult);
        return dealerMatchResult;
    }

    private void swap(final Map<MatchResult, Integer> dealerMatchResult) {
        int winningCount = dealerMatchResult.get(WIN);
        int losingCount = dealerMatchResult.get(LOSE);
        dealerMatchResult.put(WIN, losingCount);
        dealerMatchResult.put(LOSE, winningCount);
    }

    private Map<MatchResult, Integer> initDealerMatchResult() {
        Map<MatchResult, Integer> dealerMatchResult = new LinkedHashMap<>();
        dealerMatchResult.put(WIN, INIT_COUNT);
        dealerMatchResult.put(DRAW, INIT_COUNT);
        dealerMatchResult.put(LOSE, INIT_COUNT);
        return dealerMatchResult;
    }

    private void printNewLine() {
        System.out.print(System.lineSeparator());
    }

}
