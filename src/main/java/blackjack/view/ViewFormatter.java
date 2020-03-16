package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.Name;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewFormatter {
    public static String formatCard(Card card) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(card.getCardNumber());
        stringBuffer.append(card.getCardFigure());
        return stringBuffer.toString();
    }

    public static String formatCards(List<Card> cards) {
        List<String> formattedCards = cards.stream()
                .map(ViewFormatter::formatCard)
                .collect(Collectors.toList());
        return String.join(",", formattedCards);
    }

    public static String formatDealerResult(Map<ResultType, Integer> result) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("딜러");
        stringBuffer.append(": ");
        for (ResultType resultType : ResultType.values()) {
            stringBuffer.append(result.get(resultType));
            stringBuffer.append(resultType.getMessage());
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }

    public static String formatPlayerResult(Name name, String resultType) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name);
        stringBuffer.append(": ");
        stringBuffer.append(resultType);
        return stringBuffer.toString();
    }
}
