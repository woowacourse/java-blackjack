package model;

import model.result.ParticipantCurrentHand;
import model.result.ParticipantProfit;
import model.result.ProfitResult;
import model.participant.DealerStatus;
import model.participant.PlayerStatus;
import java.util.List;
import model.card.BlackJackDeck;
import model.card.Card;
import model.participant.BetPrice;
import model.participant.Participants;
import model.result.DealerProfit;
import model.result.PlayerProfits;
import model.result.ProfitCalculator;

public class BlackJackGame {
    private static final Integer INITIAL_DRAW_QUANTITY = 2;

    private final BlackJackDeck cards;
    private final Participants participants;

    public BlackJackGame(BlackJackDeck cards) {
        this.cards = cards;
        this.participants = new Participants();
    }

    public void registerPlayer(String playerName) {
        participants.addPlayer(playerName);
    }

    public void placeBet(String playerName, BetPrice bet) {
        participants.addBet(playerName, bet);
    }

    public void initGame() {
        shuffle();
        initDraw();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public boolean isBust(String playerName) {
        return participants.isPlayerBust(playerName);
    }

    public List<ParticipantCurrentHand> getPlayerCurrentHands() {
        return participants.getPlayersHand();
    }

    public Card getDealerFirstCard() {
        return participants.getDealerFirstCard();
    }

    public ParticipantCurrentHand getDealerCurrentHand() {
        return participants.getDealerHand();
    }

    public void drawDealer(Runnable onDraw) {
        while (participants.isDealerCanDraw()) {
            Card card = cards.draw();
            participants.drawDealer(card);
            onDraw.run();
        }
    }

    public ParticipantCurrentHand drawPlayer(String playerName) {
        Card card = cards.draw();
        participants.drawPlayer(playerName, card);

        return participants.getPlayerCurrentHand(playerName);
    }

    public ProfitResult getProfitResult() {
        DealerProfit dealerProfit = new DealerProfit();
        PlayerProfits playerProfit = new PlayerProfits();

        DealerStatus dealerStatus = participants.getDealerStatus();
        List<PlayerStatus> playerStatuses = participants.getPlayerStatuses();

        playerStatuses.forEach(playerStatus -> {
            Long profit = ProfitCalculator.calculateBetAmount(dealerStatus, playerStatus);

            playerProfit.addPlayerProfit(new ParticipantProfit(playerStatus.name(), profit));
            dealerProfit.increase(-profit);
        });

        return new ProfitResult(dealerProfit.getDealerProfit(dealerStatus.name()), playerProfit.getPlayerProfits());
    }

    private void shuffle() {
        cards.shuffle();
    }

    private void initDraw() {
        for(int i  = 0; i < INITIAL_DRAW_QUANTITY; i++) {
            participants.drawDealer(cards.draw());
            initPlayersDraw();
        }
    }

    private void initPlayersDraw() {
        List<String> playerNames = participants.getPlayerNames();
        playerNames.forEach(playerName -> participants.drawPlayer(playerName, cards.draw()));
    }
}
