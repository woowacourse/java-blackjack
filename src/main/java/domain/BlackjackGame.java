package domain;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Participants participants;

    public BlackjackGame(Participants participants) {
        this.participants = participants;
        this.cardDeck = new CardDeck();
    }

    public void dealOutCard() {
        participants.addTwoCards(cardDeck);
    }

    // TODO: 카드 점수에 따른 승패 판단 (딜러와 각 플레이어를 비교)
    // TODO: 승패 저장

}
