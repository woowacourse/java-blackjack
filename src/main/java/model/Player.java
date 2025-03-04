package model;

public class Player {

  private final Nickname nickname;

  public Player(String name) {
    this.nickname = new Nickname(name);
  }

  public Nickname getNickname() {
    return nickname;
  }

}
