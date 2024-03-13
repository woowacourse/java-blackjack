package view.dto.card;

public enum CardPhrase {
    ACE("A"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");
    private final String phrase;

    CardPhrase(final String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }
}
