package blackjack.view;

import blackjack.model.blackjackgame.PlayersGameResults;
import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";
    private static final String MULTIPLE_OUTPUTS_DELIMITER = ", ";

    public void printDistributedCardsInfo(List<Player> players, Dealer dealer) {
        System.out.println();
        System.out.printf(DEALER_NAME + "와 %s에게 2장을 나누었습니다.%n", getPlayersNames(players));

        System.out.print(getDealerCardFormat(getDealerCard(dealer)));
        players.forEach(player ->
                System.out.print(getParticipantCardsFormat(player.getName(), player.getCards())));
        System.out.println();
    }

    public void printPlayerCardsInfo(List<Player> players, int index) {
        Player player = players.get(index);
        System.out.print(getParticipantCardsFormat(player.getName(), player.getCards()));
    }

    public void printDealerChange() {
        System.out.println();
        System.out.println(DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(List<Player> players, Dealer dealer) {
        System.out.println();
        System.out.print(getParticipantScoreFormat(DEALER_NAME, dealer.getCards(), dealer.getCards().getCardsScore()));
        players.forEach(player -> System.out.printf(
                getParticipantScoreFormat(player.getName(), player.getCards(),
                        player.getCards().getCardsScore())));
    }

    public void printGameResults(PlayersGameResults playersGameResults) {
        System.out.println();
        System.out.println("### 최종 승패");
        Map<DealerResultStatus, Long> dealerResult = playersGameResults.getDealerResult();

        System.out.printf("%s: %s%n", DEALER_NAME, getDealerResultFormat(dealerResult));
        Map<Player, PlayerResultStatus> gameResult = playersGameResults.getResult();
        printPlayerResultsFormat(gameResult);
    }

    public void printInvalidDrawCardState() {
        System.out.println("카드 합계가 21을 초과하였습니다. 턴을 종료합니다.");
    }

    private String getDealerCardFormat(Card card) {
        return String.format("%s: %s%n", DEALER_NAME, convertCardText(card));
    }

    private String getParticipantCardsFormat(String name, Cards cards) {
        return String.format("%s: %s%n", name, getCardsText(cards));
    }

    private String getParticipantScoreFormat(String name, Cards cards, int score) {
        return String.format("%s: %s - 결과: %d%n", name, getCardsText(cards), score);
    }

    private void printPlayerResultsFormat(Map<Player, PlayerResultStatus> gameResult) {
        gameResult.entrySet()
                .stream()
                .map(entry -> getPlayerResultFormat(entry.getKey().getName(), entry.getValue().getResult()))
                .forEach(System.out::print);
    }

    private String getPlayerResultFormat(String name, String result) {
        return String.format("%s: %s%n", name, result);
    }

    private String getDealerResultFormat(Map<DealerResultStatus, Long> dealerResult) {
        return dealerResult.entrySet()
                .stream()
                .map(entry -> String.format("%d%s", entry.getValue(), entry.getKey().getResult()))
                .collect(Collectors.joining(" "));
    }

    private String getPlayersNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(MULTIPLE_OUTPUTS_DELIMITER));
    }

    private Card getDealerCard(Dealer dealer) {
        return dealer.getCards()
                .getCards()
                .get(0);
    }

    private String getCardsText(Cards cards) {
        return cards.getCards()
                .stream()
                .map(this::convertCardText)
                .collect(Collectors.joining(MULTIPLE_OUTPUTS_DELIMITER));
    }

    private String convertCardText(Card card) {
        String cardNumberText = card.getCardNumber().getText();
        String cardShapeText = card.getCardShape().getText();
        return cardNumberText + cardShapeText;
    }
}
