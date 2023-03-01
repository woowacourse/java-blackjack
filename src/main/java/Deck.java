import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> deck = new ArrayList<>();

    public Deck() {
        for (Symbol symbol: Symbol.values()){
            addCardsWithSymbolOf(symbol);
        }
        Collections.shuffle(deck);
    }

    private void addCardsWithSymbolOf(Symbol symbol){
        for (CardNumber cardNumber: CardNumber.values()){
            deck.add(new Card(symbol, cardNumber));
        }
    }

    public Card draw() {
        try {
            return this.deck.remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("덱이 비었습니다.");
        }
    }
}
