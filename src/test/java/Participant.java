public class Participant {
    private String name;

    public Participant(String name) {
        this.name = name;
    }

    public static Participant from(String input) {
        return new Participant(input);
    }

    public String getName() {
        return name;
    }
}
