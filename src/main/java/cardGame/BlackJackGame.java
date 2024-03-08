package cardGame;

import cardGame.dto.BlackJackGameStatusDto;
import cardGame.dto.ParticipantsTotalGameResultDto;
import dealer.Dealer;
import java.util.ArrayList;
import java.util.List;
import player.Name;
import player.Player;
import player.Players;

public class BlackJackGame {

    private final Players players;
    private final Dealer dealer;

    public BlackJackGame(List<String> names) {
        this.dealer = new Dealer();
        this.players = initGamePlayer(names);
    }

    private Players initGamePlayer(List<String> names) {
        List<Name> playerNames = names.stream()
                .map(Name::new)
                .toList();

        return initPlayersCard(playerNames);
    }

    private Players initPlayersCard(List<Name> playerNames) {
        Players players = Players.from(playerNames);

        for (Player player : players.getPlayers()) {
            player.receiveCard(dealer.giveCard());
            player.receiveCard(dealer.giveCard());
        }
        return players;
    }

    public List<SingleMatch> startGame() {
        ArrayList<SingleMatch> singleMatches = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            singleMatches.add(new SingleMatch(player, dealer));
        }
        return singleMatches;
    }

    public BlackJackGameStatusDto getBackJackGameStatus() {
        return BlackJackGameStatusDto.of(players, dealer);
    }

    public ParticipantsTotalGameResultDto getParticipantScore() {
        return ParticipantsTotalGameResultDto.of(players, dealer);
    }

    public int countDealerExtraCard() {
        dealer.checkNeedExtraCard();
        return dealer.countExtraCard();
    }
}
