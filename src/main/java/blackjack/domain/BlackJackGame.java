package blackjack.domain;

import blackjack.domain.strategy.RandomCardGenerator;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    private final List<Participant> players;
    private final Participant dealer;
    private final CardDeck cardDeck;

    public BlackJackGame(List<String> playersNames) {
        this.cardDeck = new CardDeck(new RandomCardGenerator());
        this.dealer = new Dealer(cardDeck.drawTwoCards());
        validateEmptyNames(playersNames);
        this.players = createPlayers(playersNames);
    }

    private void validateEmptyNames(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }


    private List<Participant> createPlayers(List<String> playersNames) {
        return playersNames.stream()
                .map(playerName -> new Player(playerName.trim(), cardDeck.drawTwoCards()))
                .collect(Collectors.toList());
    }


}
