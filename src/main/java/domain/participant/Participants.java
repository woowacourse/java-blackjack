package domain.participant;

import domain.Bet;
import domain.card.Deck;
import domain.card.TrumpCard;
import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 블랙잭 게임의 모든 참가자(딜러와 플레이어들)를 관리하는 클래스입니다.
 * <p>
 * 내부적으로는 딜러와 플레이어를 구체적인 타입으로 구분하여 관리하지만,
 * <p>
 * 외부로는 다형적 인터페이스(Participant<? extends Role>)를 제공합니다.
 */
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

  public static Participants initialDealOf(final Map<String, Bet> participants, final Deck deck) {
    final var players = participants.entrySet().stream()
        .map(entry -> new Participant<>(Player.generateFrom(entry), deck.drawForInitialDeal()))
        .toList();

    final var roleForDealer = Dealer.generateFrom(participants.values());
    final var dealer = new Participant<>(roleForDealer, deck.drawForInitialDeal());

    return new Participants(dealer, players);
  }

  public Participant<? extends Role> hit(
      final Participant<? extends Role> participant,
      final TrumpCard card
  ) {
    final var targetParticipant = findParticipant(participant);
    return targetParticipant.hit(card);
  }

  private void validate(
      final Participant<Dealer> dealer,
      final List<Participant<Player>> participants
  ) {
    validatePlayerNotEmpty(participants);
    validateDuplicateOfPlayer(participants);
    validateDealer(dealer);
  }

  private void validatePlayerNotEmpty(final List<Participant<Player>> participants) {
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

  private Participant<? extends Role> findParticipant(
      final Participant<? extends Role> participant
  ) {
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

  public Participant<? extends Role> getDealer() {
    return new Participant<>(dealer.getRole(), dealer.getCards());
  }

  public List<Participant<? extends Role>> getPlayers() {
    return Collections.unmodifiableList(participants);
  }
}
