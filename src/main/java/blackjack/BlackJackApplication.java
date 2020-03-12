package blackjack;

import blackjack.domain.card.CardFactory;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.rule.HandInitializer;
import blackjack.domain.rule.PlayerAnswer;
import blackjack.dto.GamersResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.generate());
        Dealer dealer = new Dealer();
        Players players = Players.ofComma(InputView.askPlayerNames());

        HandInitializer.initialize(dealer, players, deck);
        OutputView.printInitialCards(dealer, players);

        drawMoreCard(dealer, players, deck);
        OutputView.printGamerScore(dealer, players);

        GamersResultDto gamersResultDto = GamersResultDto.of(dealer, players);
        OutputView.printGamersResult(gamersResultDto);
    }

    private static void drawMoreCard(Dealer dealer, Players players, Deck deck) {
        hitOrStandForPlayers(players, deck);
        drawCardForDealer(dealer, deck);
    }

    private static void hitOrStandForPlayers(Players players, Deck deck) {
        for (Player player : players) {
            hitOrStand(player, deck);
        }
    }

    private static void drawCardForDealer(Dealer dealer, Deck deck) {
        while (dealer.shouldDrawCard()) {
            dealer.draw(deck.pick());
            OutputView.printDealerDrewCard();
        }
    }

    private static void hitOrStand(Player player, Deck deck) {
        while (!player.isBusted() && wantMoreCard(player)) {
            player.draw(deck.pick());
            OutputView.printPlayerHand(player);
        }
    }

    private static boolean wantMoreCard(Player player) {
        PlayerAnswer answer = PlayerAnswer.of(InputView.askHitOrStand(player.getName()));
        return answer.isYes();
    }
}
