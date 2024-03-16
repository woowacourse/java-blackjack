package blackjack.view;

import blackjack.model.blackjackgame.PlayersBlackjackResults;
import blackjack.model.blackjackgame.Profit;
import blackjack.model.cards.Card;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Name;
import blackjack.model.participants.Participant;
import blackjack.model.participants.Player;
import blackjack.model.participants.Players;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";
    private static final String MULTIPLE_OUTPUTS_DELIMITER = ", ";
    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("#,###.#");

    public void printDistributedCardsInfo(final Players players, final Dealer dealer) {
        System.out.println();
        List<Player> allPlayers = players.getPlayers();
        System.out.printf(DEALER_NAME + "와 %s에게 2장을 나누었습니다.%n", getPlayersNamesInOneLine(allPlayers));

        Card dealerCard = getDealerCard(dealer);
        System.out.println(getNameAndInformationFormat(DEALER_NAME, convertCardText(dealerCard)));
        System.out.println(getPlayersCardsInOneLine(allPlayers));
    }

    public void printPlayerCardsInfo(final Players players, final int index) {
        Player player = players.getPlayer(index);
        System.out.println(getNameAndInformationFormat(getPlayerName(player), getCardsInOneLine(player)));
    }

    public void printDealerChange() {
        System.out.println();
        System.out.println(DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(final Players players, final Dealer dealer) {
        System.out.println();
        System.out.println(getParticipantScoreFormat(DEALER_NAME, getCardsInOneLine(dealer), getScore(dealer)));
        System.out.println(getParticipantsScoresInOneLine(players));
    }

    public void printGameResults(final PlayersBlackjackResults playersBlackjackResults) {
        System.out.println();
        System.out.println("### 최종 수익");
        System.out.println(getNameAndInformationFormat(DEALER_NAME, playersBlackjackResults.calculateDealerProfit()));
        System.out.println(getPlayerResultsInOneLine(playersBlackjackResults.getResults()));
    }

    public void printInvalidDrawCardState() {
        System.out.println("카드 합계가 21을 초과하였습니다. 턴을 종료합니다.");
    }

    private String getPlayersNamesInOneLine(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .map(Name::getValue)
                .collect(Collectors.joining(MULTIPLE_OUTPUTS_DELIMITER));
    }

    private String getPlayersCardsInOneLine(final List<Player> allPlayers) {
        return allPlayers.stream()
                .map(player -> getNameAndInformationFormat(getPlayerName(player), getCardsInOneLine(player)))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String getParticipantsScoresInOneLine(final Players players) {
        return players.getPlayers()
                .stream()
                .map(player ->
                        getParticipantScoreFormat(getPlayerName(player), getCardsInOneLine(player), getScore(player)))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String getPlayerResultsInOneLine(final Map<Player, Profit> results) {
        return results.entrySet()
                .stream()
                .map(result -> getNameAndInformationFormat(getPlayerName(result.getKey()), result.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String getCardsInOneLine(final Participant participant) {
        return participant.getCards()
                .getCards()
                .stream()
                .map(this::convertCardText)
                .collect(Collectors.joining(MULTIPLE_OUTPUTS_DELIMITER));
    }

    private String getNameAndInformationFormat(final String name, final String information) {
        return String.format("%s: %s", name, information);
    }

    private String getNameAndInformationFormat(final String name, final Profit profit) {
        return String.format("%s: %s", name, getProfitFormat(profit));
    }

    private String getParticipantScoreFormat(final String name, final String cards, final int score) {
        return String.format("%s: %s - 결과: %d", name, cards, score);
    }

    private String getProfitFormat(final Profit profit) {
        return DOUBLE_FORMAT.format(profit.getValue());
    }

    private String getPlayerName(final Player player) {
        return player.getName().getValue();
    }

    private Card getDealerCard(final Dealer dealer) {
        return dealer.getCards()
                .getCards()
                .get(0);
    }

    private int getScore(final Participant participant) {
        return participant.getCards().getCardsScore().getValue();
    }

    private String convertCardText(final Card card) {
        CardNumberText cardNumberText = CardNumberText.from(card.getCardNumber());
        CardShapeText cardShapeText = CardShapeText.from(card.getCardShape());
        return cardNumberText.getText() + cardShapeText.getText();
    }
}
