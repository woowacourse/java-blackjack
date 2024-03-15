package view;

import domain.Profit;
import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.result.Judge;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import view.mapper.CardNumberMapper;
import view.mapper.CardShapeMapper;

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
        System.out.print(LINE_SEPARATOR);
        System.out.printf("%s는 16이하라 카드 %s를 더 받았습니다.", dealerName, resolveCardExpression(card));
        System.out.print(LINE_SEPARATOR);
    }

    public void printGamersCardsScore(Gamers gamers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LINE_SEPARATOR);
        for (Player gamer : gamers.getGamers()) {
            stringBuilder.append(String.format("%s - 결과: %d\n",
                    resolvePlayerCards(gamer), gamer.finalizeCardsScore()));
        }
        System.out.println(stringBuilder);
    }

    public void printFinalProfit(Dealer dealer, Judge judge) {
        System.out.println("## 최종 수익");
        Profit dealerProfit = judge.getDealerProfit();
        Map<Player, Profit> playersProfit = judge.getPlayersProfit();
        System.out.println(resolveDealerProfit(dealer, dealerProfit));
        System.out.println(resolvePlayersProfit(playersProfit));
    }

    private String resolveDealerProfit(Dealer dealer, Profit profit) {
        return String.format("%s: %.1f", dealer.getPlayerName(), profit.getValue());
    }

    private String resolvePlayersProfit(Map<Player, Profit> profitResult) {
        List<String> resolvedProfits = new ArrayList<>();
        for (Entry<Player, Profit> playerWinState : profitResult.entrySet()) {
            resolvedProfits.add(resolvePlayerProfit(playerWinState));
        }
        return String.join(LINE_SEPARATOR, resolvedProfits);
    }

    private String resolvePlayerProfit(Entry<Player, Profit> playerWinState) {
        Player player = playerWinState.getKey();
        Profit profit = playerWinState.getValue();
        return String.format("%s: %.1f", player.getPlayerName(), profit.getValue());
    }
}
