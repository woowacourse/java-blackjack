package domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import vo.Bet;
import vo.GameResult;
import vo.Name;

public class BlackjackGame {
    private final static Integer FIRST_CARD_DEAL_COUNT = 2;

    private final GameJudge gameJudge = new GameJudge();
    private Participants participants;
    private Dealer dealer;
    private final Deck deck;

    public BlackjackGame() {
        this.deck = new Deck();
    }

    public BlackjackGame(Deck deck) {
        this.deck = deck;
    }

    public void prepare(Map<Name, Bet> bets) {
        this.participants = new Participants(bets);
        this.dealer = new Dealer();
        dealCards();
    }

    public void dealCards() {
        for (int cardCount = 0; cardCount < FIRST_CARD_DEAL_COUNT; cardCount++) {
            participants.dealCards(deck);
            dealer.receiveCard(deck.dealCard());
        }
    }

    public List<String> getParticipantNames() {
        return participants.getParticipantNames();
    }

    public List<User> getUsers() {
        return participants.getUsers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public GameSummary getResult() {
        Map<User, GameResult> userResults = new LinkedHashMap<>();

        for (User user : participants.getUsers()) {
            userResults.put(user,
                    gameJudge.judge(user, dealer));
        }
        return new GameSummary(userResults);
    }

    public void processPlayerDecision(User user) {
        participants.dealCard(deck, user);
    }

    public boolean dealToDealer() {
        if (dealer.determineDealerDealMore()) {
            dealer.receiveCard(deck.dealCard());
            return true;
        }
        return false;
    }
}
