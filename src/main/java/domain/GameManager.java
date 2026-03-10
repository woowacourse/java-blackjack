package domain;

import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;

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
    }

    public List<String> drawPlayerCard(Player player) {
        player.receiveCard(deck.draw());
        return player.getHandToString();
    }

    public void addPlayer(String name) {
        // TODO: 베팅 기능 추가 시 이름만이 아니라 베팅 금액도 함께 받도록 수정 필요
        players.add(new Player(name));
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

    public List<GameFinalResultDto> getFinalResult() {
        return GameResultJudge.judge(dealer, players);
    }

}
