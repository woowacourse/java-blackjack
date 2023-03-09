package domain.game;

import domain.CardShuffler;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantMoney;
import domain.participant.Player;

import java.util.Map;

public final class GameManager {

    private final Deck deck;
    private final ParticipantMoney participantMoney;

    private GameManager(final Deck deck, final ParticipantMoney participantMoney) {
        this.deck = deck;
        this.participantMoney = participantMoney;
    }

    public static GameManager create(final Dealer dealer,
                                     final Map<Player, BettingMoney> playerInfo,
                                     final CardShuffler cardShuffler) {
        return new GameManager(Deck.create(cardShuffler), ParticipantMoney.create(dealer, playerInfo));
    }

    public void handFirstCards() {
        participantMoney.getParticipantMoney()
                .keySet()
                .forEach(participant -> participant.addCard(deck.draw(), deck.draw()));
    }

    public void handCard(final Participant participant) {
        participant.addCard(deck.draw());
    }
}
