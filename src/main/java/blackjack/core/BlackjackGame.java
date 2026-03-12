package blackjack.core;

import blackjack.dto.DealerResultDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.PlayerResultDto;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.GameResult;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.Player;
import blackjack.model.Score;
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
        for (Participant participant : participants) {
            participant.hit(deck.draw());
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
        if (dealer.canHit()) {
            dealer.hit(deck.draw());
            view.printDealerHit(dealer);
        }
    }

    private void printScore(Participants participants) {
        List<ParticipantScoreDto> participantScoreDtos = participants.stream()
            .map(participant -> {
                Score score = participant.getScore();
                return ParticipantScoreDto.from(participant, score);
            })
            .toList();
        view.printScore(participantScoreDtos);
    }

    private void printResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<PlayerResultDto> playerResultDtos = participants.getPlayers().stream()
            .map(player -> {
                    GameResult result = dealer.judgeAgainst(player);
                    return PlayerResultDto.of(player, result);
                }
            ).toList();
        DealerResultDto dealerResultDto = DealerResultDto.from(playerResultDtos);
        view.printResult(new GameResultDto(dealerResultDto, playerResultDtos));
    }
}
