package domain.user;

public class Player extends User {
    private Name name;

    public Player(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("이름이 비어있습니다.");
        }
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }
}
