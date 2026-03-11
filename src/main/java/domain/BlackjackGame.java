package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import vo.GameResult;

public class BlackjackGame {
    private final static Integer FIRST_CARD_DEAL_COUNT = 2;

    private final GameJudge gameJudge = new GameJudge();
    private Participants participants;
    private Deck deck;

    public void prepare(String participantsName) {
        participants = new Participants(participantsName);
        makeDeck();
        dealCards();
    }

    public void makeDeck() {
        deck = new Deck();
    }

    void setDeck(Deck deck) {
        this.deck = deck;
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

    public GameSummary getResult() {
        Map<String, GameResult> userResults = new LinkedHashMap<>();
        List<User> users = participants.getUsers();
        Dealer dealer = participants.getDealer();

        for (User user : users) {
            userResults.put(user.getName(), gameJudge.judge(dealer.getScore(), user.getScore()));
        }
        return new GameSummary(userResults);
    }
}
