package domain;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Players players;

    public BlackjackGame(Players players) {
        this.players = players;
        this.cardDeck = CardDeck.generate();
    }

    public void dealOutCard() {
        players.addTwoCards(cardDeck);
    }


    // TODO: 조건에 따라 나눠준다 (플레이어부터, 딜러는 가장 마지막에)
    // TODO: 카드 점수에 따른 승패 판단 (딜러와 각 플레이어를 비교)
    // TODO: 승패 저장

}
