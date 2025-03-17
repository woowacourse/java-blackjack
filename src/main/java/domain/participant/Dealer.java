package domain.participant;

import domain.GameResult;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.cardsGenerator.CardsGenerator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public GameResult judgeResult(Player player) {
        if (isPlayerBlackJackWin(player)) {
            return GameResult.BLACKJACK;
        }
        if (isPlayerWin(player)) {
            return GameResult.WIN;
        }
        if (isPlayerLose(player)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private boolean isPlayerBlackJackWin(Player player) {
        return player.isBlackJack() && !this.isBlackJack();
    }

    private boolean isPlayerWin(Player player) {
        return (this.isBurst() && !player.isBurst()) ||
                (!player.isBurst() && player.calculateScore() > this.calculateScore());
    }

    private boolean isPlayerLose(Player player) {
        return player.isBurst() || (player.calculateScore() < this.calculateScore()) && !this.isBurst();
    }

    public Map<Player, Money> getPlayersRevenue(Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(player -> player, this::calculateRevenue));
    }

    private Money calculateRevenue(Player player) {
        GameResult result = judgeResult(player);
        return player.calculateRevenue(result);
    }

    public Map<Player, GameResult> getGameResult(Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(player -> player, this::judgeResult));
    }

    public int getNewCardCount() {
        int beforeCount = super.handSize();
        drawUntilLimit();
        return super.handSize() - beforeCount;
    }

    public void drawUntilLimit() {
        while (hasToDraw()) {
            deck.giveCardTo(this, 1);
        }
    }

    private boolean hasToDraw() {
        return this.calculateScore() <= DRAW_BOUNDARY;
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
