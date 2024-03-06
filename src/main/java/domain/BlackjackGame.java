package domain;

public class BlackjackGame {

    // 게임을 시작하기 전에 카드 돌려서 세팅
    // 게임 시작
    // 승무패

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initGame() {
        dealer.shuffle();
        for (int i = 0; i < 2; i++) {
            Card dealerCard = dealer.pickCard();
            dealer.add(dealerCard);
            for (int playerIndex = 0; playerIndex < players.count(); playerIndex++) {
                Card card = dealer.pickCard();
                players.giveCardToPlayer(playerIndex, card);
            }
        }
    }
}
