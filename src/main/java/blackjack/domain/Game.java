package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.gametable.GameTable;
import blackjack.domain.gametable.ScoreBoard;
import blackjack.domain.utils.RandomCardDeck;
import blackjack.dto.ProcessDto;
import blackjack.dto.ResultDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final GameTable gameTable;
    private final Players players;
    private final Participant dealer;

    public Game(List<String> participantsInfo) {
        List<Player> playersValue = getPlayerList(participantsInfo);
        this.dealer = new Dealer(new Cards(Collections.emptyList()));
        this.players = new Players(playersValue);
        this.gameTable = new GameTable(dealer, players, new RandomCardDeck());
    }

    private List<Player> getPlayerList(List<String> participantsInfo) {
        List<Player> playersValue = new ArrayList<>();
        for (String nameAndMoney : participantsInfo) {
            final String[] infos = nameAndMoney.split(",");
            playersValue.add(new Player(new Name(infos[0]), new BettingMoney(infos[1])));
        }
        return playersValue;
    }

    public ProcessDto getProcessDto() {
        return new ProcessDto(players, dealer);
    }

    public List<Player> getPlayers() {
        return players.getUnmodifiableList();
    }

    public Player turnForPlayer(Player player) {
        gameTable.giveCard(player);
        return player;
    }

    public void turnForDealer() {
        gameTable.giveCard(dealer);
    }

    public ResultDto getResultDto() {
        return new ResultDto(new ScoreBoard(dealer, players));
    }

}