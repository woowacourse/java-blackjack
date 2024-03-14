package view;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.gamer.bet.BetAmount;
import view.notations.CardNumberNotation;
import view.notations.CardShapeNotation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ", ";
    public static final int DEALER_CARD_INDEX = 0;

    public void printInitialCards(Dealer dealer, List<Player> players) {
        printSharingCardsMessage(dealer, players);
        printDealerCard(dealer);
        printPlayersCards(players);
    }

    private void printSharingCardsMessage(Dealer dealer, List<Player> players) {
        String dealerName = dealer.getPlayerName();
        String playersNames = players.stream()
                .map(Player::getPlayerName)
                .collect(Collectors.joining(DELIMITER));
        System.out.println(LINE_SEPARATOR + String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, playersNames));
    }

    private void printDealerCard(Dealer dealer) {
        Card dealerCard = dealer.getHand().get(DEALER_CARD_INDEX);
        System.out.println(dealer.getPlayerName() + ": " + resolveCardExpression(dealerCard));
    }

    private void printPlayersCards(List<Player> players) {
        for (Player player : players) {
            printPlayerCards(player);
        }
        System.out.print(LINE_SEPARATOR);
    }

    public void printPlayerCards(Player player) {
        System.out.printf("%s카드: ", player.getPlayerName());
        System.out.println(resolvePlayerCards(player));
    }

    private String resolvePlayerCards(Player player) {
        List<String> cards = new ArrayList<>();
        for (Card card : player.getHand()) {
            cards.add(resolveCardExpression(card));
        }
        return String.join(DELIMITER, cards);
    }

    private String resolveCardExpression(Card card) {
        CardNumber cardNumber = card.getCardNumber();
        CardShape cardShape = card.getCardShape();
        return resolveCardNumber(cardNumber) + resolveCardShape(cardShape);
    }

    private String resolveCardNumber(CardNumber cardNumber) {
        try {
            return CardNumberNotation.findNotationByCardNumber(cardNumber);
        } catch (IllegalArgumentException e) {
            return String.valueOf(cardNumber.getScore());
        }
    }

    private String resolveCardShape(CardShape cardShape) {
        return CardShapeNotation.findNotationByCardShape(cardShape);
    }

    public void printDealerHitMessage(Dealer dealer, Card card) {
        System.out.printf(LINE_SEPARATOR + "%s는 16이하라 카드 %s를 더 받았습니다.",
                dealer.getPlayerName(), resolveCardExpression(card));
    }

    public void printAllGamersCardsResult(Dealer dealer, Players players) {
        List<Player> gamers = new ArrayList<>(players.getPlayers());
        gamers.add(0, dealer);

        StringBuilder allGamersCardsResult = new StringBuilder();
        allGamersCardsResult.append(LINE_SEPARATOR);
        for (Player player : gamers) {
            allGamersCardsResult.append(reslovePlayerCardsAndScore(player));
        }
        System.out.println(allGamersCardsResult);
    }

    private String reslovePlayerCardsAndScore(Player player) {
        return String.format("%s카드: ", player.getPlayerName())
                + resolvePlayerCards(player)
                + " - 결과: "
                + player.finalizeCardsScore()
                + LINE_SEPARATOR;
    }

    public void printFinalProfit(Map<Player, BetAmount> results, Dealer dealer) {
        System.out.println("## 최종 수익");
        LinkedHashMap<Player, BetAmount> copiedResults = new LinkedHashMap<>(results);
        printDealerResult(copiedResults, dealer);
        printPlayersResult(copiedResults);
    }

    private static void printPlayersResult(LinkedHashMap<Player, BetAmount> copiedResults) {
        for (Map.Entry<Player, BetAmount> playerBetAmountEntry : copiedResults.entrySet()) {
            Player player = playerBetAmountEntry.getKey();
            BetAmount betAmount = playerBetAmountEntry.getValue();
            System.out.println(player.getPlayerName() + ": " + betAmount.getBetAmount());
        }
    }

    private void printDealerResult(Map<Player, BetAmount> results, Dealer dealer) {
        System.out.println(dealer.getPlayerName() + ": " + results.get(dealer).getBetAmount());
        results.remove(dealer);
    }
}
