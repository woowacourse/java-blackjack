package domain.gameplaying;

import dto.PlayedGameResult;
import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    private Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants onlyDealer(BlackJackDeck sharedDeck) {
        return new Participants(Dealer.of(sharedDeck), Players.noOne(sharedDeck));
    }

    public void join(List<String> names) {
        this.players.join(names);
    }

    public List<String> allPlayerNames() {
        return players.names();
    }

    public PlayedGameResult dealerCardInfos() {
        return dealer.infos();
    }

    public PlayedGameResult currentPlayerCardInfos() {
        return players.currentPlayerCardInfos();
    }

    public void allParticipantsDrawInitialCards() {
        dealerDrawInitialCards();
        playersDrawInitialCards();
    }

    private void playersDrawInitialCards() {
        players.drawInitialCards();
    }

    private void dealerDrawInitialCards() {
        dealer.drawInitialCards();
    }

    public List<PlayedGameResult> allPlayerCardInfos() {
        return players.allPlayerCardInfos();
    }

    public String currentPlayerName() {
        return players.currentPlayerName();
    }

    public void currentPlayerDrawCard() {
        players.currentPlayerDrawCard();
    }

    public boolean isCurrentPlayerPlayable() {
        return players.isCurrentPlayerPlayable();
    }

    public boolean hasWaitingPlayers() {
        return players.hasWaitingPlayers();
    }

    public PlayedGameResult currentPlayerResult() {
        return players.currentPlayerResult();
    }

    public PlayedGameResult dealerResult() {
        return PlayedGameResult.from(dealer.name(), dealer.cardInfos(), dealer.scoreSum());
    }

    public boolean isDealerPlayable() {
        return dealer.isPlayable();
    }

    public void dealerDrawCard() {
        dealer.draw();
    }
}
