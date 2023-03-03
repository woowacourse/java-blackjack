package blackjack.view;

import blackjack.domain.Card;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String OPEN_CARD_FORMAT = "%s : %s%n";
    private static final String OPEN_CARD_MESSAGE = "%n%s에게 2장을 나누었습니다.%n";
    private static final String DEALER_HIT_RESULT_MESSAGE = DEALER_NAME + "는 16 이하라 %d장의 카드를 더 받았습니다.%n";

    public static void showOpenCards(Card dealerFirstCard, Map<String, List<Card>> playersCards) {
        Set<String> playerNames = playersCards.keySet();
        System.out.printf(OPEN_CARD_MESSAGE, String.join(DELIMITER, playerNames));
        System.out.printf(OPEN_CARD_FORMAT, DEALER_NAME, toCardName(dealerFirstCard));
        for (String playerName : playerNames) {
            List<Card> playerCard = playersCards.get(playerName);
            showPlayerCard(playerName, playerCard);
        }
    }

    public static void showPlayerCard(String playerName, List<Card> playerCard) {
        System.out.printf(OPEN_CARD_FORMAT, playerName, joinAllCardNames(playerCard));
    }

    private static String toCardName(Card card) {
        return NumberWord.toWord(card.getNumber()) + SymbolWord.toWord(card.getSymbol());
    }

    private static String joinAllCardNames(List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(OutputView::toCardName)
                .collect(Collectors.toList());
        return String.join(DELIMITER, cardNames);
    }

    public static void showDealerHitResult(int hitCount) {
        if (hitCount == 0) {
            return;
        }
        System.out.printf(DEALER_HIT_RESULT_MESSAGE, hitCount);
    }
}
