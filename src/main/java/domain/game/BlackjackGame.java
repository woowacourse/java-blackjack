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
    private final Participants participants;

    private BlackjackGame(Deck deck, Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static BlackjackGame create(Map<String, Integer> playerInfos,
                                       RandomValueGenerator randomValueGenerator) {
        Deck deck = DeckCreator.createDeck(CardsCreator.createCards(), randomValueGenerator);
        Dealer dealer = Dealer.from(createInitialHand(deck));
        Players players = Players.of(createPlayers(playerInfos, deck));
        return new BlackjackGame(deck, Participants.of(dealer, players));
    }

    private static List<Player> createPlayers(Map<String, Integer> playerInfos, Deck deck) {
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

    public void hitDealer() {
        participants.getDealer().addCard(deck.drawCard());
    }

    public boolean areAllPlayersBust() {
        return participants.getPlayers().areAllBust();
    }
}
