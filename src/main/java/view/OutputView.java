package view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;
import model.blackjackgame.BlackjackGame;
import model.card.Card;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String AFTER_SETTING_INTRO = NEWLINE + "딜러와 %s에게 2장을 나누었습니다." + NEWLINE;
    private static final String DEALER_CARD_FORMAT = "딜러: %s" + NEWLINE;
    private static final String PLAYER_CARD_FORMAT = "%s카드: %s" + NEWLINE;

    public static void printCardsAfterSetting(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        printAfterSettingIntro(players);
        printDealerCard(dealer);
        printPlayersCards(players);
    }

    private static void printAfterSettingIntro(Players players) {
        String playerNames = players.getPlayers()
            .stream()
            .map(Player::getName)
            .collect(collectingAndThen(toList(), names -> String.join(DELIMITER, names)));
        System.out.printf(AFTER_SETTING_INTRO, playerNames);
    }

    private static void printDealerCard(Dealer dealer) {
        List<Card> cards = dealer.getCards().getCards();
        System.out.printf(DEALER_CARD_FORMAT, cards.get(0));
    }

    private static void printPlayersCards(Players players) {
        players.getPlayers()
            .forEach(OutputView::printPlayerCard);
        System.out.println();
    }

    public static void printPlayerCard(Player player) {
        String playerCards = player.getCards()
            .getCards()
            .stream()
            .map(Card::toString)
            .collect(collectingAndThen(toList(), cards -> String.join(DELIMITER, cards)));
        System.out.printf(PLAYER_CARD_FORMAT, player.getName(), playerCards);
    }
}
