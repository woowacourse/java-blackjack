package domain.game;

import domain.DtoFactory;
import domain.card.Deck;
import dto.GameInitialInfoDto;
import dto.GameResultDto;
import dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static final int FIRST_DRAW_CARDS = 2;

    private final Deck deck;
    private Players players;
    private final Dealer dealer;

    public GameManager(Deck deck) {
        this.deck = deck;
        this.dealer = new Dealer();
    }

    public void startGame() {
        for (int i = 0; i < FIRST_DRAW_CARDS; i++) {
            players.receiveOneCardFrom(deck);
            dealer.receiveCard(deck.draw());
        }
    }

    public List<String> drawPlayerCard(Player player) {
        player.receiveCard(deck.draw());
        return player.getHandToString();
    }

    public void registerPlayers(List<String> playerNames, List<Integer> bettingMoneyList) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), bettingMoneyList.get(i)));
        }

        this.players = Players.of(players);
    }

    public List<Player> getPlayersToPlay() {
        return players.getNonNaturalBlackJackPlayers();
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

    public GameResultDto getFinalResult() {
        return GameResultJudge.judge(dealer, players);
    }
}