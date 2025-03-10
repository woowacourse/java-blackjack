package domain;

import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

  private static final int NUMBER_OF_DECK = 1;

  private final Participants participants;
  private final Deck deck;

  public BlackjackGame(final Participants participants, final Deck deck) {
    this.participants = participants;
    this.deck = deck;
  }

  public static BlackjackGame generate() {
    var deck = Deck.createDecks(NUMBER_OF_DECK);
    final var participants = new Participants();
    final Deck shuffled = deck.shuffle();
    return new BlackjackGame(participants, shuffled);
  }

  public void addParticipants(final List<String> participantNames) {
    participants.addDealer();
    participants.add(participantNames);
  }

  public void initialDeal() {
    participants.initialDeal(deck);
  }

  public Map<String, Boolean> round() {
    final Map<String, Boolean> roundResult = new HashMap<>();
    final Participant dealer = getDealer();
    for (Participant player : getPlayers()) {
      var name = player.getName();
      var result = player.round(dealer);
      roundResult.put(name, result);
    }

    return roundResult;
  }

  public Participant getDealer() {
    return participants.getDealer();
  }

  public List<Participant> getPlayers() {
    return participants.getPlayers();
  }

  public List<Participant> getParticipants() {
    return participants.getParticipants();
  }
}
