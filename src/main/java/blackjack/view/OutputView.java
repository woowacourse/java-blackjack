package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerProfitAmounts;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.ProfitAmount;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String SPLIT_DELIMITER = ", ";
    private static final String RESULT_FORMAT = " - 결과: ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printPlayersInitialHand(Players players, Dealer dealer) {
        List<Player> resultPlayers = players.getPlayers();
        List<Name> playerNames = getPlayerNames(resultPlayers);
        System.out.println(String.format(LINE_SEPARATOR + "딜러와 %s에게 2장을 나누었습니다.", convertNameToString(playerNames)));
        List<Card> visibleCards = dealer.getVisibleCards();
        System.out.println("딜러: " + convertCardsToString(visibleCards));
        resultPlayers.forEach(player -> System.out.println(convertParticipantHandToString(player)));
        System.out.println();
    }

    private List<Name> getPlayerNames(List<Player> resultPlayers) {
        return resultPlayers.stream()
                .map(Player::getName)
                .toList();
    }

    private String convertNameToString(List<Name> playerNames) {
        return playerNames.stream()
                .map(Name::getName)
                .collect(Collectors.joining(SPLIT_DELIMITER));
    }

    private String convertCardsToString(List<Card> visibleCards) {
        return visibleCards.stream()
                .map(this::convertCardToString)
                .collect(Collectors.joining(SPLIT_DELIMITER));
    }

    private String convertCardToString(Card card) {
        return OutputDenomination.convertDenominationToString(card.getDenomination()) +
                OutputSuit.convertSuitToString(card.getSuit());
    }

    public String convertParticipantHandToString(Participant participant) {
        return participant.getName().getName() + "카드: " + convertCardsToString(participant.getCards());
    }

    public void printParticipantHand(Participant participant) {
        System.out.println(convertParticipantHandToString(participant));
    }

    public void printDealerDraw() {
        System.out.println(LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printParticipantResult(Players players, Dealer dealer) {
        List<Player> resultPlayers = players.getPlayers();
        List<Card> cards = dealer.getCards();
        System.out.println(
                LINE_SEPARATOR + "딜러: " + convertCardsToString(cards) + RESULT_FORMAT + dealer.calculateHand()
                        .getValue());
        resultPlayers.forEach(player -> System.out.println(
                convertParticipantHandToString(player) + RESULT_FORMAT + player.calculateHand().getValue()));
    }

    public void printProfitDetails(PlayerProfitAmounts playerProfitAmounts) {
        ProfitAmount dealerProfitAmount = playerProfitAmounts.calculateDealerProfit();
        Map<Name, ProfitAmount> playerProfit = playerProfitAmounts.getProfitAmounts();

        System.out.println(LINE_SEPARATOR + "## 최종 수익");
        System.out.println(String.format("%-4s:%7d", "딜러", dealerProfitAmount.getAmount()));
        playerProfit.forEach(
                (name, profit) -> System.out.println(String.format("%-6s:%7d", name.getName(), profit.getAmount())));
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
