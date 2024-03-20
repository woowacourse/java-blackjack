package view;

import card.Card;
import card.Hand;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import participant.dealer.Dealer;
import participant.player.Name;
import participant.player.Player;
import participant.player.Players;

public class OutputView {

    private static final String CARD_SYMBOL = "카드: ";
    private static final String DEALER_NAME = "딜러";
    private static final int INIT_CARD_COUNT = 2;
    private static final int MIN_DEALER_SCORE = 16;
    private static final String NAME_FORMAT_SYMBOL = ", ";

    public void printInitCardStatus(Players players, Card card) {

        String playersNames = changeNameFormat(players.getPlayerNames());

        System.out.println("\n" + DEALER_NAME + "와 " + playersNames + "에게 " + INIT_CARD_COUNT + "장을 나누었습니다.");
        System.out.println(DEALER_NAME + ": " + card.getCardFeature());

        for (Player player : players.getPlayers()) {
            System.out.println(
                    player.getName().getValue() + CARD_SYMBOL + formatCardsStatus(player.getCards()));
        }

        System.out.println();
    }

    public void printPlayerCardStatus(Name playerName, Hand playerHand) {
        System.out.println(makeCardsStatus(playerName, playerHand));
    }

    public void printExtraCardInfo(Hand dealerHand) {
        System.out.println();
        for (int i = 2; i <= dealerHand.countCard(); i++) {
            System.out.println(DEALER_NAME + "가 " + MIN_DEALER_SCORE + "이하라 한장의 카드를 더 받았습니다.\n");
        }
    }

    public void printBlackJackResult(Map<Name, Integer> playerResult) {
        System.out.println("\n## 최종 수익");
        printDealerResult(playerResult.values());
        for (Name name : playerResult.keySet()) {
            System.out.println(name.getValue() + ": " + playerResult.get(name));
        }
    }

    private void printDealerResult(Collection<Integer> playerEarnMoney) {
        System.out.println(DEALER_NAME + ": " + -calculateDealerMoney(playerEarnMoney));
    }

    private int calculateDealerMoney(Collection<Integer> playerEarnMoney) {
        return playerEarnMoney.stream()
                .mapToInt(money -> money)
                .sum();
    }

    public String formatCardsStatus(Hand hand) {
        return String.join(",", hand.getCardsFeatures());
    }

    private String makeCardsStatus(Name name, Hand hand) {
        return name.getValue() + CARD_SYMBOL + hand.getCardsFeatures();
    }

    private String changeNameFormat(List<Name> names) {
        return names.stream()
                .map(Name::getValue)
                .collect(Collectors.joining(NAME_FORMAT_SYMBOL));
    }

    public void printDealerHand(Dealer dealer) {
        System.out.println(DEALER_NAME + "카드:" + dealer.getCards().getCardsFeatures() + " - 결과: "
                + dealer.getCards().countMaxScore());
    }

    public void printPlayerHand(Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(makeCardsStatus(player.getName(), player.getCards()) + " - 결과: "
                    + player.getCardScore());
        }
    }
}
