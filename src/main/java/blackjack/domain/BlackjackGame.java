package blackjack.domain;

import java.util.*;

public class BlackjackGame {

    private static final int FIRST_HIT_COUNT = 2;

    private final Participants participants;
    private final Cards cards;

    private BlackjackGame(Participants participants, Cards cards) {
        this.participants = participants;
        this.cards = cards;
    }

    public static BlackjackGame of(List<String> playerName, Cards cards) {
        return new BlackjackGame(Participants.from(playerName, new Dealer()), cards);
    }

    public BlackjackGameResult generatePlayersResult(BlackJackReferee referee) {
        for (Player player : participants.findPlayers()) {
            referee.createResult(participants.findDealer(), player);
        }
        return new BlackjackGameResult(referee.getPlayerWinningResult());
    }

    public void getTwoHitCards(final Participant participant) {
        for (int count = 0; count < FIRST_HIT_COUNT; count++) {
            participant.hit(cards.pick());
        }
    }

    public void hitDealerCard() {
        Dealer dealer = participants.findDealer();
        while (dealer.decideHit()) {
            dealer.hit(cards.pick());
        }
    }

    public void hitPlayerCard(Player player) {
        player.hit(cards.pick());
    }

    public List<Player> findPlayers() {
        return Collections.unmodifiableList(participants.findPlayers());
    }

    public Dealer findDealer() {
       return participants.findDealer();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants.getParticipants());
    }

    public List<String> getParticipantsName() {
        return Collections.unmodifiableList(participants.getParticipantsName());
    }
}
