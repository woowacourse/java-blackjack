package domain.game;

import static util.Constants.DEFAULT_START_CARD_COUNT;

import domain.card.Card;
import domain.card.GameCards;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Participant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {

    private final Dealer dealer;
    private final Gamblers gamblers;
    private final GameCards gameCards;

    public Game(String dealerName, List<String> names, int amount) {
        this.dealer = new Dealer(dealerName);
        this.gamblers = new Gamblers(names);
        this.gameCards = new GameCards(amount);
    }

    public void initializeGame() {
        for (int i = 0; i < DEFAULT_START_CARD_COUNT; i++) {
            dealer.addCard(gameCards.drawCard());
            gamblers.receiveCards(gameCards);
        }
    }

    public Map<String, List<String>> getParticipantsHandInfo() {
        Map<String, List<String>> info = new HashMap<>();
        info.put(dealer.getName(), List.of(dealer.getFirstHand()));
        info.putAll(gamblers.getHandsInfo());
        return info;
    }

    public int getDealerHandSize() {
        return dealer.getCardSize();
    }

    public List<Integer> getGamblersHandSize() {
        return gamblers.getHandSize();
    }

    public List<Gambler> getGamblersList() {
        return gamblers.getGamblers();
    }

    public Card pickCard() {
        return gameCards.drawCard();
    }
}

