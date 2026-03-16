package domain.game;

import domain.card.Deck;
import domain.player.BettingHand;
import domain.player.BettingProfit;
import domain.player.Dealer;
import domain.player.Hand;
import domain.player.Money;
import domain.player.Name;
import domain.player.Player;
import domain.player.Players;
import domain.random.RandomValueGenerator;
import java.util.List;
import java.util.Map;
import util.CardsCreator;
import util.DeckCreator;

public class BlackjackGame {

    private static final int FIRST_DRAW_CARDS_NUM = 2;

    private final Deck deck;
    private Participants participants;

    private BlackjackGame(Deck deck) {
        this.deck = deck;
    }

    public static BlackjackGame create(RandomValueGenerator randomValueGenerator) {
        Deck deck = DeckCreator.createDeck(CardsCreator.createCards(), randomValueGenerator);
        return new BlackjackGame(deck);
    }

    public void start(Map<String, Integer> playerInfos) {
        Dealer dealer = Dealer.from(createInitialHand(deck));
        Players players = Players.of(createPlayers(playerInfos));
        this.participants = Participants.of(dealer, players);
    }

    private List<Player> createPlayers(Map<String, Integer> playerInfos) {
        return playerInfos.entrySet().stream()
                .map(entry -> {
                    Name name = new Name(entry.getKey());
                    Money money = new Money(entry.getValue());
                    BettingProfit bettingProfit = new BettingProfit(money);

                    Hand initialHand = createInitialHand(deck);
                    BettingHand bettingHand = BettingHand.of(initialHand, bettingProfit);

                    return Player.of(name, bettingHand);
                })
                .toList();
    }

    private static Hand createInitialHand(Deck deck) {
        return new Hand(deck.drawCards(FIRST_DRAW_CARDS_NUM));
    }

    public int calculateDealerProfit() {
        return getPlayers().stream()
                .mapToInt(p -> p.calculateProfit(getDealer()))
                .sum() * -1;
    }

    public List<Player> getPlayers() {
        return participants.getPlayers().getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public boolean isNotFinishPlayersRound() {
        return !participants.getPlayers().isAllFinished();
    }

    public Player getCurrentPlayer() {
        return participants.getPlayers().getCurrentPlayer();
    }

    public void passTurn() {
        participants.getPlayers().passTurn();
    }

    public void playCurrentPlayerTurn(boolean isHit) {
        Player current = getCurrentPlayer();
        if (isHit && current.canHit()) {
            current.hit(deck.drawCard());
            if (!current.canHit()) {
                passTurn();
            }
            return;
        }
        passTurn();
    }

    public boolean canDealerHit() {
        Dealer dealer = participants.getDealer();
        boolean isAllPlayersBust = participants.getPlayers().areAllBust();

        return dealer.needsToHit() && !isAllPlayersBust;
    }

    public void hitDealer() {
        participants.getDealer().addCard(deck.drawCard());
    }

    public boolean areAllPlayersBust() {
        return participants.getPlayers().areAllBust();
    }
}
