package domain;

import java.util.EnumMap;
import java.util.HashMap;
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

    public Map<String, GameResult> getUserResults() {
        Map<String, GameResult> userResults = new HashMap<>();
        List<User> users = participants.getUsers();
        Dealer dealer = participants.getDealer();

        for (User user : users) {
            userResults.put(user.getName(), gameJudge.judge(dealer.getScore(), user.getScore()));
        }
        return userResults;
    }

    public EnumMap<GameResult, Integer> getDealerResults() {
        EnumMap<GameResult, Integer> dealerResults = initEnumMap();
        List<User> users = participants.getUsers();
        Dealer dealer = participants.getDealer();

        for (User user : users) {
            GameResult dealerResult = gameJudge.judge(dealer.getScore(), user.getScore()).opposite();
            dealerResults.replace(dealerResult, dealerResults.get(dealerResult) + 1);
        }
        return dealerResults;
    }

    private EnumMap<GameResult, Integer> initEnumMap() {
        EnumMap<GameResult, Integer> dealerScore = new EnumMap<>(GameResult.class);
        for (GameResult result : GameResult.values()) {
            dealerScore.put(result, 0);
        }
        return dealerScore;
    }
}
