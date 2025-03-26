package domain.betting;

public record Revenue(int value) {

    public Revenue reverse() {
        return new Revenue(-value);
    }
}
