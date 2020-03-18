package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private CardSymbol cardSymbol;
    private CardType cardType;

    public Card(CardSymbol cardSymbol, CardType cardType) {
        if (Objects.isNull(cardSymbol) || Objects.isNull(cardType)) {
            throw new IllegalArgumentException("잘못된 카드 형식입니다.");
        }
        this.cardSymbol = cardSymbol;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return cardSymbol.getCardSymbol() + cardType.getKoreanName();
    }

    public int getNumber() {
        return cardSymbol.getCardNumber();
    }

    public boolean isAce() {
        return cardSymbol.isAce();
    }
}
