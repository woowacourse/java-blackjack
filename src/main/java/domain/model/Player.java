package domain.model;

public class Player extends Person {

    private final String name;
    private PlayerStatus playerStatus = PlayerStatus.NONE;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void changeStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }
}