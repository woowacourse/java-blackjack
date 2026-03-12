package blackjack.core;

import blackjack.domain.card.CardsGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.dto.GameResultDto;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDtos;
import blackjack.view.BlackjackView;
import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final BlackjackView view;
    private final CardsGenerator cardsGenerator;

    public BlackjackGame(BlackjackView view, CardsGenerator cardsGenerator) {
        this.view = view;
        this.cardsGenerator = cardsGenerator;
    }

    public void run() {
        Participants participants = Participants.from(view.readPlayers());
        Deck deck = Deck.create(cardsGenerator);

        initialDeal(participants, deck);

        hitPlayers(participants.getPlayers(), deck);
        hitDealer(participants.getDealer(), deck);

        printScore(participants);
        printResult(participants);
    }

    private void initialDeal(Participants participants, Deck deck) {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            deal(participants, deck);
        }
        view.printInitialDeal(InitialDealDtos.from(participants));
    }

    private void deal(Participants participants, Deck deck) {
        participants.stream().forEach(participant -> participant.hit(deck.draw()));
    }

    private void hitPlayers(List<Player> players, Deck deck) {
        players.forEach(player -> hitPlayer(player, deck));
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
            view.printDealerHit(dealer);
        }
    }

    private void printScore(Participants participants) {
        view.printScore(ParticipantScoreDtos.from(participants));
    }

    private void printResult(Participants participants) {
        view.printResult(GameResultDto.from(participants));
    }
}
