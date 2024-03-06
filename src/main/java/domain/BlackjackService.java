package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackService {
    public static final int INITIAL_CARD_COUNT = 2;
    private final Participants participants;
    private final Deck deck;

    public BlackjackService(Player dealer, List<Player> players) {
        this.participants = new Participants(dealer, players);
        this.deck = new Deck();
        Arrays.stream(Rank.values())
                .forEach((rank) -> {
                    Arrays.stream(Shape.values())
                            .forEach((shape) -> deck.addCard(new Card(shape, rank)));
                });
    }

    public void initialDistribute() {
        List<Deck> initialCards = new ArrayList<>();
        for (int participantsCount = 0; participantsCount < participants.count(); participantsCount++) {
            initialCards.add(makeInitialDeck());
        }
        participants.receiveInitialCards(initialCards);
    }

    private Deck makeInitialDeck() {
        Deck tempDeck = new Deck();
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            tempDeck.addCard(this.deck.pickRandomCard());
        }
        return tempDeck;
    }

    public Deck getDeck() {
        return deck;
    }

    public void addCard(Player player) {
        player.receiveCard(deck.pickRandomCard()); // TODO refactor

    }

    /*
    public Map<Player, Boolean> calculateVictory() {
        Map<Player, Boolean> result = new LinkedHashMap<>();
        participants.forEach(player -> {
            if (player.getDeck().calculateTotalScore() > 21) {
                result.put(player, false);
                return;
            }
            if (dealer.getDeck().calculateTotalScore() > 21) {
                result.put(player, true);
                return;
            }
            if (dealer.getDeck().calculateTotalScore() >= player.getDeck().calculateTotalScore()) {
                result.put(player, false);
                return;
            }
            result.put(player, true);
        });

        return result;
    }

     */


}
