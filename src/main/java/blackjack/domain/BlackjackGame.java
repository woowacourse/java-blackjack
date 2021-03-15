package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.utils.CardDeck;
import blackjack.dto.ProcessDto;
import blackjack.dto.ResultDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Players players;
    private final Participant dealer;
    private final CardDeck cardDeck;

    public BlackjackGame(final Map<String,String> participantsInfo, CardDeck cardDeck) {
        List<Player> playersValue = getPlayerList(participantsInfo);
        this.dealer = new Dealer();
        this.players = new Players(playersValue);
        this.cardDeck = cardDeck;
        initGame();
    }

    public BlackjackGame(final Participant dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
        initGame();
    }

    private List<Player> getPlayerList(final Map<String,String> participantsInfo) {
        List<Player> playersValue = new ArrayList<>();

        for (Map.Entry<String, String> info : participantsInfo.entrySet()) {
            playersValue.add(new Player(info.getKey(), info.getValue()));
        }

        return playersValue;
    }

    private void initGame() {
        dealer.takeCard(cardDeck.pop());
        dealer.takeCard(cardDeck.pop());

        players.takeTwoCards(cardDeck);
    }

    public ProcessDto getProcessDto() {
        return new ProcessDto(players, dealer);
    }

    public List<Player> getPlayers() {
        return players.getUnmodifiableList();
    }

    public Player turnForPlayer(final Player player) {
        if (player.isAbleToTake()) {
            player.takeCard(cardDeck.pop());
        }
        return player;
    }

    public void turnForDealer() {
        if (dealer.isAbleToTake()) {
            dealer.takeCard(cardDeck.pop());
        }
    }

    public ResultDto getResultDto() {
        return new ResultDto(players.resultOfPlayers(dealer.finalScore()));
    }

}