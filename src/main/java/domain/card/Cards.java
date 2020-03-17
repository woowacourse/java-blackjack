package domain.card;

import java.util.*;

public class Cards {
    private static final int FIRST = 0;

    private final List<Card> cardsDeck;

    public Cards() {
        cardsDeck = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            addCard(cardNumber);
        }
        validateDuplicatedCard();
        Collections.shuffle(cardsDeck);
    }

    private void addCard(CardNumber cardNumber) {
        for (CardSuitSymbol cardSuitSymbol : CardSuitSymbol.values()) {
            cardsDeck.add(new Card(cardNumber, cardSuitSymbol));
        }
    }

    public List<Card> giveTwoCardStartGame(){

        return new ArrayList<>(Arrays.asList(giveCard(),giveCard()));
    }

    public Card giveCard() {
        validateCardsDeck();
        return cardsDeck.remove(FIRST);
    }

    private void validateCardsDeck() {
        if (this.cardsDeck.isEmpty()) {
            throw new IllegalArgumentException("카드를 모두 소모하였습니다. 프로그램이 종료됩니다.");
        }
    }

    private void validateDuplicatedCard(){
        Set<Card> cardsDeck = new HashSet<>(this.cardsDeck);
        if(cardsDeck.size() != this.cardsDeck.size()){
            throw new IllegalArgumentException("카드가 중복되게 만들어졌습니다.");
        }
    }
}
