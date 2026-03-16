package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;

public class CardDistributor {

    private final RandomCardPicker randomCardPicker;

    public CardDistributor(RandomCardPicker randomCardPicker) {
        this.randomCardPicker = randomCardPicker;
    }

    public void distributeCardsToParticipant(Participant participant, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            Card card = randomCardPicker.drawCard();
            participant.receiveOneCard(card);
        }
    }

    public void distributeCardsToDealerUntilScoreAtLeast(Dealer dealer) {
        while (!dealer.isDealerDone()) {
            distributeCardsToParticipant(dealer, 1);
        }
    }
}
