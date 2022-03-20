package blackjack.domain.card;

import blackjack.domain.strategy.RandomNumberGenerator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private final List<Card> cardDeck;
    private final CardPickMachine cardPickMachine;

    public Cards() {
        this.cardDeck = Arrays.stream(Card.values())
                .collect(Collectors.toList());
        this.cardPickMachine = new CardPickMachine();
    }

    public Card draw() {
        return cardPickMachine.pickCard(cardDeck, RandomNumberGenerator.getInstance());
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }
}
