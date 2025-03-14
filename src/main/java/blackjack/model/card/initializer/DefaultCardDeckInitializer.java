package blackjack.model.card.initializer;

import blackjack.model.card.BlackJackCard;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class DefaultCardDeckInitializer implements CardDeckInitializer {

    @Override
    public BlackJackCards initialize() {
        return new BlackJackCards(initializeCardDeck());
    }

    private List<BlackJackCard> initializeCardDeck() {
        List<BlackJackCard> blackJackCards = new ArrayList<>();
        Arrays.stream(CardType.values())
                .forEach(cardType -> Arrays.stream(CardNumber.values())
                        .forEach(
                                cardNumber -> blackJackCards.add(new BlackJackCard(cardType, cardNumber))
                        )
                );
        Collections.shuffle(blackJackCards);
        return blackJackCards;
    }
}
