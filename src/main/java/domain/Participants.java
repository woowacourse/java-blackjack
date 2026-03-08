package domain;

import domain.constant.BlackJackRule;
import domain.vo.NameAndCardInfos;
import java.util.List;

class Participants {

    private final Dealer dealer;
    private final Players players;

    Participants(Dealer dealer) {
        this.dealer = dealer;
        this.players = new Players();
    }

    void addPlayer(String name) {
        players.add(name);
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

    void allParticipantsDrawInitialCards(DrawStrategy drawStrategy) {
        dealerDrawInitialCards(drawStrategy);
        playersDrawInitialCards(drawStrategy);
    }

    private void playersDrawInitialCards(DrawStrategy drawStrategy) {
        players.drawInitialCards(drawStrategy);
    }

    void dealerDrawInitialCards(DrawStrategy drawStrategy) {
        for (int i = 0; i < BlackJackRule.INITIAL_CARD_COUNT.value(); i++) {
            dealer.draw(drawStrategy);
        }
    }

    List<NameAndCardInfos> allPlayerCardInfos() {
        return players.allPlayerCardInfos();
    }

    public String currentPlayerName() {
        return players.currentPlayerName();
    }

    public void currentPlayerDrawCard(DrawStrategy drawStrategy) {
        players.currentPlayerDrawCard(drawStrategy);
    }

    public boolean isCurrentPlayerPlayable() {
        return players.isCurrentPlayerPlayable();
    }

    public boolean hasWaitingPlayers() {
        return players.hasWaitingPlayers();
    }
}
