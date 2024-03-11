package cardGame;

import card.Card;
import card.CardDeck;
import controller.dto.WinningResult;
import dealer.Dealer;
import dealer.dto.DealerWinningResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import player.Name;
import player.Player;
import player.Players;
import player.dto.CardsStatus;

public class BlackJackGame {

    private static final String DEALER_NAME = "딜러";

    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackGame() {
        this.cardDeck = new CardDeck();
        this.dealer = new Dealer(cardDeck.firstCardSettings());
    }

    public Players initGamePlayer(List<String> names) {
        return Players.from(names, cardDeck);
    }

    public Card getInitDealerStatus() {
        return dealer.getCards().getFirstCard();
    }

    public List<CardsStatus> playGame(BiConsumer<Player, CardDeck> playSingleMatch, Players players) {
        List<CardsStatus> playerResult = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            playerResult.add(player.play(playSingleMatch, cardDeck));
        }

        return playerResult;
    }

    public CardsStatus playDealerTurn() {
        dealer.getExtraCard(cardDeck);
        return new CardsStatus(new Name(DEALER_NAME), dealer.getCards());
    }

    public List<WinningResult> getPlayersResult(Players players) {
        return players.getPlayers().stream()
                .map(player -> new WinningResult(player.getName(), player.isWinner(dealer.getCardScore())))
                .collect(Collectors.toList());
    }

    public DealerWinningResult getDealerResult(Players players) {
        return dealer.getWinningResult(players);
    }
}
