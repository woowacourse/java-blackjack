package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Result;
import blackjack.dto.AllPlayersStatusWithPointDto;
import blackjack.dto.ChallengerNameAndMoneyDto;
import blackjack.dto.ProfitDto;

import java.util.List;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Players players;

    private BlackJackGame(final CardDeck cardDeck, final Players players) {
        this.cardDeck = cardDeck;
        this.players = players;
    }

    public static BlackJackGame from(final List<ChallengerNameAndMoneyDto> challengerNameAndMoneyDtos) {
        Players players = Players.from(challengerNameAndMoneyDtos);
        CardDeck cardDeck = CardDeck.create();
        cardDeck.shuffle();
        return new BlackJackGame(cardDeck, players);
    }

    public void handOutStartCards() {
        players.pickStartCards(cardDeck);
    }

    public boolean canPick(final Player player) {
        return player.canPick();
    }

    public boolean isDealerCanPick() {
        return getDealer().canPick();
    }

    public boolean isPlayerBlackjack(final Player player) {
        return player.isBlackjack();
    }

    public void pick(final Player player) {
        Card pickedCard = cardDeck.pick();
        player.pick(pickedCard);
    }

    public void takeDealerTurn() {
        Player dealer = getDealer();
        if (dealer.canPick()) {
            pick(dealer);
        }
    }

    public AllPlayersStatusWithPointDto getFinalResult() {
        return AllPlayersStatusWithPointDto.of(players);
    }

    public ProfitDto calculateProfit() {
        Result result = Result.from(players);
        return ProfitDto.of(result, players);
    }

    public Player getDealer() {
        return players.getDealer();
    }

    public List<Challenger> getChallengers() {
        return players.getChallengers();
    }
}
