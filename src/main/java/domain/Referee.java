package domain;

import java.util.List;
import java.util.Map;

public class Referee {
    public static List<Betting> bettings;

    public static Dealer dealer;

    public Referee(Dealer dealer, Bettings bettings) {
        this.dealer = dealer;
        this.bettings = bettings.getBettings();
    }

    public void decideWinner(Player player, Map<Gambler, Integer> result) {
        if (isPlayerWin(player, dealer)) {
            result.put(player, 1);
        }

        if (isDealerWin(player, dealer)) {
            result.put(player, 0);
            result.replace(dealer, result.get(dealer) + 1);
        }
    }

    public void decideBenefits(Player player, List<Player> players, Map<Gambler, Integer> benefits) {
        int index = players.indexOf(player);
        int winnerCount = 0;

        if (isPlayerWin(player, dealer)) {
            if (!benefits.containsKey(player)) {
                benefits.put(player, 0);
            }
        }

        if (isDealerWin(player, dealer)) {
            loseBenefit(player, benefits, bettings.get(index).getBetting());
            winnerCount++;

            for (Player searchPlayer : players) {
                if (searchPlayer != player && isPlayerWin(searchPlayer, dealer)) {
                    winnerCount++;
                }
            }

            int money = bettings.get(index).getBetting() / winnerCount;
            earnBenefit(benefits, money, dealer);
            for (Player searchPlayer : players) {
                if (searchPlayer != player && isPlayerWin(searchPlayer, dealer)) {
                    earnBenefit(benefits, money, searchPlayer);
                }
            }
        }
    }

    private void loseBenefit(Player player, Map<Gambler, Integer> benefits, int money) {
        if (!benefits.containsKey(player)) {
            benefits.put(player, 0 - money);
            return;
        }

        if (benefits.containsKey(player)) {
            benefits.replace(player, benefits.get(player) - money);
        }
    }

    private void earnBenefit(Map<Gambler, Integer> benefits, int money, Gambler gambler) {
        if (!benefits.containsKey(gambler)) {
            benefits.put(gambler, money);
        }

        if (benefits.containsKey(gambler)) {
            benefits.replace(gambler, benefits.get(gambler) + money);
        }
    }

    private boolean isPlayerWin(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return (dealerScore <= playerScore && !player.isBustedGambler()
                || (dealer.isBustedGambler() && !player.isBustedGambler()));
    }

    private boolean isDealerWin(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return dealerScore > playerScore || player.isBustedGambler();
    }
}
