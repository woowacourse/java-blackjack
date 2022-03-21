package blackjack.domain.player;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import blackjack.domain.BettingMoney;
import blackjack.domain.CardsTestDataGenerator;
import blackjack.domain.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class PlayerArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                arguments(List.of(new Player("stitch",new BettingMoney(1000), CardsTestDataGenerator.generateBlackJackCards()))),
                arguments(List.of(new Player("stitch",new BettingMoney(2500), CardsTestDataGenerator.generateBlackJackCards()),
                        new Player("sudal",new BettingMoney(1500), CardsTestDataGenerator.generateBlackJackCards())))
        );
    }
}
