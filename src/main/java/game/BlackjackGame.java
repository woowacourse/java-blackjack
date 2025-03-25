package game;

import card.Card;
import card.CardDeck;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import user.Dealer;
import user.Participant;
import user.Participants;
import user.Player;
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
        List<Participant> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players);
        Participants participants = new Participants(users);
        return new BlackjackGame(participants, cardDeck);
    }

    public void firstHandOutCard() {
        for (int count = 0; count < INIT_CARD_NUMBER; count++) {
            participants.drawCardAllParticipant(cardDeck);
        }
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public Map<String, List<Card>> openFirstPlayersCard() {
        return participants.getPlayersAllCard();
    }

    public List<Card> openFirstDealerCard() {
        Participant dealer = getDealer();
        return dealer.openInitialCard();
    }

    public void controlTurn(Participant participant, YesOrNo yesOrNo) {
        if (yesOrNo == YesOrNo.YES && participant.isDrawable()) {
            participant.drawCard(cardDeck.drawCard());
        }
    }

    public int calculateScore(Participant participant) {
        return participants.calculateScore(participant);
    }

    public Participants getParticipants() {
        return this.participants;
    }

    public Map<Participant, GameResult> calculatePlayerScore() {
        return participants.calculateGameResults();
    }

    public Map<Participant, Double> calculateRewards(Map<Participant, GameResult> gameResult, Dealer dealer) {
        Map<Participant, Double> rewards = new LinkedHashMap<>();

        for (Entry<Participant, GameResult> userGameResult : gameResult.entrySet()) {
            Participant participant = userGameResult.getKey();
            GameResult result = userGameResult.getValue();
            rewards.put(participant, result.calculateReward(participant, dealer));
        }

        rewards.put(dealer, dealer.getBetMoney());
        return rewards;
    }
}
