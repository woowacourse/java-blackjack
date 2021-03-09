package blackjack.domain;

import blackjack.domain.participants.Participant;

public class CardDistributorForTest {
    private final CardDistributor cardDistributor;

    private CardDistributorForTest(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public static CardDistributorForTest valueOf(CardDistributor cardDistributor) {
        return new CardDistributorForTest(cardDistributor);
    }

    public void distributeCardsTo(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            cardDistributor.distributeCardTo(participant);
        }
    }
}
