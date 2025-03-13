package domain;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Role;
import java.util.List;
import java.util.Map;

public final class BlackjackManager {

  private static final int NUMBER_OF_DECK = 1;

  private final Deck deck;
  private final Participants participants;

  public BlackjackManager(final Participants participants, final Deck deck) {
    this.participants = participants;
    this.deck = deck;
  }

  public static BlackjackManager from(final Map<String, Money> participantNames) {
    final Deck deck = Deck.createShuffledDecks(NUMBER_OF_DECK);
    final Participants participants = Participants.generateOf(participantNames, deck);
    return new BlackjackManager(participants, deck);
  }


  public Participant<? extends Role> hitByParticipant(
      final Participant<? extends Role> participant) {
    final var card = deck.draw();
    return participants.hit(participant, card);
  }

  public RoundHistory writeRoundHistory() {
    final var dealer = getDealer();
    final List<Participant<? extends Role>> players = getPlayers();
    return RoundHistory.of(dealer, players);
  }

  public TrumpCard openDealerFirstCard() {
    final var dealer = getDealer();
    final List<TrumpCard> cards = dealer.getCards();
    return cards.getFirst();
  }

  public Participant<? extends Role> getDealer() {
    return participants.getDealer();
  }

  public List<Participant<? extends Role>> getPlayers() {
    return participants.getPlayers();
  }

  public List<Participant<? extends Role>> getParticipant() {
    return participants.getAllParticipants();
  }
}
