package blackjack.view;

import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.money.Money;
import blackjack.domain.result.CardResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ViewRenderer {

    private static final Map<CardShape, String> CARD_SHAPE_STRING_MAPPER = Map.of(
            CardShape.SPADE, "스페이드",
            CardShape.CLOVER, "클로버",
            CardShape.DIAMOND, "다이아몬드",
            CardShape.HEART, "하트"
    );
    private static final Map<CardNumber, String> CARD_NUMBER_STRING_MAPPER = new EnumMap<>(CardNumber.class);
    private static final String CARD_RESULT_FORMAT = "%s - 결과: %d";

    static {
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.ACE, "A");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.TWO, "2");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.THREE, "3");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.FOUR, "4");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.FIVE, "5");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.SIX, "6");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.SEVEN, "7");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.EIGHT, "8");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.NINE, "9");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.TEN, "10");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.JACK, "J");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.QUEEN, "Q");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.KING, "K");
    }

    private ViewRenderer() {
    }

    public static List<String> renderCardGroup(final CardGroup cardGroup) {
        return cardGroup.getCards().stream()
                .map(card -> CARD_NUMBER_STRING_MAPPER.get(card.getNumber())
                        + CARD_SHAPE_STRING_MAPPER.get(card.getShape()))
                .collect(toUnmodifiableList());
    }

    public static Map<String, String> renderUserNameAndCardResults(
            final Map<Name, CardResult> userNameAndCardResults) {
        final Map<String, String> renderedUserNameAndCardResults = new LinkedHashMap<>();

        userNameAndCardResults
                .forEach((key, value) -> renderedUserNameAndCardResults.put(key.getValue(), renderCardResults(value)));

        return Collections.unmodifiableMap(renderedUserNameAndCardResults);
    }

    private static String renderCardResults(final CardResult cardResult) {
        final List<String> cardNames = renderCardGroup(cardResult.getCards());
        return String.format(CARD_RESULT_FORMAT, String.join(", ", cardNames)
                , cardResult.getScore().getValue());
    }

    public static List<String> renderNames(final List<Name> userNames) {
        return userNames.stream()
                .map(Name::getValue)
                .collect(toUnmodifiableList());
    }

    public static Map<String, Integer> renderUserNameAndProfit(final Map<Name, Money> playerNameAndProfit,
                                                               final Money dealerProfit) {
        final Map<String, Integer> renderedUserNameAndProfit = new LinkedHashMap<>();
        renderedUserNameAndProfit.put(Dealer.DEALER_NAME, dealerProfit.getValue());
        playerNameAndProfit.forEach((name, money) -> renderedUserNameAndProfit.put(name.getValue(), money.getValue()));
        return renderedUserNameAndProfit;
    }
}
