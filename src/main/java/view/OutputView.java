package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String COMMA = ", ";
    private static final String HAND_OUT_FORMAT = "%s와 %s에게 2장의 카드를 나누었습니다.";
    private static final String STATUS_FORMAT = "%s: %s";
    private static final String DEALER_HIT_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARDS_SCORE_MESSAGE = "%s - 결과: %d";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 수익";
    private static final String INCOME_RESULT_FORMAT = "%s: %d";

    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void showInitialTurnStatus(Players players, Dealer dealer) {
        System.out.print(System.lineSeparator());
        System.out.printf(HAND_OUT_FORMAT, dealer.getName(), String.join(COMMA, players.toNames()));
        System.out.print(System.lineSeparator());

        showInitialDealerCardStatus(dealer);
        for (Player player : players.getPlayers()) {
            showPlayerCardStatus(player);
        }
        System.out.print(System.lineSeparator());
    }

    public void showPlayerCardStatus(Player player) {
        System.out.println(showCardStatus(player));
    }

    private String showCardStatus(Participant participant) {
        return String.format(STATUS_FORMAT, participant.getName(),
            toString(participant.getCards()));
    }

    private void showInitialDealerCardStatus(Dealer dealer) {
        Card firstCard = dealer.getFirstCard();
        System.out.printf(STATUS_FORMAT, dealer.getName(), expressCard(firstCard));
        System.out.print(System.lineSeparator());
    }

    private String expressCard(Card card) {
        return card.getDenomination() + card.getSymbol();
    }

    private String toString(List<Card> cards) {
        List<String> cardRepresentation = cards.stream()
            .map(this::expressCard)
            .collect(Collectors.toList());

        return String.join(COMMA, cardRepresentation);
    }

    public void showDealerHitCardMessage() {
        System.out.print(System.lineSeparator());
        System.out.println(DEALER_HIT_CARD_MESSAGE);
    }

    public void showFinalTurnStatus(Players players, Dealer dealer) {
        System.out.print(System.lineSeparator());
        System.out.printf(CARDS_SCORE_MESSAGE, showCardStatus(dealer), dealer.calculateScore());
        System.out.print(System.lineSeparator());

        for (Player player : players.getPlayers()) {
            System.out.printf(CARDS_SCORE_MESSAGE, showCardStatus(player), player.calculateScore());
            System.out.print(System.lineSeparator());
        }
    }

    public void showResult(String dealerName,int dealerIncome, List<String> names, List<Integer> playerIncomes) {
        System.out.print(System.lineSeparator());
        System.out.println(FINAL_RESULT_MESSAGE);
        showDealerResult(dealerName, dealerIncome);
        showPlayersResult(names, playerIncomes);
    }

    private void showDealerResult(String dealerName, int dealerIncome) {
        System.out.printf(INCOME_RESULT_FORMAT, dealerName, dealerIncome);
        System.out.print(System.lineSeparator());
    }

    private void showPlayersResult(List<String> names, List<Integer> playerIncomes) {
        for (int i = 0; i < names.size(); i++) {
            System.out.printf(INCOME_RESULT_FORMAT, names.get(i),playerIncomes.get(i));
            System.out.print(System.lineSeparator());
        }
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
