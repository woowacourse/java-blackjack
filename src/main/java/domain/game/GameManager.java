package domain.game;

import domain.DtoFactory;
import domain.card.Deck;
import domain.dto.GameResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public class GameManager {
    private final int FIRST_DRAW_CARDS = 2;

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;

    public GameManager(Deck deck) {
        this.deck = deck;
        this.players = new Players();
        this.dealer = new Dealer();
    }

    public void startGame() {
        for (int i = 0; i < FIRST_DRAW_CARDS; i++) {
            players.receiveCard(deck.draw());
            dealer.receiveCard(deck.draw());
        }
        players.renewedWithBlackJack();
    }

    public List<String> drawPlayerCard(Player player) {
        player.receiveCard(deck.draw());
        return player.getHandToString();
    }

    public void addPlayer(String name, int bettingMoney) {
        players.add(new Player(name, bettingMoney));
    }

    public List<Player> getPlayerSequence() {
        return players.getPlayers();
    }

    public List<GameScoreResultDto> getScoreResults() {
        return DtoFactory.toScoreResults(dealer, players);
    }

    public List<GameInitialInfoDto> getInitialInfo() {
        return DtoFactory.toInitialInfo(dealer, players);
    }


    public boolean proceedDealerTurn() {
        if (!dealer.canDraw()) {
            return false;
        }

        dealer.receiveCard(deck.draw());
        return true;
    }

    public List<GameResultDto> getFinalResult() {
        return GameResultJudge.judge(dealer, players);
    }

}
