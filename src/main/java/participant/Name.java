package participant;

public record Name(String name) {
    @Override
    public String toString() {
        return name;
    }
}
