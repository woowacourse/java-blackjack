package domain;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;

public class BlackjackGame {

  private static final int NUMBER_OF_DECK = 1;

  private final Participants participants;
  private final Deck deck;

  private BlackjackGame(final Participants participants, final Deck deck) {
    this.participants = participants;
    this.deck = deck;
  }

  public static BlackjackGame generate() {
    final var deck = Deck.createDecks(NUMBER_OF_DECK);
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

  public RoundHistory writeRoundHistory() {
    final Participant dealer = getDealer();
    final List<Participant> players = getPlayers();
    return RoundHistory.of(dealer, players);
  }

  public TrumpCard getCardForDeal() {
    return deck.draw();
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
