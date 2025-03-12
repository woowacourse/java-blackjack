package domain.participant;

import domain.card.Deck;
import domain.card.TrumpCard;
import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Participants {

  private static final int NUMBER_OF_DEALER = 1;

  private final List<Participant> participants;

  public Participants(List<Participant> participants) {
    validateNumberOfDealer(participants);
    this.participants = participants;
  }

  private void validateNumberOfDealer(List<Participant> participants) {
    long currentNumberOfDealer = participants.stream()
        .filter(Participant::isDealer)
        .count();
    if (currentNumberOfDealer != NUMBER_OF_DEALER) {
      throw new BlackjackArgumentException("하나의 게임에 하나의 딜러가 존재해야 합니다.");
    }
  }

  public static Participants from(final List<String> participantNames) {
    final List<Participant> participants = new ArrayList<>();
    participants.add(new Dealer());
    participants.addAll(generatePlayers(participantNames));
    return new Participants(participants);
  }

  private static List<Participant> generatePlayers(final List<String> participantNames) {
    final List<Participant> participants = new ArrayList<>();
    for (final String participantName : participantNames) {
      final Participant player = new Player(participantName);
      if (participants.stream().anyMatch(i -> i.getName().equals(participantName))) {
        throw new BlackjackArgumentException("중복된 닉네임을 가진 플레이어가 포함되어있습니다: " + participantName);
      }
      participants.add(player);
    }
    return participants;
  }

  public void initialDeal(final Deck deck) {
    for (final Participant participant : participants) {
      participant.initialDeal(deck);
    }
  }

  public Participant getDealer() {
    return participants.stream()
        .filter(Participant::isDealer)
        .findFirst()
        .orElseThrow(() -> new BlackjackArgumentException("딜러를 찾을 수 없습니다. 딜러는 반드시 게임에 참가해야 합니다."));
  }

  public List<Participant> getPlayers() {
    final List<Participant> players = participants.stream()
        .filter(participant -> !participant.isDealer())
        .toList();
    if (players.isEmpty()) {
      throw new BlackjackArgumentException("게임 참가자가 없습니다! 게임 설정을 다시 진행해주세요.");
    }
    return players;
  }

  public List<TrumpCard> getCards(final Participant target) {
    final Participant participant = find(target);
    return participant.getCards();
  }

  public List<Participant> getParticipants() {
    return Collections.unmodifiableList(participants);
  }

  public Participant find(final Participant target) {
    return participants.stream()
        .filter(currentParticipant -> currentParticipant.equals(target))
        .findFirst()
        .orElseThrow(() -> new BlackjackArgumentException("등록되지 않은 사용자입니다: " + target.getName()));
  }
}
