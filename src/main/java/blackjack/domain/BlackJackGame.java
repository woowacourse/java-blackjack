package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;

import java.util.Map;

public class BlackJackGame {

    private static final int NUMBER_OF_INITIAL_CARD = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final String inputNames) {
        this.dealer = new Dealer();
        this.players = new Players(inputNames);
    }

    public void handOutInitCards(final Deck deck) {
        handOutInitCardsTo(deck, dealer);
        players.getPlayers()
                .forEach(player -> handOutInitCardsTo(deck, player));
    }

    private void handOutInitCardsTo(final Deck deck, final Participant participant) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARD; i++) {
            handOutCardTo(deck, participant);
        }
    }

    public void handOutCardTo(final Deck deck, final Participant participant) {
        final Card card = deck.draw();
        participant.receiveCard(card);
    }

    public void calculateParticipantResult(final DealerResult dealerResult, final PlayerResult playerResult) {
        for (final Player player : players.getPlayers()) {
            playerResult.calculatePlayerResult(player, dealer);
            dealerResult.calculateDealerResult(playerResult.getPlayerResult(player));
        }
    }

    public Map<Player, Money> calculatePlayerProfit(final PlayerResult playerResult, final Map<Player, Money> playerBetting) {
        for (final Player player : players.getPlayers()) {
            final Result result = playerResult.getPlayerResult(player);
            final Money bettingMoney = playerBetting.get(player);
            playerBetting.put(player, bettingMoney.calculateProfit(result.getRate()));
        }
        return playerBetting;
    }

    public Money calculateDealerProfit(final Map<Player, Money> playerProfit) {
        return playerProfit.values().stream()
                .reduce(Money.dealer(), Money::minus);
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
