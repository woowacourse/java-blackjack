package view;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;

import static domain.Result.*;

public class OutputView {
    private static final String JOINING_DELIMITER = ", ";
    private static final String NAME_AND_VALUE_DELIMITER = ": ";
    private static final String CARD_NOTICE = "카드: ";
    private static final String GIVE_TWO_CARDS_NOTICE = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String ACCEPTED_ADD_CARD_TO_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DECLINED_ADD_CARD_TO_DEALER = "딜러는 16초과라 카드를 받지 않았습니다.";
    private static final String FINAL_RESULT_NOTICE = "## 최종 승패";
    private static final String DEALER_NOTICE = "딜러: ";
    private static final String RESULT_NOTICE = " - 결과: ";

    public void printCardsFrom(Users users) {
        printDealCardsNotice(users.getPlayers());
        printFirstPlayerCard(users.getDealer());
        printPlayersCards(users.getPlayers());
    }

    private void printDealCardsNotice(List<Player> players) {
        String joinedPlayerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(JOINING_DELIMITER));

        System.out.printf(GIVE_TWO_CARDS_NOTICE, joinedPlayerNames);
    }

    private void printFirstPlayerCard(User user) {
        String name = user.getName();
        Card card = user.getCards().get(0);

        System.out.println(name + NAME_AND_VALUE_DELIMITER + getCardDisplay(card));
    }

    private void printPlayersCards(List<Player> players) {
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    public void printPlayerCards(Player player) {
        System.out.println(player.getName() + CARD_NOTICE + getCardDisplays(player.getCards()));
    }

    public void noticeDealerAccept() {
        System.out.println(ACCEPTED_ADD_CARD_TO_DEALER);
    }

    public void noticeDealerDecline() {
        System.out.println(DECLINED_ADD_CARD_TO_DEALER);
    }

    public void printCardsAndScores(Users users) {
        printCardsAndScore(users.getDealer());

        for (Player player : users.getPlayers()) {
            printCardsAndScore(player);
        }
    }

    public void printCardsAndScore(User user) {
        String name = user.getName();
        String cardDisplays = getCardDisplays(user.getCards());

        System.out.println(name + CARD_NOTICE + cardDisplays + RESULT_NOTICE + user.score().getValue());
    }

    public void printResultNotice() {
        System.out.println(FINAL_RESULT_NOTICE);
    }

    public void printResult(String name, Result result) {
        System.out.println(name + NAME_AND_VALUE_DELIMITER + result.message());
    }

    public void printDealerResults(List<Result> results) {
        long winCount = getCount(results, WIN);
        long loseCount = getCount(results, LOSE);
        long drawCount = getCount(results, DRAW);
        String result = winCount + WIN.message() + drawCount + DRAW.message() + loseCount + LOSE.message();
        System.out.println(DEALER_NOTICE + result);
    }

    private long getCount(List<Result> results, Result target) {
        return results.stream()
                .filter(result -> result.equals(target))
                .count();
    }

    private String getCardDisplays(List<Card> cards) {
        return cards.stream()
                .map(this::getCardDisplay)
                .collect(Collectors.joining(JOINING_DELIMITER));
    }

    private String getCardDisplay(Card card) {
        return card.letter() + card.suit().getName();
    }

}
