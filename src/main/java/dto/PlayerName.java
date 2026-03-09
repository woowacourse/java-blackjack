package dto;

public record PlayerName(String value) {
    @Override
    public String toString() {
        return value;
    }
}
