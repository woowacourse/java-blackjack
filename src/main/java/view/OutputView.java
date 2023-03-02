package view;

import domain.Card;
import domain.Dealer;
import domain.Participant;
import domain.Player;
import domain.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";
    private static final String GAME_INIT_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String PLAYER_CARDS_FORMAT = "%s카드: %s";
    private static final String SCORE_FORMAT = " - 결과: %d";
    private static final String BUSTED_FORMAT = "%s는 버스트 되었습니다.";
    public static final String DELIMITER = ", ";
    public static final String DEALER_NO_MORE_CARD = "딜러의 카드합이 17이상이라 카드를 더 받지 않았습니다.";
    public static final String DEALER_MORE_CARDS_FORMAT = "딜러는 16이하라 %d장의 카드를 더 받았습니다.";

    public void printInitCards(Dealer dealer, Players players) {
        String namesFormat = players.getPlayers().stream().map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(String.format(GAME_INIT_MESSAGE, namesFormat));
        printInitDealerCards(dealer);
        printInitPlayerCards(players);
    }

    private void printInitDealerCards(Dealer dealer) {
        Card dealerFirstCard = dealer.getDealerFirstCard();

        System.out.println(String.format(PLAYER_CARDS_FORMAT, DEALER_NAME, getCardFormat(dealerFirstCard)));
    }

    private void printInitPlayerCards(Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(String.format(PLAYER_CARDS_FORMAT, player.getName(), getCardsFormat(player.getCards())));
        }
    }

    private String getCardsFormat(List<Card> cards) {
        return cards.stream()
                .map(this::getCardFormat)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardFormat(Card card) {
        return card.getNumberName() + card.getShapeName();
    }

    public void printPlayerCards(Player player) {
        System.out.println(String.format(PLAYER_CARDS_FORMAT, player.getName(), getCardsFormat(player.getCards())));
    }

    public void printBusted(String name) {
        System.out.println(String.format(BUSTED_FORMAT, name));
    }

    public void printDealerHitCount(int hitCardCount) {
        if (hitCardCount == 0) {
            System.out.println(DEALER_NO_MORE_CARD);
            return;
        }
        System.out.println(String.format(DEALER_MORE_CARDS_FORMAT, hitCardCount));
    }

    public void printCardsWithScore(Dealer dealer, Players players){
        printDealerCardsWithScore(dealer);
        printPlayersCardsWithScore(players);
    }
    private void printDealerCardsWithScore(Dealer dealer) {
        System.out.println(getCardsWithScoreFormat(dealer, DEALER_NAME));
    }

    private void printPlayersCardsWithScore(Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(getCardsWithScoreFormat(player, player.getName()));
        }
    }

    private String getCardsWithScoreFormat(Participant participant, String name) {
        return String.format(PLAYER_CARDS_FORMAT + SCORE_FORMAT, name,
                getCardsFormat(participant.getCards()), participant.calculateScore());
    }
}
