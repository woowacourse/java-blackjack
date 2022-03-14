package blackjack.domain;

import blackjack.domain.card.CardGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.DistributeResult;
import blackjack.domain.result.UserResult;

import java.util.List;

public class BlackJack {

    private static final int INIT_DISTRIBUTE_COUNT = 2;
    public static final int SCORE_LIMIT = 21;
    public static final String DEALER_NAME = "딜러";

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

    public DistributeResult playGameOnePlayer(String playerName) {
        Participant participant = participants.getUserByName(playerName);
        participant.receiveCard(deck.drawCard());
        return new DistributeResult(participant);
    }

    public boolean checkLimit(String playerName) {
        Participant participant = participants.getUserByName(playerName);
        return participant.getCardSum() < SCORE_LIMIT;
    }

    public boolean checkDealerUnderSumStandard() {
        Participant participant = participants.getUserByName(DEALER_NAME);
        Dealer dealer = (Dealer) participant;
        return dealer.checkUnderSumStandard();
    }

    public List<DistributeResult> getCardResults() {
        return participants.getDistributeResult();
    }

    public DealerResult calculateDealerResult() {
        return new DealerResult(participants);
    }

    public List<UserResult> calculateUserResult() {
        return participants.getUserResults();
    }
}

