package model;

import java.util.List;

public class Players {

  private final List<Player> values;

  private Players(List<Player> values) {
    validateDuplication(values);
    validateNumber(values);
    this.values = values;
  }

  public static Players from(List<String> input) {
    List<Player> inputPlayers = input.stream()
            .map(Player::from)
            .toList();
    return new Players(inputPlayers);
  }

  public List<Player> getPlayers() {
    return values;
  }

  public List<String> getNicknames() {
    return values.stream()
            .map(Participant::getNickname)
            .toList();
  }

  private static void validateDuplication(List<Player> values) {
    if(values.stream().distinct().count() != values.size()) {
      throw new IllegalArgumentException("중복된 플레이어는 등록할 수 없습니다.");
    }
  }

  private static void validateNumber(List<Player> values) {
    if (values.size() < 1 || values.size() > 30) {
      throw new IllegalArgumentException("게임 참가자는 1~30명까지 가능합니다.");
    }
  }
}
