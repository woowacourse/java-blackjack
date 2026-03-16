package domain;

public record CardInfo(String cardLabel, String cardMark) {

    @Override
    public String toString() {
        return cardLabel() + cardMark();
    }
}
