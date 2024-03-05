public class User {
    private final UserDeck userDeck;
    private final Name name;

    public User(Name name) {
        this.userDeck = new UserDeck();
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
