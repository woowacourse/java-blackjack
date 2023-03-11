package view;

import domain.Money;
import domain.card.Card;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String JOINING_DELIMITER = ", ";
    private static final String NAME_AND_VALUE_DELIMITER = ": ";
    private static final String CARD_NOTICE = "카드: ";
    private static final String GIVE_TWO_CARDS_NOTICE = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String ACCEPTED_ADD_CARD_TO_DEALER = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DECLINED_ADD_CARD_TO_DEALER = "\n딜러는 16초과라 카드를 받지 않았습니다.\n";
    private static final String FINAL_RESULT_NOTICE = "\n## 최종 승패";
    private static final String DEALER_NOTICE = "딜러: ";
    private static final String RESULT_NOTICE = " - 결과: ";

    public void printCardsFrom(Users users) {
        printDealCardsNotice(users.players());
        printFirstPlayerCard(users.dealer());
        printPlayersCards(users.players());
    }

    private void printDealCardsNotice(List<Player> players) {
        String joinedPlayerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(JOINING_DELIMITER));

        System.out.printf(GIVE_TWO_CARDS_NOTICE, joinedPlayerNames);
    }

    private void printFirstPlayerCard(User user) {
        String name = user.getName();
        Card card = user.cards().get(0);

        System.out.println(name + NAME_AND_VALUE_DELIMITER + getCardDisplay(card));
    }

    private void printPlayersCards(List<Player> players) {
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    public void printPlayerCards(Player player) {
        System.out.println(player.getName() + CARD_NOTICE + getCardDisplays(player.cards()));
    }

    public void noticeDealerAccept() {
        System.out.println(ACCEPTED_ADD_CARD_TO_DEALER);
    }

    public void noticeDealerDecline() {
        System.out.println(DECLINED_ADD_CARD_TO_DEALER);
    }

    public void printCardsAndScores(Users users) {
        printCardsAndScore(users.dealer());

        for (Player player : users.players()) {
            printCardsAndScore(player);
        }
    }

    public void printCardsAndScore(User user) {
        String name = user.getName();
        String cardDisplays = getCardDisplays(user.cards());

        System.out.println(name + CARD_NOTICE + cardDisplays + RESULT_NOTICE + user.score().value());
    }

    public void printResultNotice() {
        System.out.println(FINAL_RESULT_NOTICE);
    }

    public void printResult(String name, Money money) {
        System.out.println(name + NAME_AND_VALUE_DELIMITER + money.amount());
    }

    public void printDealerResults(Money money) {
        System.out.println(DEALER_NOTICE + money.amount());
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
