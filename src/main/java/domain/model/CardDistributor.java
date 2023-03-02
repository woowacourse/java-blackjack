package domain.model;

import java.util.List;

public class CardDistributor {

    private final CardGenerator cardGenerator;

    public CardDistributor(final CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void giveCard(final Participant participant) {
        participant.addCard(cardGenerator.generate());
    }

    public void giveCard(final List<Participant> participants) {
        participants.forEach(this::giveCard);
    }
}
