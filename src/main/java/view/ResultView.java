package view;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.judge.Judge;
import domain.judge.WinState;
import view.notations.CardNumberNotation;
import view.notations.CardShapeNotation;
import view.notations.WinStateNotation;

import java.util.ArrayList;
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

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LINE_SEPARATOR);
        for (Player player : gamers) {
            stringBuilder.append(String.format("%s카드: ", player.getPlayerName()));
            stringBuilder.append(resolvePlayerCards(player));
            stringBuilder.append(" - 결과: ");
            stringBuilder.append(player.finalizeCardsScore());
            stringBuilder.append(LINE_SEPARATOR);
        }
        System.out.println(stringBuilder);
    }

    public void printFinalResults(Dealer dealer, Judge judge) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 승패");
        stringBuilder.append(LINE_SEPARATOR);
        stringBuilder.append(resolveDealerFinalResult(dealer, judge));
        stringBuilder.append(resolvePlayersFinalResult(judge));
        System.out.println(stringBuilder);
    }

    private String resolvePlayersFinalResult(Judge judge) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Player, WinState> playerWinState : judge.getPlayerResult().entrySet()) {
            stringBuilder.append(resolvePlayerFinalResult(playerWinState));
        }
        return stringBuilder.toString();
    }

    private String resolvePlayerFinalResult(Map.Entry<Player, WinState> playerWinState) {
        Player player = playerWinState.getKey();
        WinState winState = playerWinState.getValue();
        String formattedPlayerResult = String.format("%s: %s", player.getPlayerName(), resolvePlayerResult(winState));
        return formattedPlayerResult + LINE_SEPARATOR;
    }

    private String resolveDealerFinalResult(Dealer dealer, Judge judge) {
        String formattedDealerResult = String.format("%s: %s", dealer.getPlayerName(), resolveDealerResult(judge.getDealerResult()));
        return formattedDealerResult + LINE_SEPARATOR;
    }

    private String resolveDealerResult(Map<WinState, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        dealerResult.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .forEach(entry -> {
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append(resolvePlayerResult(entry.getKey()));
                    stringBuilder.append(" ");
                });
        return stringBuilder.toString();
    }

    private String resolvePlayerResult(WinState winState) {
        return WinStateNotation.findNotationByWinState(winState);
    }
}
