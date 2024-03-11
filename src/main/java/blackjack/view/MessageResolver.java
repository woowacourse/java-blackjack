package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardShape.CLOVER;
import static blackjack.domain.card.CardShape.DIAMOND;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardShape.SPADE;

public class MessageResolver {
    private static final Map<CardShape, String> CARD_SHAPE_NAME_MAP = Map.of(
            SPADE, "스페이드",
            HEART, "하트",
            DIAMOND, "다이아몬드",
            CLOVER, "클로버"
    );

    private static final Map<CardNumber, String> CARD_NUMBER_NAME_MAP = Map.ofEntries(
            Map.entry(ACE, "A"), Map.entry(TWO, "2"),
            Map.entry(THREE, "3"), Map.entry(FOUR, "4"),
            Map.entry(FIVE, "5"), Map.entry(SIX, "6"),
            Map.entry(SEVEN, "7"), Map.entry(EIGHT, "8"),
            Map.entry(NINE, "9"), Map.entry(TEN, "10"),
            Map.entry(JACK, "J"), Map.entry(QUEEN, "Q"),
            Map.entry(KING, "K")
    );

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PLAYERS_NAME_DELIMITER = ", ";

    public String resolveInitialHandOfEachPlayer(final Dealer dealer, final List<Player> players) {
        final String initialDistributionMessage = resolveInitialDistributionMessage(dealer, players);
        final String dealerCardMessage = resolveDealerCard(dealer);
        final String playersCardMessage = players.stream().map(this::resolvePlayerCard).collect(Collectors.joining(LINE_SEPARATOR));
        return String.join(LINE_SEPARATOR, initialDistributionMessage, dealerCardMessage, playersCardMessage);
    }

    private String resolveInitialDistributionMessage(final Dealer dealer, final List<Player> players) {
        final String playerNames = resolvePlayerNames(players);
        final String message = String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), playerNames);
        return String.join("", LINE_SEPARATOR, message);
    }

    private String resolvePlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(PLAYERS_NAME_DELIMITER));
    }

    private String resolveDealerCard(final Dealer dealer) {
        final Card card = dealer.getFirstCard();
        return String.join(": ", dealer.getName(), resolveCardInfo(card));
    }

    public String resolvePlayerCard(final Player player) {
        return resolvePlayerCardInfo(player);
    }

    public String resolveDealerHitMessage(final Dealer dealer) {
        final String dealerHitMessage = String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        return String.join("", dealerHitMessage, LINE_SEPARATOR);
    }

    public String resolvePlayerCardWithScore(final Player player) {
        return String.format("%s - 결과: %d", resolvePlayerCardInfo(player), player.getScore());
    }

    public String resolveResult(final CardGameResult cardGameResult) {
        final String resultOfDealer = resolveResultOfDealer(cardGameResult);
        final String resultOfEachPlayer = resolveResultOfEachPlayer(cardGameResult);
        return String.join("", resultOfDealer, LINE_SEPARATOR, resultOfEachPlayer);
    }

    private String resolveResultOfDealer(final CardGameResult cardGameResult) {
        final String prefix = String.join("", LINE_SEPARATOR, "## 최종 승패");
        final String message = String.format("딜러: %d승 %d패", cardGameResult.getDealerWinCount(), cardGameResult.getDealerLoseCount());
        return String.join("", prefix, LINE_SEPARATOR, message);
    }

    private String resolveResultOfEachPlayer(final CardGameResult cardGameResult) {
        return cardGameResult.totalResult()
                .entrySet()
                .stream()
                .map(result -> result.getKey().getName() + ": " + result.getValue().getValue())
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolvePlayerCardInfo(final Player player) {
        final String cardsInfo = player.getCards()
                .stream()
                .map(this::resolveCardInfo)
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", player.getName(), cardsInfo);
    }

    private String resolveCardInfo(final Card card) {
        return CARD_NUMBER_NAME_MAP.get(card.getNumber()) + CARD_SHAPE_NAME_MAP.get(card.getShape());
    }
}
