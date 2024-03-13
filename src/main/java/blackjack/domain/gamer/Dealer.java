package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.money.Money;
import blackjack.domain.money.MoneyRate;
import blackjack.domain.money.Wallet;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Gamer {
    public static final int HIT_UPPER_BOUND = 16;
    private static final String name = "딜러";

    private final Wallet wallet;

    public Dealer(Deck deck) {
        super(deck);
        wallet = new Wallet();
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
        wallet.put(name, money);
    }

    public double calculateDealerRevenue(Players players) {
        return calculatePlayerRevenues(players)
                .values()
                .stream()
                .mapToDouble(revenue -> -revenue)
                .sum();
    }

    public Map<String, Double> calculatePlayerRevenues(Players players) {
        Map<String, MoneyRate> rateByPlayerNames = generateRateByPlayerNames(players);

        return getGeneratePlayerRevenues(rateByPlayerNames);
    }

    private Map<String, MoneyRate> generateRateByPlayerNames(Players players) {
        return players.get()
                .stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        this::judgePlayerMoneyRate
                ));
    }

    private MoneyRate judgePlayerMoneyRate(Player player) {
        if (player.isBust()) {
            return MoneyRate.LOSE;
        }
        if (player.isBlackjack()) {
            return MoneyRate.BLACKJACK;
        }
        if (player.getScore() > getScore() || isBust()) {
            return MoneyRate.WIN;
        }
        if (getScore() == player.getScore()) {
            return MoneyRate.DRAW;
        }
        return MoneyRate.LOSE;
    }

    private Map<String, Double> getGeneratePlayerRevenues(Map<String, MoneyRate> rateByPlayerNames) {
        return wallet.get()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .applyRate(rateByPlayerNames.getOrDefault(entry.getKey(), MoneyRate.DRAW))
                ));
    }
}
