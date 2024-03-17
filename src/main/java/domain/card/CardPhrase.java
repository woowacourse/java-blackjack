package domain.card;

public enum CardPhrase {
    ACE("A", CardNumber.ACE),
    JACK("J", CardNumber.JACK),
    QUEEN("Q", CardNumber.QUEEN),
    KING("K", CardNumber.KING);
    private final String phrase;
    private final CardNumber cardNumber;

    CardPhrase(String phrase, CardNumber cardNumber) {
        this.phrase = phrase;
        this.cardNumber = cardNumber;
    }

    public String getPhrase() {
        return phrase;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
