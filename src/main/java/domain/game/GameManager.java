package domain.game;

import domain.card.Card;
import domain.participant.Dealer;
import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Players;

public class GameManager {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public GameManager(Players players) {
        this.dealer = new Dealer();
        this.players = players;
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void distributeInitialCards() {
        distributeInitialCards(dealer);
        distributeCardToPlayers(players);
    }

    // ToDO: GameManager가 초기 카드를 배분하는 게임 진행 흐름의 일부이다. 플레이어들이 무얼하나 보다는, 초기 카드를 배분하는 게임 흐름의 책임.
    private void distributeCardToPlayers(Players players) {
        players.forEach(this::distributeInitialCards);
    }

    private void distributeInitialCards(Participant participant) {
        drawCardTo(participant);
        drawCardTo(participant);
    }

    public void drawCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receiveCard(card);
    }
}
