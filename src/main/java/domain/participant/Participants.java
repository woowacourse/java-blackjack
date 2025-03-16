package domain.participant;

import domain.Bet;
import domain.card.Deck;
import domain.card.TrumpCard;
import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Participants {

  private final Participant dealer;
  private final List<Participant> participants;

  public Participants(
      final Participant dealer,
      final List<Participant> participants
  ) {
    validate(dealer, participants);
    this.dealer = dealer;
    this.participants = participants;
  }

  public static Participants initialDealOf(final Map<String, Bet> participants, final Deck deck) {
    final var players = participants.entrySet().stream()
        .map(entry -> new Participant(Player.generateFrom(entry), deck.drawForInitialDeal()))
        .toList();

    final var roleForDealer = Dealer.generateByTotalBet(participants.values());
    final var dealer = new Participant(roleForDealer, deck.drawForInitialDeal());

    return new Participants(dealer, players);
  }

  public Participant hit(
      final Participant participant,
      final TrumpCard card
  ) {
    final var targetParticipant = findParticipant(participant);
    return targetParticipant.hit(card);
  }

  private void validate(
      final Participant dealer,
      final List<Participant> participants
  ) {
    validatePlayerNotEmpty(participants);
    validateDuplicateOfPlayer(participants);
    validateDealer(dealer);
  }

  private void validatePlayerNotEmpty(final List<Participant> participants) {
    if (participants.isEmpty()) {
      throw new BlackjackArgumentException("게임 참가자가 없습니다! 게임 설정을 다시 진행해주세요.");
    }
  }

  private void validateDuplicateOfPlayer(final List<Participant> participants) {
    final var distinctCount = participants.stream()
        .map(Participant::getName)
        .distinct()
        .count();
    if (participants.size() != distinctCount) {
      throw new BlackjackArgumentException("중복된 닉네임을 가진 플레이어가 포함되어있습니다");
    }
  }

  private void validateDealer(final Participant dealer) {
    if (dealer == null) {
      throw new BlackjackArgumentException("딜러를 찾을 수 없습니다. 딜러는 반드시 게임에 참가해야 합니다.");
    }
  }

  private Participant findParticipant(
      final Participant participant
  ) {
    final var allParticipants = getAllParticipants();

    return allParticipants.stream()
        .filter(p -> p.equals(participant))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("참가자를 찾을 수 없습니다."));
  }

  public List<Participant> getAllParticipants() {
    final List<Participant> allParticipants = new ArrayList<>();
    allParticipants.add(dealer);
    allParticipants.addAll(participants);
    return Collections.unmodifiableList(allParticipants);
  }

  public Participant getDealer() {
    return new Participant(dealer.getRole(), dealer.getCards());
  }

  public List<Participant> getPlayers() {
    return Collections.unmodifiableList(participants);
  }
}
