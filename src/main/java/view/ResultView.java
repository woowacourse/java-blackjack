package view;

import domain.Judge;
import domain.WinState;
import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.mapper.CardNumberMapper;
import view.mapper.CardShapeMapper;
import view.mapper.WinStateMapper;

public class ResultView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ", ";

    public void printInitialCards(Dealer dealer, List<Player> players) {
        System.out.print(LINE_SEPARATOR);
        System.out.println(resolveMessageSharingCards(dealer, players));
        System.out.println(resolveDealerCard(dealer));
        System.out.println(resolvePlayersCards(players));
    }

    private String resolveMessageSharingCards(Dealer dealer, List<Player> players) {
        String dealerName = dealer.getPlayerName();
        String playersNames = players.stream()
                .map(Player::getPlayerName)
                .collect(Collectors.joining(DELIMITER));
        return String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, playersNames);
    }

    private String resolveDealerCard(Dealer dealer) {
        String dealerName = dealer.getPlayerName();
        Card dealerCard = dealer.openOneCard();
        String dealerCardExpression = resolveCardExpression(dealerCard);
        return String.format("%s카드: %s", dealerName, dealerCardExpression);
    }

    private String resolvePlayersCards(List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            stringBuilder.append(resolvePlayerCards(player));
            stringBuilder.append(LINE_SEPARATOR);
        }
        return stringBuilder.toString();
    }

    private String resolvePlayerCards(Player player) {
        String playerName = player.getPlayerName();
        List<Card> playerCards = player.getCards();
        List<String> playerCardsExpression = playerCards.stream()
                .map(this::resolveCardExpression)
                .toList();
        return String.format("%s카드: %s", playerName, String.join(DELIMITER, playerCardsExpression));
    }

    private String resolveCardExpression(Card card) {
        CardNumber cardNumber = card.getCardNumber();
        CardShape cardShape = card.getCardShape();
        return CardNumberMapper.toExpression(cardNumber) + CardShapeMapper.toExpression(cardShape);
    }

    public void printPlayerCards(Player player) {
        System.out.println(resolvePlayerCards(player));
    }

    public void printDealerHitMessage(Dealer dealer, Card card) {
        String dealerName = dealer.getPlayerName();
        System.out.printf(LINE_SEPARATOR + "%s는 16이하라 카드 %s를 더 받았습니다.", dealerName, resolveCardExpression(card));
    }

    public void printGamersCardsScore(Gamers gamers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LINE_SEPARATOR);
        for (Player gamer : gamers.getGamers()) {
            stringBuilder.append(String.format("%s - 결과: %d\n", resolvePlayerCards(gamer), gamer.finalizeCardsScore()));
        }
        System.out.println(stringBuilder);
    }

    public void printFinalResult(Dealer dealer, Judge judge) {
        System.out.println("## 최종 승패");
        System.out.println(LINE_SEPARATOR);
        System.out.println(resolveDealerFinalResult(dealer, judge) + resolvePlayersFinalResult(judge));
    }

    private String resolveDealerFinalResult(Dealer dealer, Judge judge) {
        return String.format("%s: %s", dealer.getPlayerName(), resolveDealerWinState(judge.getDealerResult()))
                + LINE_SEPARATOR;
    }

    private String resolveDealerWinState(Map<WinState, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        dealerResult.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .forEach(entry -> {
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append(WinStateMapper.toExpression(entry.getKey()));
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
        return String.format("%s: %s", player.getPlayerName(), WinStateMapper.toExpression(playerWinState))
                + LINE_SEPARATOR;
    }
}
