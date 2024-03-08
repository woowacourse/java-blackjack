package view;

import domain.WinState;
import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printInitialCards(Gamers gamers) {
        Dealer dealer = gamers.callDealer();
        List<Player> players = gamers.callPlayers();

        String dealerName = dealer.getPlayerName();
        String playersNames = players.stream()
                .map(Gamer::getPlayerName)
                .collect(Collectors.joining(", "));
        System.out.println(LINE_SEPARATOR + String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, playersNames));

        Card dealerCard = dealer.openOneCard();
        System.out.println(dealerName + ": " + resolveCardExpression(dealerCard));
        for (Gamer gamer : gamers.callPlayers()) {
            printPlayerCards(gamer);
        }
        System.out.print(LINE_SEPARATOR);
    }

    public void printPlayerCards(Gamer gamer) {
        System.out.printf("%s카드: ", gamer.getPlayerName());
        System.out.println(resolvePlayerCards(gamer));
    }

    private String resolvePlayerCards(Gamer gamer) {
        List<String> cards = new ArrayList<>();
        for (Card card : gamer.getCards()) {
            cards.add(resolveCardExpression(card));
        }
        return String.join(", ", cards);
    }

    private String resolveCardExpression(Card card) {
        CardNumber cardNumber = card.getCardNumber();
        CardShape cardShape = card.getCardShape();
        return resolveCardNumber(cardNumber) + resolveCardShape(cardShape);
    }

    private String resolveCardShape(CardShape cardShape) {
        StringBuilder stringBuilder = new StringBuilder();
        if (cardShape.equals(CardShape.HEART)) {
            stringBuilder.append("하트");
        }
        if (cardShape.equals(CardShape.CLOVER)) {
            stringBuilder.append("클로버");
        }
        if (cardShape.equals(CardShape.DIAMOND)) {
            stringBuilder.append("다이아몬드");
        }
        if (cardShape.equals(CardShape.SPADE)) {
            stringBuilder.append("스페이드");
        }
        return stringBuilder.toString();
    }

    private String resolveCardNumber(CardNumber cardNumber) {
        if (cardNumber.equals(CardNumber.A) || cardNumber.equals(CardNumber.J)
                || cardNumber.equals(CardNumber.Q) || cardNumber.equals(CardNumber.K)) {
            return cardNumber.name();
        }
        return String.valueOf(cardNumber.getScore());
    }

    public void printDealerHitMessage(Dealer dealer, Card card) {
        System.out.printf(LINE_SEPARATOR + "%s는 16이하라 카드 %s를 더 받았습니다.", dealer.getPlayerName(), resolveCardExpression(card));
    }

    public void printAllGamersResult(Gamers gamers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LINE_SEPARATOR);
        stringBuilder.append(LINE_SEPARATOR);
        for (Gamer gamer : gamers.getGamers()) {
            stringBuilder.append(String.format("%s카드: ", gamer.getPlayerName()));
            stringBuilder.append(resolvePlayerCards(gamer));
            stringBuilder.append(" - 결과: ");
            stringBuilder.append(gamer.finalizeCardsScore());
            stringBuilder.append(LINE_SEPARATOR);
        }
        System.out.println(stringBuilder);
    }

    public void printFinalResults(Dealer dealer, Map<WinState, Integer> dealerResult, Map<Player, WinState> playersResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 승패");
        stringBuilder.append(LINE_SEPARATOR);
        String formattedDealerResult = String.format("%s: %s", dealer.getPlayerName(), resolveDealerResult(dealerResult));
        stringBuilder.append(formattedDealerResult);
        stringBuilder.append(LINE_SEPARATOR);

        for (Map.Entry<Player, WinState> playerWinState : playersResult.entrySet()) {
            Player player = playerWinState.getKey();
            WinState winState = playerWinState.getValue();
            String formattedPlayerResult = String.format("%s: %s", player.getPlayerName(), resolvePlayerResult(winState));
            stringBuilder.append(formattedPlayerResult);
            stringBuilder.append(LINE_SEPARATOR);
        }
        System.out.println(stringBuilder);
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
        if (winState == WinState.WIN) {
            return "승";
        }
        if (winState == WinState.LOSE) {
            return "패";
        }
        return "무";
    }
}
