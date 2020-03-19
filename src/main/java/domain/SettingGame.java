package domain;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.PlayerInputInformation;
import domain.player.Players;
import domain.player.PlayersName;
import view.InputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SettingGame {
    private CardDeck cardDeck;
    private PlayersName playersName;
    private PlayerInputInformation playerInputInformation;

    public SettingGame(String playersName) {
        if (playersName == null) {
            throw new NullPointerException("null 값이 입력되었습니다.");
        }
        this.playersName = new PlayersName(playersName);
        this.cardDeck = new CardDeck();
        this.playerInputInformation = new PlayerInputInformation(battingMoneyByName(this.playersName));
    }

    private Map<String, Double> battingMoneyByName(PlayersName playersName) {
        Map<String, Double> playerInputInformation = new LinkedHashMap<>();
        for (String playerName : playersName.getPlayerName()) {
            playerInputInformation.put(playerName, InputView.inputBattingMoney(playerName));
        }
        return playerInputInformation;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public List<String> getPlayersName() {
        return playersName.getPlayerName();
    }

    public Dealer generateDealer() {
        return new Dealer(cardDeck.giveTwoCardStartGame());
    }

    public Players generatePlayers() {
        return new Players(cardDeck, playerInputInformation.getPlayerInformation());
    }

}
