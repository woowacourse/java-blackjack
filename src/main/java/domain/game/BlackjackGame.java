package domain.game;

import domain.card.Card;
import domain.card.CardMachine;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    private final CardMachine cardMachine;
    private final BlackjackJudge blackjackJudge;
    private final Participants participants;

    public BlackjackGame(CardMachine cardMachine, BlackjackJudge blackjackJudge, Participants participants) {
        this.cardMachine = cardMachine;
        this.blackjackJudge = blackjackJudge;
        this.participants = participants;
    }

    public void drawInitialCards() {
        participants.drawInitialCards(this::drawCard);
    }

    public boolean drawDealerCard() {
        if (participants.dealerShouldHit()) {
            participants.drawCardsByDealer(this::drawCard);
            return true;
        }

        return false;
    }

    private Card drawCard() {
        return cardMachine.drawCard();
    }

    public Player updatePlayer(String name) {
        return participants.drawCardsByPlayer(name, this::drawCard);
    }

    public Dealer getDealer() {
        return participants.dealer();
    }

    public List<Player> getPlayers() {
        return participants.players().getPlayers();
    }

    public BlackjackStatistics calculateStatistics() {
        List<PlayerProfit> playerProfits = new ArrayList<>();
        int dealerProfit = 0;
        for (Player player : participants.players().getPlayers()) {
            PlayerResult playerResult = blackjackJudge.judgePlayerResult(participants.dealer(), player);
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
        return participants.isPlayerBust(name);
    }
}
