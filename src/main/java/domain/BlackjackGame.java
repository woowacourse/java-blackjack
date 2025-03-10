package domain;

import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final int MIN_PEOPLE_WITHOUT_DEALER = 1;
    private final int MAX_PEOPLE_WITHOUT_DEALER = 7;
    private final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 1-7명만 이용하실 수 있습니다.";

    private final BlackjackParticipants participants;
    private final BlackjackDeck deck;

    public BlackjackGame(List<String> names, BlackjackDeck deck) {
        validatePlayerSize(names.size());
        this.deck = deck;
        this.participants = new BlackjackParticipants(names, new Dealer());
        initiateGame();
    }

    private void validatePlayerSize(int playerSize) {
        if (playerSize < MIN_PEOPLE_WITHOUT_DEALER || playerSize > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new IllegalArgumentException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
    }

    private void initiateGame() {
        for (String name : participants.getPlayerNames()) {
            dealCard(name);
            dealCard(name);
        }
        dealDealerCard();
        dealDealerCard();
    }

    public List<TrumpCard> playerCards(String name) {
        return participants.participantCards(name);
    }

    public TrumpCard dealerCardFirst() {
        return dealerCards().getFirst();
    }

    public List<TrumpCard> dealerCards() {
        String dealerName = participants.dealerName();
        return participants.participantCards(dealerName);
    }

    public String dealerName() {
        return participants.dealerName();
    }

    public List<String> playerNames() {
        return participants.getPlayerNames();
    }

    public void dealCard(String name) {
        participants.addCard(name, deck.drawCard());
    }

    private void dealDealerCard() {
        String dealerName = participants.dealerName();
        participants.addCard(dealerName, deck.drawCard());
    }

    public boolean isBust(String name) {
        return participants.isBust(name);
    }

    public void dealerHit() {
        if (dealerDrawable()) {
            String dealerName = participants.dealerName();
            participants.addCard(dealerName, deck.drawCard());
        }
    }

    public boolean dealerDrawable() {
        String dealerName = participants.dealerName();
        return participants.isDrawable(dealerName);
    }

    public List<BlackjackResult> currentPlayerBlackjackResult() {
        return participants.calculatePlayerResults();
    }

    public BlackjackResult currentDealerBlackjackResult() {
        String dealerName = participants.dealerName();
        return participants.calculateResult(dealerName);
    }

    public DealerWinStatus getDealerWinStatus() {
        return BlackjackResultEvaluator.calculateDealerWinStatus(participants);
    }

    public Map<String, WinStatus> getPlayerWinStatuses() {
        return BlackjackResultEvaluator.calculateWinStatuses(participants);
    }

}
