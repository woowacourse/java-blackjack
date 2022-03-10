package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.Result;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COMMA = ", ";
    private static final String PARTICIPANTS_INITIAL_HAND_OUT_MESSAGE =
        "%s와 %s에게 2장의 카드를 나누었습니다." + System.lineSeparator();
    private static final String DELIMITER = ": ";
    private static final String DEALER_HIT_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARDS_SCORE_MESSAGE = " - 결과: ";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_MESSAGE = "딜러: ";

    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void showInitialTurnStatus(Players players, Dealer dealer) {
        System.out.printf(PARTICIPANTS_INITIAL_HAND_OUT_MESSAGE,
            dealer.getName(), String.join(COMMA, players.toNames()));

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

    public void showFinalTurnStatus(Players players, Dealer dealer) {
        System.out.println(makeCardStatus(dealer.getName(), dealer.getCards()) + CARDS_SCORE_MESSAGE
            + dealer.calculateScore());
        for (Player player : players.getPlayers()) {
            System.out.println(makeCardStatus(player.getName(),
                player.getCards()) + CARDS_SCORE_MESSAGE + player.calculateScore());
        }
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

    public void showResult(List<Integer> dealerResult, List<String> toNames,
        List<Result> playerResult) {
        System.out.println(FINAL_RESULT_MESSAGE);
        System.out.println(
            DEALER_MESSAGE + dealerResult.get(0) + Result.WIN.getMessage() + dealerResult.get(1)
                + Result.DRAW.getMessage() + dealerResult.get(2)
                + Result.LOSE.getMessage());

        for (int idx = 0; idx < toNames.size(); idx++) {
            System.out.println(toNames.get(idx) + DELIMITER + playerResult.get(idx).getMessage());
        }
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
