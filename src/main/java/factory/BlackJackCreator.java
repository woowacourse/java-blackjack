package factory;


import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackCreator {

    private BlackJackCreator() {
    }

    public static CardDeck createCardDeck(CardBundle cardBundle) {
        List<Card> allCards = cardBundle.getAllCards();
        List<Card> shuffledAllCards = new ArrayList<>(allCards);
        Collections.shuffle(shuffledAllCards);
        return new CardDeck(shuffledAllCards);
    }
}
