package blackjack.domain;

import blackjack.common.ErrorMessage;
import java.util.List;

public class Player{

    private final String name;
    private final CardHolder cardHolder;

    public Player(String name, CardHolder cardHolder) {
        validName(name);
        this.name = name;
        this.cardHolder = cardHolder;
    }

    public void validName(String name){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException(ErrorMessage.USE_VALID_NAME.getMessage());
        }
    }

    public List<Integer> getPossibleSums() {
        return cardHolder.getPossibleSums();
    }

    public List<Card> getAllCards() {
        return cardHolder.getAllCards();
    }

    public void takeCard(Card newCard) {
        cardHolder.takeCard(newCard);
    }

    public CardHolder getCardHolder() {
        return cardHolder;
    }

    public String getName() {
        return name;
    }
}
