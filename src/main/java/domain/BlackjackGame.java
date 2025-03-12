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


  public static BlackjackGame from(final List<String> participantNames) {
    final var deck = Deck.createDecks(NUMBER_OF_DECK);
    final Deck shuffled = deck.shuffle();

    final var participants = Participants.from(participantNames);

    return new BlackjackGame(participants, shuffled);
  }

  public void initialDeal() {
    participants.initialDeal(deck);
  }

  public RoundHistory writeRoundHistory() {
    final Participant dealer = getDealer();
    final List<Participant> players = getPlayers();
    return RoundHistory.of(dealer, players);
  }


  public TrumpCard openDealerFirstCard() {
    final var dealer = participants.getDealer();
    final List<TrumpCard> cards = dealer.getCards();
    return cards.getFirst();
  }

  public void hitByParticipant(final Participant participant) {
    final var card = deck.draw();
    participant.hit(card);
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

  public List<TrumpCard> getCards(Participant participant) {
    return participants.getCards(participant);
  }
}
