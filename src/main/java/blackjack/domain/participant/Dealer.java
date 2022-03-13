package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final int MIN_SCORE_STANDARD = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean checkUnderScoreStandard() {
        return holdingCard.cardSum() <= MIN_SCORE_STANDARD;
    }

    public ParticipantDto getDealerInfoWithoutHiddenCard() {
        return ParticipantDto.of(name, holdingCard.getCards().subList(1, holdingCard.getCards().size()));
    }

    public ParticipantDto getDealerInfoWithScore() {
        return ParticipantDto.of(name, holdingCard.getCards(), holdingCard.cardSum());
    }
}
