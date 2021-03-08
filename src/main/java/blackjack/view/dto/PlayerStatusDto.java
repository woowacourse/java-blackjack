package blackjack.view.dto;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerStatusDto {
    private final String playerName;
    private final List<String> playerCardStatus;
    private final int playerScore;

    public PlayerStatusDto(final String playerName, final List<Card> playerCards, final int playerScore) {
        this.playerName = playerName;
        this.playerCardStatus = cardsToString(playerCards);
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getPlayerCardStatus() {
        return playerCardStatus;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public List<String> cardsToString(final List<Card> playerCards) {
        return playerCards.stream()
                .map(playerCard -> playerCard.symbolName() + playerCard.numberScore())
                .collect(Collectors.toList());
    }
}
