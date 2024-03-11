package cardGame;

import card.Card;
import card.CardDeck;
import controller.dto.SinglePlayerResultDto;
import dealer.Dealer;
import dealer.dto.DealerGameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import player.Name;
import player.Player;
import player.Players;
import player.dto.SinglePlayerStatusDto;

public class BlackJackGame {

    private static final String DEALER_NAME = "딜러";

    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackGame() {
        this.cardDeck = new CardDeck();
        this.dealer = new Dealer(cardDeck);
    }

    public Players initGamePlayer(List<String> names) {
        return Players.from(names, cardDeck);
    }

    public Card getInitDealerStatus() {
        return dealer.getCards().getFirstCard();
    }

    public List<SinglePlayerStatusDto> playGame(BiConsumer<SingleMatch, CardDeck> playSingleMatch, Players players) {
        List<SinglePlayerStatusDto> playerResult = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            SingleMatch singleMatch = new SingleMatch(player);
            playerResult.add(singleMatch.play(playSingleMatch, cardDeck));
        }

        return playerResult;
    }

    public SinglePlayerStatusDto playDealerTurn() {
        dealer.getExtraCard(cardDeck);
        return new SinglePlayerStatusDto(new Name(DEALER_NAME), dealer.getCards());
    }

    public List<SinglePlayerResultDto> getPlayersResult(Players players) {
        return players.getPlayers().stream()
                .map(player -> new SinglePlayerResultDto(player.getName(), player.isWinner(dealer.getCardScore())))
                .collect(Collectors.toList());
    }

    public DealerGameResult getDealerResult(Players players) {
        return dealer.getWinningResult(players);
    }
}
