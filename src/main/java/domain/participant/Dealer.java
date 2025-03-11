package domain.participant;

import domain.GameResult;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.cardsGenerator.CardsGenerator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Dealer extends Participant {

    private static final int DRAW_BOUNDARY = 16;
    private static final int INIT_COUNT = 2;

    private final Deck deck;

    private Dealer(Hand hand, Deck deck) {
        super(hand);
        this.deck = deck;
    }

    public static Dealer of(Hand hand, CardsGenerator cardsGenerator) {
        return new Dealer(hand, new Deck(cardsGenerator));
    }

    public static Dealer init(CardsGenerator cardsGenerator) {
        return new Dealer(Hand.empty(), new Deck(cardsGenerator));
    }

    public void handoutCards(Players players) {
        giveCards(this, INIT_COUNT);
        List<Player> participants = players.getPlayers();
        for (Participant participant : participants) {
            giveCards(participant, INIT_COUNT);
        }
    }

    public void giveCards(Participant participant, int count) {
        deck.giveCardTo(participant, count);
    }

    public void drawUntilLimit() {
        while (hasToDraw()) {
            deck.giveCardTo(this, 1);
        }
    }

    private boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }


    public Map<Player, GameResult> getGameResult(Players players) {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult playerResult = GameResult.getResult(player, this);
            gameResult.put(player, playerResult);
        }
        return gameResult;
    }

    public int getNewCardCount() {
        return super.getHand().getCards().size() - INIT_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Dealer dealer = (Dealer) o;
        return Objects.equals(deck, dealer.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck);
    }
}
