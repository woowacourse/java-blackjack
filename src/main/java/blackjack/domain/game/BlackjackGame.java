package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.*;

public class BlackjackGame {

    private static final int FIRST_HIT_COUNT = 2;

    private final Participants participants;
    private final Deck deck;

    private BlackjackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame of(List<String> playersName,List<String> playersAmount, Deck deck) {
        return new BlackjackGame(Participants.from(playersName, playersAmount, new Dealer()), deck);
    }

    public BlackjackGameResult generatePlayersResult(BlackJackReferee referee) {
        for (Player player : participants.findPlayers()) {
            referee.createResult(participants.findDealer(), player);
        }
        return new BlackjackGameResult(referee.getPlayerWinningResult());
    }

    public void getTwoHitCards(final Participant participant) {
        for (int count = 0; count < FIRST_HIT_COUNT; count++) {
            participant.hit(deck.pick());
        }
    }

    public void hitDealerCard() {
        Dealer dealer = participants.findDealer();
        while (dealer.decideHit()) {
            dealer.hit(deck.pick());
        }
    }

    public void hitPlayerCard(Player player) {
        player.hit(deck.pick());
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
