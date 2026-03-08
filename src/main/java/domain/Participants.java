package domain;

import domain.constant.BlackJackRule;
import domain.vo.NameAndCardInfos;
import java.util.List;

class Participants {

    private final Dealer dealer;
    private final Players players;

    Participants(List<String> names, DrawStrategy drawStrategy) {
        this.dealer = Dealer.of("딜러", drawStrategy);
        this.players = Players.from(names, drawStrategy);
    }

    void addPlayer(String name, DrawStrategy drawStrategy) {
        players.add(name, drawStrategy);
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
}
