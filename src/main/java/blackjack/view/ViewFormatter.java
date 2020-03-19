package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.component.Name;

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

    public static String formatResult(Result result) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(result.getUserName());
        stringBuffer.append(": ");
        stringBuffer.append(result.getProfit());
        return stringBuffer.toString();
    }
}
