package domain.participant;

import domain.card.Deck;
import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.List;

public class Participants {

  private final List<Participant> participants;

  public Participants() {
    this.participants = new ArrayList<>();
  }

  public Participants(final List<Participant> participants) {
    this.participants = participants;
  }

  public void addDealer() {
    if (participants.stream().anyMatch(Participant::isDealer)) {
      throw new BlackjackArgumentException("하나의 게임에 복수의 딜러는 존재할 수 없습니다.");
    }

    final Participant dealer = new Dealer();
    participants.add(dealer);
  }


  public void add(final List<String> participantNames) {

    for (String participantName : participantNames) {
      final Participant player = new Player(participantName);
      if (participants.stream().anyMatch(i -> i.getName().equals(participantName))) {
        throw new BlackjackArgumentException("중복된 닉네임을 가진 플레이어가 포함되어있습니다: " + participantName);
      }
      participants.add(player);
    }
  }

  public void initialDeal(final Deck deck) {
    for (Participant participant : participants) {
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

  public List<Participant> getParticipants() {
    return new ArrayList<>(participants);
  }
}
