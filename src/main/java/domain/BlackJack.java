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
        Score score = participant.getScore();
        return !score.isBust() && !score.isHit();
    }

    public int getDealerDrawnCount() {
        return dealer.drawCards();
    }

    public int getDealerScore() {
        Score score = dealer.getScore();
        return score.value();
    }

    public List<Card> getDealerCards() {
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

    public int getDealerRevenue() {
        Map<Player, Integer> revenues = getPlayerRevenues();
        return -1 * revenues.values().stream().mapToInt(i -> i).sum();
    }

    public Map<Player, Integer> getPlayerRevenues() {
        Map<Player, Integer> revenues = new HashMap<>();
        for (Player player : players.get()) {
            GameResult gameResult = dealer.getResult(player);
            PlayerRevenuePolicy policy = PlayerRevenuePolicy.from(gameResult, player.isBlackjack());
            Money bettingMoney = players.getBettingMoneyOf(player);
            final int playerRevenue = policy.getRevenue(bettingMoney.value());
            revenues.put(player, playerRevenue);
        }
        return revenues;
    }
}
