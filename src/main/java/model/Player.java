package model;

public class Player extends Participant {

    public Player(PlayerName name) {
        super(name);
        validate(name);
    }

    private void validate(PlayerName name) {
        if(name.get().equals("딜러")) {
            throw new IllegalArgumentException("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player other = (Player) o;
        return this.getResult().name().equals(other.getResult().name());
    }

}
