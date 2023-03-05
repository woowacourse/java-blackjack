package blackjack.domain.game;

import blackjack.controller.AddCardOrNot;
import java.util.List;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.dealer.DealerFirstCardDto;
import blackjack.domain.participant.dealer.DealerWinningDto;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.player.Player;
import blackjack.domain.participant.ParticipantCardsDto;
import blackjack.domain.participant.player.PlayerResultDto;
import blackjack.domain.participant.player.PlayerWinningDto;
import blackjack.domain.participant.player.Players;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackjackGame {
    public static final int FIRST_DRAW_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void supplyCardsToDealer() {
        dealer.hit(deck.drawCards(FIRST_DRAW_COUNT));
    }

    public void supplyCardsToPlayers() {
        players.takeCard(deck, FIRST_DRAW_COUNT);
    }

    public void supplyCardToPlayerNameOf(Function<Name, AddCardOrNot> decideAddCardOrNot, Consumer<Player> showPlayerCards) {
        players.hitAdditionalCard(deck, decideAddCardOrNot, showPlayerCards);
    }

    public int countPlayer() {
        return players.count();
    }

    public boolean canDealerHit() {
        return !dealer.isBust() && dealer.isUnderScore();
    }

    public void supplyAdditionalCardToDealer() {
        dealer.hit(deck.drawCard());
    }

    public PlayerResultDto getDealerResult() {
        return PlayerResultDto.fromDealer(dealer);
    }

    public List<PlayerResultDto> getPlayerResults() {
        return players.getPlayerResults();
    }

    public void calculateWinning() {
        players.calculateWinning(dealer);
    }

    public DealerWinningDto getDealerWinningResult() {
        return DealerWinningDto.from(dealer);
    }

    public List<PlayerWinningDto> getPlayerWinningResults() {
        return players.getWinningResults();
    }

    public DealerFirstCardDto getDealerFirstOpen() {
        return DealerFirstCardDto.from(dealer);
    }

    public List<ParticipantCardsDto> getPlayersCards() {
        return players.getPlayerCards();
    }
}
