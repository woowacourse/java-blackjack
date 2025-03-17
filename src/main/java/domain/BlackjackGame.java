package domain;

import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.YesOrNo;

public class BlackjackGame {
    private static final int INIT_CARD_NUMBER = 2;

    private final Participants participants;
    private final CardDeck cardDeck;

    private BlackjackGame(Participants participants, CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public static BlackjackGame of(List<Player> players, Dealer dealer, CardDeck cardDeck) {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players);
        Participants participants = new Participants(users);
        return new BlackjackGame(participants, cardDeck);
    }

    public void firstHandOutCard() {
        for (int count = 0; count < INIT_CARD_NUMBER; count++) {
            participants.drawFirstCard(cardDeck);
        }
    }

    public User getDealer() {
        return participants.getDealer();
    }

    public Map<String, List<Card>> openFirstPlayersCard() {
        return participants.getPlayersAllCard();
    }

    public List<Card> openFirstDealerCard() {
        User dealer = getDealer();
        return dealer.openInitialCard();
    }

    public void controlTurn(User user, YesOrNo yesOrNo) {
        if (yesOrNo == YesOrNo.YES && user.isDrawable()) {
            user.drawCard(cardDeck.drawCard());
        }
    }

    public int calculateScore(User user) {
        return participants.calculateScore(user);
    }

    public Participants getParticipants() {
        return this.participants;
    }
}
