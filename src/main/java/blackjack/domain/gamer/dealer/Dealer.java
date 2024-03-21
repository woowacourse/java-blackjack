package blackjack.domain.gamer.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.player.Player;
import blackjack.domain.gamer.player.Players;
import blackjack.domain.money.Money;
import blackjack.domain.money.RewardRate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Gamer {
    public static final int HIT_UPPER_BOUND = 16;
    private static final String name = "딜러";

    private final BettingPouch bettingPouch;

    public Dealer(Deck deck) {
        super(deck);
        bettingPouch = new BettingPouch();
    }

    @Override
    public boolean canContinue() {
        return getScore() <= HIT_UPPER_BOUND;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return super.getCards();
    }

    public Card getFirstCard() {
        return hand.getCard(0);
    }

    public void keepPlayerMoney(String name, Money money) {
        bettingPouch.put(name, money);
    }

    public double calculateDealerRevenue(Players players) {
        return calculatePlayerRevenues(players)
                .values()
                .stream()
                .mapToDouble(revenue -> -revenue)
                .sum();
    }

    public Map<String, Double> calculatePlayerRevenues(Players players) {
        Map<String, RewardRate> rateByPlayerNames = generateRateByPlayerNames(players);

        return bettingPouch.getGeneratePlayerRevenues(rateByPlayerNames);
    }

    private Map<String, RewardRate> generateRateByPlayerNames(Players players) {
        return players.get()
                .stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        this::judgePlayerMoneyRate
                ));
    }

    private RewardRate judgePlayerMoneyRate(Player player) {
        if (player.isBust()) {
            return RewardRate.LOSE;
        }
        if (player.isBlackjack()) {
            return RewardRate.BLACKJACK;
        }
        if (player.getScore() > getScore() || isBust()) {
            return RewardRate.WIN;
        }
        if (getScore() == player.getScore()) {
            return RewardRate.DRAW;
        }
        return RewardRate.LOSE;
    }
}
