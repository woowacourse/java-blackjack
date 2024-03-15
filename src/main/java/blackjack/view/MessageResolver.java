package blackjack.view;

import blackjack.domain.betting.Profit;
import blackjack.domain.betting.ProfitDetails;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
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

    public String resolveInitialHandOfEachPlayer(Dealer dealer, List<Player> players) {
        String initialDistributionMessage = resolveInitialDistributionMessage(dealer, players);
        String dealerCardMessage = resolveDealerCard(dealer);
        String playersCardMessage = players.stream()
                .map(this::resolvePlayerCard)
                .collect(Collectors.joining(LINE_SEPARATOR));
        return String.join(LINE_SEPARATOR, initialDistributionMessage, dealerCardMessage, playersCardMessage);
    }

    private String resolveInitialDistributionMessage(Dealer dealer, List<Player> players) {
        String playerNames = resolvePlayerNames(players);
        String message = String.format("%s와 %s에게 2장을 나누었습니다.", resolveName(dealer), playerNames);
        return String.join("", LINE_SEPARATOR, message);
    }

    private String resolvePlayerNames(List<Player> players) {
        return players.stream()
                .map(this::resolveName)
                .collect(Collectors.joining(PLAYERS_NAME_DELIMITER));
    }

    private String resolveDealerCard(Dealer dealer) {
        Card card = dealer.getFirstCard();
        return String.join(": ", resolveName(dealer), resolveCardInfo(card));
    }

    public String resolvePlayerCard(Player player) {
        return resolvePlayerCardInfo(player);
    }

    public String resolveDealerHitMessage(Dealer dealer) {
        String dealerHitMessage = String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", resolveName(dealer));
        return String.join("", LINE_SEPARATOR, dealerHitMessage, LINE_SEPARATOR);
    }

    public String resolvePlayerCardWithScore(Player player) {
        return String.format("%s - 결과: %d", resolvePlayerCardInfo(player), player.getScore());
    }

    // TODO: 딜러라는 이름의 의존성
    public String resolvePlayersProfitDetail(ProfitDetails profits) {
        String prefixMessage = LINE_SEPARATOR + "## 최종 수익";
        String dealerProfitDetailsMessage = resolvePlayerProfitDetail(new Name("딜러"), profits.getDealerProfit());
        String playersProfitDetailsMessage = profits.details().entrySet().stream()
                .map(nameAndProfit -> resolvePlayerProfitDetail(extractName(nameAndProfit), extractProfit(nameAndProfit)))
                .collect(Collectors.joining(LINE_SEPARATOR));
        return String.join(LINE_SEPARATOR, prefixMessage, dealerProfitDetailsMessage, playersProfitDetailsMessage);
    }

    private String resolvePlayerCardInfo(Player player) {
        String cardsInfo = player.getCards()
                .stream()
                .map(this::resolveCardInfo)
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", resolveName(player), cardsInfo);
    }

    private String resolvePlayerProfitDetail(Name name, Profit profit) {
        return String.join("", resolveName(name), ": ", resolveProfit(profit));
    }

    private String resolveCardInfo(Card card) {
        return CARD_NUMBER_NAME_MAP.get(card.getNumber()) + CARD_SHAPE_NAME_MAP.get(card.getShape());
    }

    private Name extractName(final Map.Entry<Name, Profit> nameAndProfit) {
        return nameAndProfit.getKey();
    }

    private Profit extractProfit(final Map.Entry<Name, Profit> nameAndProfit) {
        return nameAndProfit.getValue();
    }

    private String resolveName(Name name) {
        return name.value();
    }

    private String resolveName(Player player) {
        return player.getName().value();
    }

    private String resolveProfit(Profit profit) {
        return String.valueOf(profit.value());
    }
}
