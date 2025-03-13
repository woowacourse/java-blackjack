package domain.participant;

import domain.Money;
import domain.card.Deck;
import domain.card.TrumpCard;
import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Participants {

  private final Participant<Dealer> dealer;
  private final List<Participant<Player>> participants;

  public Participants(
      final Participant<Dealer> dealer,
      final List<Participant<Player>> participants
  ) {
    validate(dealer, participants);
    this.dealer = dealer;
    this.participants = participants;
  }

  private void validate(
      final Participant<Dealer> dealer,
      final List<Participant<Player>> participants
  ) {
    validatePlayerNotEmpty(participants);
    validateDuplicateOfPlayer(participants);
    validateDealer(dealer);
  }

  private static void validatePlayerNotEmpty(final List<Participant<Player>> participants) {
    if (participants.isEmpty()) {
      throw new BlackjackArgumentException("게임 참가자가 없습니다! 게임 설정을 다시 진행해주세요.");
    }
  }

  private void validateDuplicateOfPlayer(final List<Participant<Player>> participants) {
    final var distinctCount = participants.stream()
        .map(Participant::getName)
        .distinct()
        .count();
    if (participants.size() != distinctCount) {
      throw new BlackjackArgumentException("중복된 닉네임을 가진 플레이어가 포함되어있습니다");
    }
  }

  private void validateDealer(final Participant<Dealer> dealer) {
    if (dealer == null) {
      throw new BlackjackArgumentException("딜러를 찾을 수 없습니다. 딜러는 반드시 게임에 참가해야 합니다.");
    }
  }

  public static Participants generateOf(final Map<String, Money> participants, final Deck deck) {
    List<Participant<Player>> players = participants.entrySet().stream()
        .map(entry -> new Participant<>(Player.generateFrom(entry)))
        .toList();

    final var roleForDealer = Dealer.generateFrom(participants.values());
    Participant<Dealer> dealer = new Participant<>(roleForDealer);

    final var initialDealer = dealer.initialDeal(deck);
    final var initialParticipants = initialDealForPlayers(players, deck);
    return new Participants(initialDealer, initialParticipants);
  }

  private static List<Participant<Player>> initialDealForPlayers(
      final List<Participant<Player>> players,
      final Deck deck
  ) {
    final List<Participant<Player>> newPlayers = new ArrayList<>();
    for (final var player : players) {
      newPlayers.add(player.initialDeal(deck));
    }
    return newPlayers;
  }

  public Participant<? extends Role> getDealer() {
    return new Participant<>(dealer.getRole(), dealer.getCards());
  }

  public List<Participant<? extends Role>> getPlayers() {
    return Collections.unmodifiableList(participants);
  }

  public Participant<? extends Role> hit(final Participant<? extends Role> participant,
      final TrumpCard card) {
    final var targetParticipant = findParticipant(participant);
    return targetParticipant.hit(card);
  }

  private Participant<? extends Role> findParticipant(
      final Participant<? extends Role> participant) {
    final var allParticipants = getAllParticipants();

    return allParticipants.stream()
        .filter(p -> p.equals(participant))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("참가자를 찾을 수 없습니다."));
  }

  public List<Participant<? extends Role>> getAllParticipants() {
    final List<Participant<? extends Role>> allParticipants = new ArrayList<>();
    allParticipants.add(dealer);
    allParticipants.addAll(participants);
    return Collections.unmodifiableList(allParticipants);
  }
}
