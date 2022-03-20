package blackjack.domain;

import blackjack.domain.card.CardGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.dto.DistributeResult;
import blackjack.domain.dto.ProfitResult;

import java.util.List;
import java.util.Map;

public class BlackJack {

    private static final int INIT_DISTRIBUTE_COUNT = 2;
    private static final int SCORE_LIMIT = 21;
    private static final String DEALER_NAME = "딜러";

    private final Participants participants;
    private final Deck deck;

    public BlackJack() {
        deck = new Deck(new CardGenerator());
        participants = new Participants();
        participants.addDealer();
    }

    public BlackJack(String[] userNames) {
        this();
        participants.addUsers(userNames);
    }

    public BlackJack(Map<String, Integer> priceByName) {
        this();
        participants.addUsers(priceByName);
    }

    public List<DistributeResult> initDistribute() {
        for (int i = 0; i < INIT_DISTRIBUTE_COUNT; i++) {
            participants.distributeCard(deck);
        }
        return participants.getDistributeResult();
    }

    public List<ProfitResult> calculateProfitResult() {
        return participants.calculateProfitResult();
    }

    public DistributeResult playGameOnePlayer(String playerName) {
        Participant participant = participants.getUserByName(playerName);
        participant.receiveCard(deck.drawCard());
        return new DistributeResult(participant);
    }

    public boolean checkDealerDrawMoreCard() {
        return checkDealerUnderSumStandard() && checkDealerLimit();
    }

    public boolean checkLimit(String playerName) {
        Participant participant = participants.getUserByName(playerName);
        return participant.cardSum() < SCORE_LIMIT;
    }

    public boolean checkDealerUnderSumStandard() {
        Participant participant = participants.getUserByName(DEALER_NAME);
        Dealer dealer = (Dealer) participant;
        return dealer.checkUnderSumStandard();
    }

    public List<DistributeResult> getCardResults() {
        return participants.getDistributeResult();
    }

    public void playGameWithDealer() {
        playGameOnePlayer(DEALER_NAME);
    }

    public boolean checkDealerLimit() {
        return checkLimit(DEALER_NAME);
    }
}

