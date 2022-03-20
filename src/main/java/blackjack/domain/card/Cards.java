package blackjack.domain.card;

import blackjack.domain.strategy.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {
    private final List<Card1> cardDeck;
    private final CardPickMachine cardPickMachine;

    public Cards() {
        this.cardDeck = new ArrayList<>();
        this.cardPickMachine = new CardPickMachine();
        initCards();
    }

    private void initCards() {
           Arrays.stream(Symbol.values())
                   .forEach(this::addCardsBySymbol);
    }

    private void addCardsBySymbol(Symbol symbol) {
       Arrays.stream(Number.values())
               .forEach(number -> cardDeck.add(Card1.of(symbol, number)));
    }

    // TODO: Card1 return 으로 바꾸기
    public Card draw() {
        return cardPickMachine.pickCard(RandomNumberGenerator.getInstance().generateNumber());
    }

    public List<Card1> getCardDeck() {
        return cardDeck;
    }
}
