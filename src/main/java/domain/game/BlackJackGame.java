package domain.game;

import domain.BettingMoney;
import domain.DtoFactory;
import domain.Name;
import domain.card.Deck;
import dto.GameInitialInfoDto;
import dto.GameResultDto;
import dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private static final int FIRST_DRAW_CARDS = 2;

    private final Deck deck;
    private Players players;
    private final Dealer dealer;

    public BlackJackGame(Deck deck) {
        this.deck = deck;
        this.dealer = new Dealer();
    }

    public void dealInitialCards() {
        for (int i = 0; i < FIRST_DRAW_CARDS; i++) {
            players.dealOneCardToPlayers(deck);
            dealer.addCard(deck.draw());
        }
    }

    public List<String> hit(Player player) {
        player.addCard(deck.draw());
        return player.getHandToString();
    }

    public void registerPlayers(List<Name> playerNames, List<BettingMoney> bettingMoneyList) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), bettingMoneyList.get(i)));
        }

        this.players = Players.of(players);
    }

    public List<Player> getPlayersToPlay() {
        return players.getPlayersInTurn();
    }

    public List<GameScoreResultDto> getScoreResults() {
        return DtoFactory.toScoreResults(dealer, players);
    }

    public List<GameInitialInfoDto> getInitialInfo() {
        return DtoFactory.toInitialInfo(dealer, players);
    }

    public boolean dealerHit() {
        if (!dealer.canDraw()) {
            return false;
        }

        dealer.addCard(deck.draw());
        return true;
    }

    public GameResultDto getFinalResult() {
        return GameResultJudge.judge(dealer, players);
    }
}