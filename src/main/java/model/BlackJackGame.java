package model;

import dto.result.ParticipantCurrentHand;
import dto.result.ProfitResult;
import java.util.List;
import model.card.BlackJackDeck;
import model.card.Card;
import model.participant.BetPrice;
import model.participant.Participants;
import model.participant.Player;
import dto.status.PlayerName;

public class BlackJackGame {
    private static final Integer INITIAL_DRAW_QUANTITY = 2;

    private final BlackJackDeck cards;
    private final Participants participants;

    public BlackJackGame(BlackJackDeck cards) {
        this.cards = cards;
        this.participants = new Participants();
    }

    public void registerPlayer(PlayerName playerName) {
        participants.addPlayer(playerName);
    }

    public void setBet(String playerName, BetPrice bet) {
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

    public String getDealerFirstCard() {
        return participants.getDealerFirstCard();
    }

    public ParticipantCurrentHand getDealerCurrentHand() {
        return participants.getDealerHand();
    }

    public void drawDealer(Runnable onDraw) {
        while(participants.isDealerCanDraw()) {
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
        return participants.getProfitResult();
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
        for(String player : participants.getPlayerNames()) {
            participants.drawPlayer(player, cards.draw());
        }
    }
}
