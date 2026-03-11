package domain;

import domain.vo.NameAndCardInfos;
import domain.vo.PlayedGameResult;
import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    private Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants onlyDealer(DrawStrategy drawStrategy) {
        return new Participants(Dealer.of(drawStrategy), Players.noOne(drawStrategy));
    }

    public Participants join(List<String> names) {
        return new Participants(this.dealer, this.players.join(names));
    }

    public List<String> allPlayerNames() {
        return players.names();
    }

    public NameAndCardInfos dealerCardInfos() {
        return dealer.infos();
    }

    public NameAndCardInfos currentPlayerCardInfos() {
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

    public List<NameAndCardInfos> allPlayerCardInfos() {
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

    public PlayedGameResult currentPlayersResult() {
        return players.currentPlayersResult();
    }

    public PlayedGameResult dealerResult() {
        return new PlayedGameResult(dealer.infos(), dealer.scoreSum());
    }

    public boolean isDealerPlayable() {
        return dealer.isPlayable();
    }

    public void dealerDrawCard() {
        dealer.draw();
    }
}
