package domain.game;

import domain.card.Card;
import domain.card.CardMachine;
import domain.game.result.BlackjackStatistics;
import domain.game.result.PlayerProfit;
import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    private final CardMachine cardMachine;
    private final BlackjackJudge blackjackJudge;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(CardMachine cardMachine, BlackjackJudge blackjackJudge,
                         List<PlayerName> playerNames, List<BetAmount> betAmounts) {
        this.cardMachine = cardMachine;
        this.blackjackJudge = blackjackJudge;
        this.dealer = new Dealer();
        this.players = new Players(playerNames, betAmounts);
    }

    public void drawInitialCards() {
        dealer.drawInitialCards(this::drawCard);
        players.drawInitialCards(this::drawCard);
    }

    public boolean drawDealerCard() {
        if (dealer.shouldHit()) {
            dealer.drawCard(drawCard());
            return true;
        }

        return false;
    }

    private Card drawCard() {
        return cardMachine.drawCard();
    }

    public Player hitPlayer(String name) {
        players.addCard(name, drawCard());
        return players.getPlayer(name);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public BlackjackStatistics calculateStatistics() {
        List<PlayerProfit> playerProfits = new ArrayList<>();
        int dealerProfit = 0;
        for (Player player : players.getPlayers()) {
            PlayerResult playerResult = blackjackJudge.judgePlayerResult(dealer, player);
            int playerProfit = playerResult.getProfit(player.getBetAmount());
            dealerProfit += getDealerProfitFrom(playerProfit);
            playerProfits.add(new PlayerProfit(player.getName(), playerProfit));
        }

        return new BlackjackStatistics(dealerProfit, playerProfits);
    }

    private static int getDealerProfitFrom(int playerProfit) {
        return -playerProfit;
    }

    public boolean isPlayerBust(String name) {
        return players.isPlayerBust(name);
    }
}
