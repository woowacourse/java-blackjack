package domain;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Role;
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


  public Participant<? extends Role> hitByParticipant(
      final Participant<? extends Role> participant) {
    final var card = deck.draw();
    return participants.hit(participant, card);
  }

  public RoundHistory writeRoundHistory() {
    final Map<Role, RoundResult> history = getPlayers().stream()
        .collect(Collectors.toMap(
            Participant::getRole,
            player -> RoundResult.round(player, getDealer())
        ));
    return new RoundHistory(history);
  }

  public TrumpCard openDealerFirstCard() {
    final var dealer = getDealer();
    final var cards = dealer.getCards();
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
