package domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import vo.GameResult;

public class BlackjackGame {
    private final static Integer FIRST_CARD_DEAL_COUNT = 2;

    private final GameJudge gameJudge = new GameJudge();
    private Participants participants;
    protected Deck deck;

    public void prepare(String participantsName) {
        participants = new Participants(participantsName);
        makeDeck();
        dealCards();
    }

    public void makeDeck() {
        deck = new Deck();
    }

    public void dealCards() {
        for (int cardCount = 0; cardCount < FIRST_CARD_DEAL_COUNT; cardCount++) {
            participants.dealCards(deck);
        }
    }

    public List<String> getParticipantNames() {
        return participants.getParticipantNames();
    }

    public List<User> getUsers() {
        return participants.getUsers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public GameSummary getResult() {
        Map<User, GameResult> userResults = new LinkedHashMap<>();
        Map<User, BigDecimal> betAmounts = new LinkedHashMap<>();
        int dealerScore = participants.getDealerScore();
        boolean isDealerBlackjack = participants.isDealerBlackjack();

        for (User user : participants.getUsers()) {
            userResults.put(user,
                    gameJudge.judge(dealerScore, user.getScore(), isDealerBlackjack, user.isBlackjack()));
            betAmounts.put(user, user.getBetAmount());
        }
        return new GameSummary(userResults, betAmounts);
    }

    public void placeBet(User user, String betAmount) {
        user.placeBet(betAmount);
    }

    public void processPlayerDecision(User user) {
        participants.dealCard(deck, user);
    }

    public boolean dealToDealer() {
        if (participants.determineDealerDealMore()) {
            participants.dealCardToDealer(deck.dealCard());
            return true;
        }
        return false;
    }
}
