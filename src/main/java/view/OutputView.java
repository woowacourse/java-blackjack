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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private final Map<Number, String> numberSymbolMap = new HashMap<>(
            Map.of(ACE, "A", QUEEN, "Q", JACK, "J", KING, "K"));
    private final String commaDelimiter = ", ";
    private final String hitCards = "딜러와 %s에게 2장을 나누었습니다.%n";
    private final String playerCards = "%s카드: ";
    private final String dealerCards = "딜러카드: ";
    private final String hitDealerCard = "딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private final String score = " - 결과: %d";
    private final String resultIntro = "## 최종 승패";
    private final String playerResult = "%s: %s";
    private final String dealerResult = "딜러: ";

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
        System.out.printf(dealerCards);
        List<String> cardSymbols = new ArrayList<>();
        for (Card card : dealer.getHand().getCards()) {
            cardSymbols.add(toSymbol(card));
        }
        return String.join(commaDelimiter, cardSymbols);
    }

    public void printDrawDealer(final Dealer dealer) {
        if (dealer.isUnderThreshold()) {
            printNewLine();
            System.out.print(hitDealerCard);
        }
    }

    public void printScore(final Players players, final Dealer dealer) {
        printNewLine();
        System.out.print(printDealerDeck(dealer));
        System.out.printf(score, dealer.sum());
        printNewLine();

        for (Player player : players.getPlayers()) {
            System.out.print(resultMaker(player));
            System.out.printf(score, player.calculateSum());
            printNewLine();
        }
    }

    public void printResult(final Map<Player, MatchResult> playerMatchResult) {
        printNewLine();
        System.out.println(resultIntro);

        printDealerResult(playerMatchResult);

        printNewLine();
        playerMatchResult.forEach((player, matchResult) -> {
            System.out.printf(playerResult, player.getName(), matchResult.getValue());
            printNewLine();
        });
    }

    private void printDealerDeckWithHidden(final Dealer dealer) {
        System.out.println(dealerCards + toSymbol(dealer.getHand().getCards().getFirst()));
    }

    private void printHitNotice(final Players players) {
        List<String> playersName = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            playersName.add(player.getName());
        }
        printNewLine();
        System.out.printf(hitCards, String.join(commaDelimiter, playersName));
    }

    private String resultMaker(final Player player) {
        System.out.printf(playerCards, player.getName());
        List<String> cardSymbols = new ArrayList<>();
        for (Card card : player.getHand().getCards()) {
            cardSymbols.add(toSymbol(card));
        }
        return String.join(commaDelimiter, cardSymbols);
    }

    private String toSymbol(final Card card) {
        Number number = card.getNumber();
        Shape shape = card.getShape();
        return numberSymbolMap.getOrDefault(number, String.valueOf(number.getScore())) + shape.getShape();
    }

    private void printDealerResult(Map<Player, MatchResult> playerMatchResult) {
        StringBuilder stringBuilder = new StringBuilder(dealerResult);
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
