package domain;

import java.util.List;
import java.util.Map;

public class Game {
    private static final String DEALER = "딜러";

    private final Deck totalDeck;
    private final Dealer dealer;
    private final Players players;

    public Game(Deck totalDeck, Dealer dealer, Players players) {
        this.totalDeck = totalDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static Game ready(List<String> playerNames, CardCreationStrategy strategy) {
        Deck totalDeck = Deck.createDeck(strategy);

        Deck dealerDeck = Deck.createParticipantDeck(totalDeck);
        Dealer dealer = new Dealer(dealerDeck);

        Players players = Players.of(playerNames, totalDeck);
        
        return new Game(totalDeck, dealer, players);
    }

    public Map<String, List<Card>> showInitialCardShareResult() {
        Map<String, List<Card>> result = players.getDecksPerPlayer();

        List<Card> dealerCard = dealer.getCards();
        dealerCard.removeLast();

        result.put(DEALER, dealerCard);

        return result;
    }

    public void play() {
        //한 턴만. -> isAnyOneGo 안씀

    }
}
