package model;

import java.util.List;
import model.dto.ParticipantWinning;
import model.dto.PlayerResult;

public class BlackJackGame {
    private static final Integer INITIAL_DRAW_QUANTITY = 2;

    private final BlackJackDeck cards;
    private final Participants participants;

    public BlackJackGame(BlackJackDeck cards) {
        this.cards = cards;
        this.participants = new Participants();
    }

    public void registerPlayer(Player player) {
        participants.addPlayer(player);
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

    public List<PlayerResult> getPlayerResults() {
        return participants.getPlayerResults();
    }

    public PlayerResult getDealerResult() {
        return participants.getDealerResult();
    }

    public String getDealerFirstCard() {
        return participants.getDealerFirstCard();
    }

    public void drawDealer(Runnable onDraw) {
        while(!participants.isDealerCanDraw()) {
            Card card = cards.draw();
            participants.drawDealer(card);
            onDraw.run();
        }
    }

    public void drawPlayer(String playerName) {
        Card card = cards.draw();
        participants.drawPlayer(playerName, card);
    }

    public PlayerResult getPlayerResult(String playerName) {
        return participants.getPlayerResult(playerName);
    }

    public ParticipantWinning getGameResult() {
        return participants.getGameResult();
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
