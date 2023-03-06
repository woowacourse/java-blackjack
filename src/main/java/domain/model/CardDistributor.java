package domain.model;

import java.util.List;
import java.util.stream.IntStream;

public class CardDistributor {

    private static final int INIT_DEAL_COUNT = 2;
    private final CardGenerator cardGenerator;

    public CardDistributor(final CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void giveCard(final Participant participant) {
        participant.addCard(cardGenerator.generate());
    }

    public void giveInitCards(final Dealer dealer, final List<Participant> participants) {
        IntStream.range(0, INIT_DEAL_COUNT)
            .forEach(i -> {
                giveCard(dealer);
                participants.forEach(this::giveCard);
            });
    }
}
