package blackjack.view.dto;

import blackjack.domain.card.Card;

import java.util.List;

public class PlayerStatusDto {
    private final String playerName;
    private final List<Card> playerCards;
    private final int playerScore;

    public PlayerStatusDto(final String playerName, final List<Card> playerCards, final int playerScore) {
        this.playerName = playerName;
        this.playerCards = playerCards;
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
