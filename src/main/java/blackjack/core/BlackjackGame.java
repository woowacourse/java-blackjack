package blackjack.core;

import blackjack.dto.DealerResultDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.GameResultDtos;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.GameResult;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.Player;
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
        participants.getDealer().hit(deck.draw());
        for (Player player : participants.getPlayers()) {
            player.hit(deck.draw());
        }
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
        List<ParticipantScoreDto> participantScoreDtos = participants.stream()
            .map(this::convertFrom)
            .toList();
        view.printScore(participantScoreDtos);
    }

    private void printResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<GameResultDtos> gameResultDtos = participants.getPlayers().stream()
            .map(player -> convertFrom(player, dealer))
            .toList();
        DealerResultDto dealerResultDto = DealerResultDto.from(gameResultDtos);
        view.printResult(new GameResultDto(dealerResultDto, gameResultDtos));
    }

    private ParticipantScoreDto convertFrom(Participant participant) {
        return ParticipantScoreDto.from(participant, participant.getScore());
    }

    private GameResultDtos convertFrom(Player player, Dealer dealer) {
        GameResult result = dealer.judgeAgainst(player);
        return GameResultDtos.of(player, result);
    }
}
