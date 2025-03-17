package domain;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class BlackjackGame {

  private static final int NUMBER_OF_DECK = 1;

  private final Deck deck;
  private final Participants participants;

  public BlackjackGame(final Participants participants, final Deck deck) {
    this.participants = participants;
    this.deck = deck;
  }

  public static BlackjackGame from(final Map<String, Bet> participantNames) {
    final var deck = Deck.createShuffledDecks(NUMBER_OF_DECK);
    final var participants = Participants.initialDealOf(participantNames, deck);
    return new BlackjackGame(participants, deck);
  }


  public Participant hitByParticipant(final Participant participant) {
    final var card = deck.draw();
    return participants.hit(participant, card);
  }

  public TrumpCard openDealerFirstCard() {
    final var dealer = getDealer();
    final var cards = dealer.getCards();
    return cards.getFirst();
  }

  public List<Role> calculateAllocatedEachRoles() {
    final var roundHistory = writeRoundHistory();
    return roundHistory.allocate();
  }

  public Bet getAllocatedTotalDifference(final List<Role> allocated) {
    final var pastBetTotal = getDealer().getBet();
    return pastBetTotal.seekAllocationTotalDifference(allocated);
  }


  private RoundHistory writeRoundHistory() {
    final Map<Role, RoundResult> history = getPlayers().stream()
        .collect(Collectors.toMap(
            Participant::getRole,
            player -> RoundResult.round(player, getDealer())
        ));
    return new RoundHistory(history);
  }

  public Participant getDealer() {
    return participants.getDealer();
  }

  public List<Participant> getPlayers() {
    return participants.getPlayers();
  }

  private List<Participant> getParticipant() {
    return participants.getAllParticipants();
  }

  public List<ParticipantHandResult> getParticipantsHandResult() {
    final List<ParticipantHandResult> handResults = new ArrayList<>();
    for (final var participant : getParticipant()) {
      final var name = participant.getName();
      final var score = participant.calculateScore();
      final var cards = participant.getCards();
      handResults.add(new ParticipantHandResult(name, score.value(), cards));
    }
    return handResults;
  }
}
