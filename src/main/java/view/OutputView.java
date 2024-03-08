package view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;
import model.blackjackgame.BlackjackGame;
import model.blackjackgame.GameResult;
import model.card.Card;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String AFTER_SETTING_INTRO = NEWLINE + DEALER_NAME + "와 %s에게 2장을 나누었습니다." + NEWLINE;
    private static final String DEALER_CARD_FORMAT = DEALER_NAME + ": %s" + NEWLINE;
    private static final String CARDS_FORMAT = "%s카드: %s" + NEWLINE;
    private static final String DEALER_HIT = NEWLINE + DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE;
    private static final String FINAL_SCORE_FORMAT = NEWLINE + "%s카드: %s - 결과: %d";

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
        String playerCards = createCardsMessage(player.getCards());
        System.out.printf(CARDS_FORMAT, player.getName(), playerCards);
    }

    public static void printAfterDealerHit() {
        System.out.println(DEALER_HIT);
    }

    public static void printFinalScore(Dealer dealer, Players players, GameResult gameResult) {
        String dealerCards = createCardsMessage(dealer.getCards());
        System.out.printf(FINAL_SCORE_FORMAT, DEALER_NAME, dealerCards, gameResult.findDealerScore());
        for (Player player : players.getPlayers()) {
            String playerName = player.getName();
            String playerCards = createCardsMessage(player.getCards());
            System.out.printf(FINAL_SCORE_FORMAT, playerName, playerCards, gameResult.findPlayerScore(playerName));
        }
    }

    private static String createCardsMessage(Cards cards) {
        return cards.getCards()
            .stream()
            .map(Card::toString)
            .collect(collectingAndThen(toList(), result -> String.join(DELIMITER, result)));
    }
}
