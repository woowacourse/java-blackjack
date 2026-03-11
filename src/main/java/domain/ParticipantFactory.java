package domain;

public class ParticipantFactory {

    private final DrawStrategy drawStrategy;

    private ParticipantFactory(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    public static ParticipantFactory basedOn(DrawStrategy drawStrategy) {
        return new ParticipantFactory(drawStrategy);
    }

    public Participants onlyDealer() {
        return Participants.onlyDealer(drawStrategy);
    }
}
