package domain.card;

public enum CardPhrase {
    ACE("A"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    UN_DETERMINE("");
    private final String phrase;

    CardPhrase(final String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }
}
