package blackjack.service;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.model.GameState;
import java.util.List;
import java.util.function.Consumer;

public class BlackjackService {

    public GameState setUp(
            List<String> playerNames,
            List<Integer> betAmounts,
            PickStrategy pickStrategy
    ) {
        if (pickStrategy == null) {
            throw new IllegalArgumentException("pickStrategy가 null입니다.");
        }

        Players players = Players.of(
                playerNames,
                betAmounts
        );
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.of(pickStrategy);

        return new GameState(
                players,
                dealer,
                cardDeck
        );
    }

    public Players distributeInitialCards(GameState gameState) {
        validateGameState(gameState);

        CardDeck cardDeck = gameState.cardDeck();

        Dealer dealer = gameState.dealer();
        dealer.pickInitialCards(cardDeck);

        Players players = gameState.players();
        players.pickInitialCards(cardDeck);

        return players.applyBlackjack();
    }

    public void forEach(Players players, Consumer<Player> consumer) {
        validatePlayers(players);
        players.perform(consumer);
    }

    public Players applyBustToPlayers(Players players) {
        validatePlayers(players);
        return players.applyBust();
    }

    private void validatePlayers(Players players) {
        if (players == null) {
            throw new IllegalArgumentException("players가 null입니다.");
        }
    }

    public void pickAdditionalCard(Player player, CardDeck cardDeck) {
        if (player == null) {
            throw new IllegalArgumentException("player가 null입니다.");
        }

        if (cardDeck == null) {
            throw new IllegalArgumentException("카드 덱이 null입니다.");
        }

        player.pickAdditionalCard(cardDeck);
    }

    public int drawDealerCardUntilSeventeen(GameState gameState) {
        validateGameState(gameState);

        Dealer dealer = gameState.dealer();
        CardDeck cardDeck = gameState.cardDeck();
        int drawnCount = 0;

        while (dealer.canPick()) {
            dealer.pickAdditionalCard(cardDeck);
            drawnCount++;
        }

        return drawnCount;
    }

    public Players awardPlayers(GameState gameState) {
        validateGameState(gameState);

        Players players = gameState.players();
        return players.award(gameState.dealer());
    }

    private void validateGameState(GameState gameState) {
        if (gameState == null) {
            throw new IllegalArgumentException("gameState이 null입니다.");
        }
    }
}
