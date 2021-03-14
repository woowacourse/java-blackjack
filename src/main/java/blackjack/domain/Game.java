package blackjack.domain;

import blackjack.domain.card.Score;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.gametable.GameTable;
import blackjack.domain.utils.RandomCardDeck;
import blackjack.dto.ProcessDto;
import blackjack.dto.ResultDto;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final String DELIMITER = ",";

    private final GameTable gameTable;
    private final Players players;
    private final Participant dealer;

    public Game(List<String> participantsInfo) {
        List<Player> playersValue = getPlayerList(participantsInfo);
        this.dealer = new Dealer();
        this.players = new Players(playersValue);
        this.gameTable = new GameTable(dealer, players, new RandomCardDeck());
    }

    private List<Player> getPlayerList(List<String> participantsInfo) {
        List<Player> playersValue = new ArrayList<>();

        for (String nameAndMoney : participantsInfo) {
            final String[] infos = nameAndMoney.split(DELIMITER);
            playersValue.add(new Player(infos[0], infos[1]));
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
        final Score dealerScore = dealer.sumCardsForResult();
        return new ResultDto(dealerScore, players.resultOfPlayers(dealerScore));
    }

}