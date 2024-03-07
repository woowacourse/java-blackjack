package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;

import java.util.List;

public class OutputView {
    private static final String DEALING_RESULT_INTRO = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_DEALING_FORM = "딜러: %s";
    private static final String PLAYER_CARDS_FORM = "%s카드: %s";
    private static final String DEALER_ACTION_FORM = "딜러는 16이하라 %d장의 카드를 더 받았습니다.";
    private static final String DEALER_CARDS_FORM = "딜러 카드: %s";

    public void printDealingResult(final Players players, final Dealer dealer) {
        String names = String.join(", ", players.getNames());
        System.out.println(String.format(DEALING_RESULT_INTRO, names));
        System.out.println(String.format(DEALER_DEALING_FORM, formatCard(dealer.getFirstCard())));
        for (Player player : players.getPlayers()) {
            System.out.println(formatPlayerCards(player));
        }
    }

    private String formatCard(final Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    private String formatPlayerCards(final Player player) {
        List<String> cards = player.getHand()
                .getCards()
                .stream()
                .map(this::formatCard)
                .toList();
        String joinedCards = String.join(", ", cards);
        return String.format(PLAYER_CARDS_FORM, player.getName(), joinedCards);
    }

    public void printPlayerActionResult(final Player player) {
        System.out.println(formatPlayerCards(player));
    }

    public void printDealerActionResult(final Dealer dealer) {
        System.out.println(String.format(DEALER_ACTION_FORM, dealer.getActionCount()));
    }
}
