package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BlackJack {
    private final Dealer dealer;
    private final Players players;

    public BlackJack(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJack init(Map<Player, Money> players) {
        Deck deck = new Deck(new RandomCardsGenerator());
        Dealer dealer = new Dealer(Cards.empty(), deck);
        return new BlackJack(dealer, new Players(players));
    }

    public void handoutCards() {
        dealer.handoutCards(players);
    }

    public void giveOneCardTo(Participant participant) {
        dealer.giveOneCardTo(participant);
    }

    public List<Card> getShowDealerCards() {
        return dealer.getShowCards();
    }

    public boolean canDraw(Participant participant) {
        return !participant.isBust() && !participant.isHit();
    }

    public int getDealerDrawnCount() {
        return dealer.drawCards();
    }

    public int getDealerScore() {
        return dealer.getCardScore();
    }

    public Cards getDealerCards() {
        return dealer.getCards();
    }

    public Set<Player> getPlayers() {
        return players.get();
    }

    public Map<Player, GameResult> getPlayersResult() {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players.get()) {
            GameResult playerResult = dealer.getResult(player);
            gameResult.put(player, playerResult);
        }
        return gameResult;
    }

    public Map<GameResult, Integer> getDealerResult() {
        Map<GameResult, Integer> result = new HashMap<>();
        Map<Player, GameResult> gameResult = getPlayersResult();
        for (GameResult playerResult : gameResult.values()) {
            GameResult dealerResult = playerResult.getReverse();
            final int updated = result.getOrDefault(dealerResult, 0) + 1;
            result.put(dealerResult, updated);
        }
        return result;
    }
}
