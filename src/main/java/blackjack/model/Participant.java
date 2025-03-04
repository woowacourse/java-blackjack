package blackjack.model;

public class Participant {
    private final String name;
    private final ReceivedCards receivedCards;

    public Participant(String name) {
        validate(name);
        this.name = name;
        this.receivedCards = new ReceivedCards();
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참여자 이름을 입력해주세요.");
        }
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("참여자 이름은 2~5글자 입니다.");
        }
    }

    public void putCard(NormalCard normalCard) {
        receivedCards.receive(normalCard);
    }

    public ReceivedCards getReceivedCards() {
        return receivedCards;
    }
}
