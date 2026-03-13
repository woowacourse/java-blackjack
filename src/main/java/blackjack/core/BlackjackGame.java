package blackjack.core;

import blackjack.domain.card.CardsGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.PlayerGroup;
import blackjack.domain.participant.Player;
import blackjack.dto.GameResultDtos;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDtos;
import blackjack.view.BlackjackView;

public class BlackjackGame {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final BlackjackView view;
    private final CardsGenerator cardsGenerator;

    public BlackjackGame(BlackjackView view, CardsGenerator cardsGenerator) {
        this.view = view;
        this.cardsGenerator = cardsGenerator;
    }

    public void run() {
        PlayerGroup playerGroup = PlayerGroup.from(view.readPlayers());
        Dealer dealer = Dealer.create();
        Deck deck = Deck.create(cardsGenerator);

        initialDeal(dealer, playerGroup, deck);

        hitPlayers(playerGroup, deck);
        hitDealer(dealer, deck);

        printScore(dealer, playerGroup);
        printResult(dealer, playerGroup);
    }

    private void initialDeal(Dealer dealer, PlayerGroup playerGroup, Deck deck) {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            dealer.hit(deck.draw());
            playerGroup.deal(deck);
        }
        view.printInitialDeal(InitialDealDtos.of(dealer, playerGroup));
    }

    private void hitPlayers(PlayerGroup playerGroup, Deck deck) {
        playerGroup.players().forEach(player -> hitPlayer(player, deck));
    }

    private void hitPlayer(Player player, Deck deck) {
        while (player.canHit() && view.isHitAnswer(player.getName())) {
            player.hit(deck.draw());
            view.printPlayerCards(ParticipantCardsDto.from(player));
        }
    }

    private void hitDealer(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            dealer.hit(deck.draw());
            view.printDealerHit(dealer.getName());
        }
    }

    private void printScore(Dealer dealer, PlayerGroup playerGroup) {
        view.printScore(ParticipantScoreDtos.of(dealer, playerGroup));
    }

    private void printResult(Dealer dealer, PlayerGroup playerGroup) {
        view.printResult(GameResultDtos.of(dealer, playerGroup));
    }
}
