package blackjack.utils;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.strategy.FixedCardsGenerateStrategy;
import java.util.LinkedList;

public class CardCreationUtil {

    private CardCreationUtil() {
    }

    public static LinkedList<Card> createCardList(Denomination... denominations){

        return new FixedCardsGenerateStrategy(denominations)
                .generate();
    }
}
