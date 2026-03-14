package domain.game;

import domain.card.Card;
import domain.participant.Dealer;
import domain.card.Deck;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Players;
import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public GameManager(Players players) {
        this.dealer = initDealer();
        this.players = players;
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    private Dealer initDealer() {
        Name name = new Name("딜러");
        Dealer dealer = new Dealer(name);
        return dealer;
    }

    public void distributeInitialCards() {
        distributeCardToDealer(dealer);
        distributeCardToPlayers(players);
    }

    private void distributeCardToDealer(Dealer dealer) {
        distributeInitialCards(dealer);
    }

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

    //TODO: 나중에 getGameResult랑 judgeResult와 함께하는 클래스를 하나 만들어야 할듯
    public Map<String, GameResult> getGameResult() {
        Map<String, GameResult> gameResult = new HashMap<>();
        players.forEach(player -> {
            GameResult result = player.judgeResult(dealer);
            gameResult.put(player.getName().getName(), result);
        });
        return gameResult;
    }
}
