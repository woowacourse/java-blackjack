package view;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Player;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Map<Integer, String> DEALER_RECEIVED_CARD_COUNT = Map.of(
            1, "한장",
            2, "두장",
            3, "세장",
            4, "네장",
            5, "다섯장"
    );
    private static final DecimalFormat BETTING_AMONUT_RETURN = new DecimalFormat("#.#");

    public void printDealerAndPlayersCards(final Dealer dealer, final List<Player> players) {
        final StringBuilder sb = new StringBuilder();
        final String playerMessage = createPlayerMessage(players);
        final String header = String.format("딜러와 %s에게 2장을 나누었습니다.", playerMessage);
        final Card dealerCard = dealer.getCards().getFirst();
        final String dealerMassage = createCardsMessage("딜러", dealerCard);
        createPlayerMessage(players, sb, header, dealerMassage);
        System.out.println(sb);
    }

    private static String createPlayerMessage(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private void createPlayerMessage(final List<Player> players, final StringBuilder sb, final String header,
                                     final String dealerMassage) {
        final String playerMessage = players.stream()
                .map(player -> createCardsMessage(player.getName(), player.getCards().toArray(new Card[0])))
                .collect(Collectors.joining(LINE_SEPARATOR));
        sb.append(prependLineSeparator(header))
                .append(prependLineSeparator(dealerMassage))
                .append(prependLineSeparator(playerMessage))
                .append(LINE_SEPARATOR);
    }

    public void printPlayerCards(final String playerName, final List<Card> cards) {
        final String playerCardsMessage = createCardsMessage(playerName, cards.toArray(new Card[0]));
        System.out.println(playerCardsMessage);
    }

    public void printDealerReceivedCardCount(final int count) {
        System.out.println(prependLineSeparator(
                String.format("딜러가 16이하라 %s의 카드를 더 받았습니다.", DEALER_RECEIVED_CARD_COUNT.get(count))));
    }

    public void printGamerCardsAndScore(final Dealer dealer, final List<Player> players) {
        final String messageFormat = "%s - 결과: %d";
        final String dealerMessage = String.format(messageFormat,
                createCardsMessage("딜러 ", dealer.getCards().toArray(new Card[0])),
                dealer.calculateScore());
        final String playerMessage = createPlayerMessage(players, messageFormat);
        System.out.println(prependLineSeparator(dealerMessage) + prependLineSeparator(playerMessage));
    }

    private String createPlayerMessage(final List<Player> players, final String messageFormat) {
        return players.stream()
                .map(player -> String.format(messageFormat,
                        createCardsMessage(player.getName(), player.getCards().toArray(new Card[0])),
                        player.calculateScore()))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String createCardsMessage(final String name, final Card... cards) {
        final String cardsMessage = Arrays.stream(cards)
                .map(card -> String.format("%s%s", card.getScore().getSymbol(), card.getType().getName()))
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", name, cardsMessage);
    }

    public void printBettingAmountOfReturn(final double dealerBettingAmountOfReturn,
                                           final Map<String, Double> playerBettingAmountOfReturn) {
        final String dealerMessage = String.format("딜러: %s", BETTING_AMONUT_RETURN.format(dealerBettingAmountOfReturn));
        final String playerMessage = createPlayerMessage(playerBettingAmountOfReturn);
        final StringBuilder sb = new StringBuilder();
        sb.append(prependLineSeparator("## 최종 수익"))
                .append(prependLineSeparator(dealerMessage))
                .append(prependLineSeparator(playerMessage));
        System.out.println(sb);
    }

    private static String createPlayerMessage(final Map<String, Double> playerBettingAmountOfReturn) {
        return playerBettingAmountOfReturn.entrySet()
                .stream()
                .map(entry -> String.format("%s: %s", entry.getKey(),
                        BETTING_AMONUT_RETURN.format(entry.getValue())))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String prependLineSeparator(final String message) {
        return LINE_SEPARATOR + message;
    }
}
