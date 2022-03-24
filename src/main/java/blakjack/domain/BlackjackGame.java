package blakjack.domain;

import blakjack.domain.card.CardDeck;
import blakjack.domain.participant.Participant;
import blakjack.domain.participant.Participants;
import blakjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public final class BlackjackGame {

    private final CardDeck cardDeck = new CardDeck();
    private final Participants participants;

    public BlackjackGame(final List<PlayerName> playerNames, final List<Chip> chips) {
        this.participants = new Participants(generatePlayers(playerNames, chips));
    }

    private static List<Participant> generatePlayers(final List<PlayerName> playerNames, final List<Chip> chips) {
        final List<Participant> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), chips.get(i)));
        }
        return players;
    }

    public void initCards() {
        participants.initCards(cardDeck);
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public String getDealerName() {
        return participants.getDealerName();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public void hitPlayer(final String name) {
        participants.hitPlayer(name, cardDeck.draw());
    }

    public Participant findPlayerByName(final String name) {
        return participants.findPlayerByName(name);
    }

    public void stay(final String name) {
        participants.stay(name);
    }

    public void hitDealer() {
        participants.hitDealer(cardDeck.draw());
    }
}
