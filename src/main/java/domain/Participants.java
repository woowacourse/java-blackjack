package domain;

import domain.constant.BlackJackRule;
import domain.vo.NameAndCardInfos;
import domain.vo.PlayedGameResult;
import java.util.List;

class Participants {

    private final Dealer dealer;
    private final Players players;

    Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    static Participants from(List<String> names, DrawStrategy drawStrategy) {
        Dealer dealer = Dealer.of(drawStrategy);
        Players players = Players.from(names, drawStrategy);

        return new Participants(dealer, players);
    }

    List<String> allPlayerNames() {
        return players.names();
    }

    NameAndCardInfos dealerCardInfos() {
        return dealer.infos();
    }

    NameAndCardInfos currentPlayerCardInfos() {
        return players.currentPlayerCardInfos();
    }

    void allParticipantsDrawInitialCards() {
        dealerDrawInitialCards();
        playersDrawInitialCards();
    }

    private void playersDrawInitialCards() {
        players.drawInitialCards();
    }

    void dealerDrawInitialCards() {
        for (int i = 0; i < BlackJackRule.INITIAL_CARD_COUNT.value(); i++) {
            dealer.draw();
        }
    }

    List<NameAndCardInfos> allPlayerCardInfos() {
        return players.allPlayerCardInfos();
    }

    String currentPlayerName() {
        return players.currentPlayerName();
    }

    void currentPlayerDrawCard() {
        players.currentPlayerDrawCard();
    }

    boolean isCurrentPlayerPlayable() {
        return players.isCurrentPlayerPlayable();
    }

    boolean hasWaitingPlayers() {
        return players.hasWaitingPlayers();
    }

    PlayedGameResult currentPlayersResult() {
        return players.currentPlayersResult();
    }

    PlayedGameResult dealerResult() {
        return new PlayedGameResult(dealer.infos(), dealer.scoreSum());
    }

    boolean isDealerPlayable() {
        return dealer.isPlayable();
    }

    void dealerDrawCard() {
        dealer.draw();
    }
}
