package blackjack.domain;

import blackjack.domain.card.CardGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participants;

import java.util.List;

public class BlackJack {

    private static final int INIT_DISTRIBUTE_COUNT = 2;

    private final Participants participants;
    private final Deck deck;

    public BlackJack(String[] userNames) {
        deck = new Deck(new CardGenerator());
        participants = new Participants();
        participants.addDealer();
        participants.addUsers(userNames);
    }

    public List<DistributeResult> initDistribute() {
        for (int i = 0; i < INIT_DISTRIBUTE_COUNT; i++) {
            participants.distributeCard(deck);
        }

        return participants.getDistributeResult();
    }
}

