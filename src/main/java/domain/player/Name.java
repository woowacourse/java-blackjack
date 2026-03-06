package domain.player;

public class Name {
    private final String name;

    public Name(String name) {
        //validate(Name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEqualName(String name) {
        return this.name.equals(name);
    }

    //private void
}
