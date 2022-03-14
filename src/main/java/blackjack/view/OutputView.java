package blackjack.view;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

import blackjack.domain.card.Card;
import blackjack.domain.MatchResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String PLAYER_MATCH_RESULT_MESSAGE = "%s: %s" + NEW_LINE;
    private static final String PLAYER_SCORE_MESSAGE = "%s카드: %s - 결과: %d" + NEW_LINE;
    private static final String DEALER_SCORE_MESSAGE = NEW_LINE + "%s 카드: %s - 결과: %d" + NEW_LINE;
    private static final String DEALER_ONE_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String GIVE_CARDS_TO_PLAYERS_MESSAGE = NEW_LINE + "딜러와 %s에게 2장의 카드를 나누었습니다." + NEW_LINE;
    private static final String GIVE_CARDS_TO_DEALER_MESSAGE = "%s: %s" + NEW_LINE;
    private static final String PLAYER_CARD_MESSAGE = "%s카드: %s" + NEW_LINE;
    private static final String COMMA_DELIMITER = ", ";
    private static final String SPACE_DELIMINATOR = " ";
    private static final String BLACKJACK_GAME_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_MATCH_RESULT_MESSAGE = "딜러: ";

    public static void printGiveCardsToParticipants(Dealer dealer, List<Player> players) {
        out.printf(GIVE_CARDS_TO_PLAYERS_MESSAGE, getPlayerNames(players));
        out.printf(GIVE_CARDS_TO_DEALER_MESSAGE, dealer.getName(), cardDisplayContents(dealer.getOpenCard()));
        for (Player player : players) {
            printPlayerHand(player);
        }
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(joining(COMMA_DELIMITER));
    }

    public static void showPlayerHand(Player player) {
        printPlayerHand(player);
    }

    private static void printPlayerHand(Player player) {
        out.printf(PLAYER_CARD_MESSAGE, player.getName(), getCardsFrom(player));
    }

    public static void printDealerOneMoreCard() {
        out.println(DEALER_ONE_MORE_CARD_MESSAGE);
    }

    public static void printParticipantScore(Dealer dealer, List<Player> players) {
        printDealerScore(dealer);
        printPlayersScore(players);
        out.println();
    }

    private static void printDealerScore(Dealer dealer) {
        String dealerCards = dealer.getCards().stream()
                .map(card -> cardDisplayContents(card))
                .collect(joining(COMMA_DELIMITER));

        out.printf(DEALER_SCORE_MESSAGE, dealer.getName(), dealerCards, dealer.getScore());
    }

    private static void printPlayersScore(List<Player> players) {
        for (Player player : players) {
            out.printf(PLAYER_SCORE_MESSAGE, player.getName(), getCardsFrom(player), player.getCardHand().getScore());
        }
    }

    private static String getCardsFrom(Player player) {
        return player.getCards().stream()
                .map(card -> cardDisplayContents(card))
                .collect(joining(COMMA_DELIMITER));
    }

    private static String cardDisplayContents(Card card) {
        return card.getDenomination() + card.getSuit();
    }

    public static void printBlackjackGameResult(Dealer dealer, List<Player> players) {
        out.println(BLACKJACK_GAME_RESULT_MESSAGE);
        printDealerMatchResult(dealer);
        printPlayerMatchResult(players);
    }

    private static void printDealerMatchResult(Dealer dealer) {
        Map<MatchResult, Integer> resultScores = dealer.getMatchResultScores();
        String dealerScoreString = resultScores.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(joining(SPACE_DELIMINATOR));
        out.println(DEALER_MATCH_RESULT_MESSAGE + dealerScoreString);
    }

    private static void printPlayerMatchResult(List<Player> players) {
        for (Player player : players) {
            out.printf(PLAYER_MATCH_RESULT_MESSAGE, player.getName(), player.getResult().getName());
        }
    }
}
