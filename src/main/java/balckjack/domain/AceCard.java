package balckjack.domain;

class AceCard extends Card {

    private static final int DEFAULT_ACE_VALUE = 11;

    public AceCard(Pattern pattern) {
        super(pattern, "A");
    }

    @Override
    protected int getValue() {
        return DEFAULT_ACE_VALUE;
    }
}
