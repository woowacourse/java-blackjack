package view;

import domain.Judge;
import domain.WinState;
import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ", ";

    public void printInitialCards(Dealer dealer, List<Player> players) {
        System.out.print(LINE_SEPARATOR);
        System.out.println(resolveMessageSharingCards(dealer, players));
        System.out.println(resolveDealerCard(dealer.getPlayerName(), dealer.openOneCard()));
        System.out.println(resolvePlayersCards(players));
    }

    private String resolveMessageSharingCards(Dealer dealer, List<Player> players) {
        String dealerName = dealer.getPlayerName();
        String playersNames = players.stream()
                .map(Player::getPlayerName)
                .collect(Collectors.joining(DELIMITER));
        return String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, playersNames);
    }

    private String resolveDealerCard(String dealerName, Card dealerCard) {
        return String.format("%s카드: %s", dealerName, resolveCardExpression(dealerCard));
    }

    private String resolvePlayersCards(List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            stringBuilder.append(resolvePlayerCards(player.getPlayerName(), player.getCards()));
            stringBuilder.append(LINE_SEPARATOR);
        }
        return stringBuilder.toString();
    }

    private String resolvePlayerCards(String playerName, List<Card> playerCards) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s카드: ", playerName));
        List<String> cards = new ArrayList<>();
        for (Card card : playerCards) {
            cards.add(resolveCardExpression(card));
        }
        stringBuilder.append(String.join(DELIMITER, cards));
        return stringBuilder.toString();
    }

    private String resolveCardExpression(Card card) {
        CardNumber cardNumber = card.getCardNumber();
        CardShape cardShape = card.getCardShape();
        return resolveCardNumber(cardNumber) + resolveCardShape(cardShape);
    }

    private String resolveCardNumber(CardNumber cardNumber) {
        if (cardNumber.equals(CardNumber.A) || cardNumber.equals(CardNumber.J)
                || cardNumber.equals(CardNumber.Q) || cardNumber.equals(CardNumber.K)) {
            return cardNumber.name();
        }
        return String.valueOf(cardNumber.getScore());
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

    public void printPlayerCards(Player player) {
        System.out.println(resolvePlayerCards(player.getPlayerName(), player.getCards()));
    }

    public void printDealerHitMessage(Dealer dealer, Card card) {
        System.out.println(resolveDealerHitMessage(dealer.getPlayerName(), card));
    }

    private String resolveDealerHitMessage(String dealerName, Card card) {
        return String.format(LINE_SEPARATOR + "%s는 16이하라 카드 %s를 더 받았습니다.",
                dealerName, resolveCardExpression(card));
    }

    public void printGamersCardsScore(Gamers gamers) {
        System.out.println(resolveGamersCardsScore(gamers.getGamers()));
    }

    private String resolveGamersCardsScore(List<Player> gamers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LINE_SEPARATOR);
        for (Player gamer : gamers) {
            stringBuilder.append(String.format("%s - 결과: %d\n",
                    resolvePlayerCards(gamer.getPlayerName(), gamer.getCards()), gamer.finalizeCardsScore()));
        }
        return stringBuilder.toString();
    }

    public void printFinalResult(Dealer dealer, Judge judge) {
        System.out.println(resolveFinalResult(dealer, judge));
    }

    private String resolveFinalResult(Dealer dealer, Judge judge) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 승패");
        stringBuilder.append(LINE_SEPARATOR);
        stringBuilder.append(resolveDealerFinalResult(dealer, judge));
        stringBuilder.append(resolvePlayersFinalResult(judge));
        return stringBuilder.toString();
    }

    private String resolveDealerFinalResult(Dealer dealer, Judge judge) {
        return String.format("%s: %s", dealer.getPlayerName(),
                resolveDealerWinState(judge.getDealerResult()))
                + LINE_SEPARATOR;
    }

    private String resolveDealerWinState(Map<WinState, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        dealerResult.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .forEach(entry -> {
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append(resolveWinState(entry.getKey()));
                    stringBuilder.append(" ");
                });
        return stringBuilder.toString();
    }

    private String resolvePlayersFinalResult(Judge judge) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Player, WinState> playerWinState : judge.getPlayerResult().entrySet()) {
            Player player = playerWinState.getKey();
            WinState winState = playerWinState.getValue();
            stringBuilder.append(resolvePlayerFinalResult(player, winState));
        }
        return stringBuilder.toString();
    }

    private String resolvePlayerFinalResult(Player player, WinState playerWinState) {
        return String.format("%s: %s", player.getPlayerName(),
                resolveWinState(playerWinState))
                + LINE_SEPARATOR;
    }

    private String resolveWinState(WinState winState) {
        if (winState == WinState.WIN) {
            return "승";
        }
        if (winState == WinState.LOSE) {
            return "패";
        }
        return "무";
    }
}
