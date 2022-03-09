package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COMMA = ", ";
    private static final String PARTICIPANTS_INITIAL_HAND_OUT_MESSAGE =
        "%s와 %s에게 2장의 카드를 나누었습니다." + System.lineSeparator();
    private static final String DELIMITER = ": ";
    public static final String DEALER_HIT_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void showInitialTurnStatus(Players players, Dealer dealer) {
        List<String> playerNames = players.toNames();

        System.out.printf(PARTICIPANTS_INITIAL_HAND_OUT_MESSAGE,
            dealer.getName(), String.join(COMMA, playerNames));

        showInitialDealerCardStatus(dealer);

        for (Player player : players.getPlayers()) {
            showPlayerCardStatus(player);
        }
    }

    public void showInitialDealerCardStatus(Dealer dealer) {
        String dealerCardStatus = makeCardStatus(dealer.getName(), dealer.getCards().subList(0, 1));
        System.out.println(dealerCardStatus);

    }

    public void showPlayerCardStatus(Player player) {
        String playerCardStatus = makeCardStatus(player.getName(), player.getCards());
        System.out.println(playerCardStatus);
    }

    private String makeCardStatus(String name, List<Card> cards) {
        return name + DELIMITER + getDeck(cards);
    }

    private String getDeck(List<Card> cards) {
        List<String> cardRepresentation = cards.stream()
            .map(card -> card.getDenomination() + card.getSymbol())
            .collect(Collectors.toList());

        return String.join(COMMA, cardRepresentation);
    }

    public void showDealerHitCardMessage() {
        System.out.println(DEALER_HIT_CARD_MESSAGE);
    }
}
