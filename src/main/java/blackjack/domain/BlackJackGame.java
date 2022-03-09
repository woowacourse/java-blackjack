package blackjack.domain;

import blackjack.dto.PlayerInfo;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final List<Player> players;

    private BlackJackGame(final CardDeck cardDeck, final Dealer dealer, final List<Player> players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackGame init(final List<String> playerNames) {
        final CardDeck cardDeck = CardDeck.init();
        final Dealer dealer = new Dealer(cardDeck.provideInitCards());
        final List<Player> players = playerNames.stream()
                .map(name -> new Player(name, true, cardDeck.provideInitCards()))
                .collect(Collectors.toList());
        return new BlackJackGame(cardDeck, dealer, players);
    }

    public PlayerInfo getInitDealerInfo() {
        return PlayerInfo.dealerToInfo(dealer);
    }

    public List<PlayerInfo> getInitPlayerInfo() {
        return players.stream()
                .map(PlayerInfo::playerToInfo)
                .collect(Collectors.toList());
    }
}
