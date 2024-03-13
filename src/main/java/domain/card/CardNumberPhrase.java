package domain.card;

public enum CardNumberPhrase {
    ACE("A"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");
    private final String phrase;

    CardNumberPhrase(final String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }
}
