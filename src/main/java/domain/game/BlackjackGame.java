package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.player.Bet;
import domain.player.BettingHand;
import domain.player.Dealer;
import domain.player.Hand;
import domain.player.Money;
import domain.player.Name;
import domain.player.Player;
import domain.player.Players;
import domain.random.RandomValueGenerator;
import java.util.List;
import java.util.stream.IntStream;
import util.CardsCreator;
import util.DeckCreator;

public class BlackjackGame {

    private static final int FIRST_DRAW_CARDS_NUM = 2;

    private final Deck deck;
    private final Table table;

    private BlackjackGame(Deck deck, Table table) {
        this.deck = deck;
        this.table = table;
    }

    public static BlackjackGame create(List<String> names, List<Integer> amounts,
                                       RandomValueGenerator randomValueGenerator) {
        Deck deck = DeckCreator.createDeck(CardsCreator.createCards(), randomValueGenerator);
        Dealer dealer = Dealer.from(createInitialHand(deck));
        Players players = Players.of(createPlayers(names, amounts, deck));
        return new BlackjackGame(deck, Table.of(dealer, players));
    }

    private static List<Player> createPlayers(List<String> names, List<Integer> amounts, Deck deck) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> {
                    Name name = new Name(names.get(i));
                    Money money = new Money(amounts.get(i));
                    Bet bet = new Bet(money);

                    Hand initialHand = createInitialHand(deck);

                    BettingHand bettingHand = BettingHand.of(initialHand, bet);

                    return Player.of(name, bettingHand);
                })
                .toList();
    }

    private static Hand createInitialHand(Deck deck) {
        return new Hand(deck.drawCards(FIRST_DRAW_CARDS_NUM));
    }

    public int calculateDealerProfit() {
        return table.getPlayerNames().stream()
                .mapToInt(this::calculatePlayerProfit)
                .sum() * -1;
    }

    public void hitPlayer(String name) {
        table.findPlayer(name).hit(deck.drawCard());
    }

    public void hitDealer() {
        table.getDealer().addCard(deck.drawCard());
    }

    public List<String> getPlayerNames() {
        return table.getPlayerNames();
    }

    public String getDealerName() {
        return table.getDealer().name();
    }

    public boolean canHit(String name) {
        return table.findPlayer(name).canHit();
    }

    public boolean isBlackjack(String name) {
        return table.findPlayer(name).isBlackjack();
    }

    public boolean dealerNeedsToHit() {
        return table.getDealer().needsToHit();
    }

    public boolean areAllPlayersBust() {
        return table.areAllPlayersBust();
    }

    public List<Card> getDealerOpenCard() {
        return table.getDealer().openFirstCard();
    }

    public List<Card> getDealerCards() {
        return table.getDealer().cards();
    }

    public List<Card> getCardsOf(String name) {
        return table.findPlayer(name).cards();
    }

    public int getDealerScore() {
        return table.getDealer().totalScore();
    }

    public int getPlayerScore(String name) {
        return table.findPlayer(name).totalScore();
    }

    public int calculatePlayerProfit(String name) {
        return table.findPlayer(name).calculateProfit(table.getDealer());
    }
}