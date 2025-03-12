package view;

import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;
import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;

import domain.blackJack.MatchResult;
import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final Map<Number, String> NUMBER_SYMBOL_MAP = new HashMap<>(
            Map.of(ACE, "A", QUEEN, "Q", JACK, "J", KING, "K"));
    private static final String COMMA_DELIMITER = ", ";

    private final String HIT_CARDS = "딜러와 %s에게 2장을 나누었습니다.%n";
    private final String PLAYER_CARDS = "%s카드: ";
    private final String DEALER_CARDS = "딜러카드: ";
    private final String HIT_DEALER_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private final String SCORE = " - 결과: %d";
    private final String RESULT_INTRO = "## 최종 수익";
    private final String DEALER_RESULT = "딜러: %d";
    private final String PLAYER_RESULT = "%s: %d";

    private final String NEW_LINE = System.lineSeparator();

    public void printParticipant(final Players players, final Dealer dealer) {
        printHitNotice(players);
        printDealerDeckWithHidden(dealer);

        for (Player player : players.getPlayers()) {
            printPlayerDeck(player);
        }
        System.out.print(NEW_LINE);
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
            System.out.print(NEW_LINE);
            System.out.print(HIT_DEALER_CARD);
        }
    }

    public void printScore(final Players players, final Dealer dealer) {
        System.out.print(NEW_LINE);
        System.out.print(printDealerDeck(dealer));
        System.out.printf(SCORE + NEW_LINE, dealer.sum());

        for (Player player : players.getPlayers()) {
            System.out.print(resultMaker(player));
            System.out.printf(SCORE + NEW_LINE, player.sum());
        }
    }

    private void printDealerDeckWithHidden(final Dealer dealer) {
        System.out.println(DEALER_CARDS + toSymbol(dealer.getHandExceptHidden()));
    }

    private void printHitNotice(final Players players) {
        List<String> playersName = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            playersName.add(player.getName());
        }
        System.out.print(NEW_LINE);
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

    private static String toSymbol(final Card card) {
        Number number = card.getNumber();
        Shape shape = card.getShape();
        return NUMBER_SYMBOL_MAP.getOrDefault(number, String.valueOf(number.getScore())) + shape.getShape();
    }

    public void printProfit(Map<Player, Integer> playerProfit, int dealerProfit) {
        System.out.println(NEW_LINE + RESULT_INTRO);
        System.out.printf(DEALER_RESULT + NEW_LINE, dealerProfit);
        playerProfit.forEach(((player, money) ->
            System.out.printf(PLAYER_RESULT + NEW_LINE, player.getName(), money)
        ));
    }
}
