package blackJack.domain;

import blackJack.domain.result.YesOrNo;
import java.util.List;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;

public class BlackJackGame {

    public static final int BLACKJACK_NUMBER = 21;

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(Participants participants) {
        this.deck = Deck.create();
        this.participants = participants;
    }

    public void defaultDistributeCards() {
        participants.distributeCards(deck);
    }

    public void distributeCard(Participant participant) {
        participant.receiveCard(deck.getCard());
    }

    public boolean isAvailableDistributeCard(Participant participant) {
        return participant.isAvailableHit();
    }

    public boolean isApproveDrawCard(YesOrNo value) {
        return YesOrNo.hasYes(value);
    }

    public Participants getParticipants() {
        return participants;
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
