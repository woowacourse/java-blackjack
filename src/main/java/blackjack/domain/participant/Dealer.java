package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int MIN_SCORE_STANDARD = 16;
    private static final int WITHOUT_HIDDEN_CARD_INDEX = 1;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean checkUnderScoreStandard() {
        return holdingCard.cardSum() <= MIN_SCORE_STANDARD;
    }

    public ParticipantDto getDealerInfoWithoutHiddenCard() {
        return ParticipantDto.of(name, holdingCard.getCards().subList(WITHOUT_HIDDEN_CARD_INDEX, holdingCard.getCards().size()));
    }

    public ParticipantDto getDealerInfoWithScore() {
        return ParticipantDto.of(name, holdingCard.getCards(), holdingCard.cardSum());
    }
}
