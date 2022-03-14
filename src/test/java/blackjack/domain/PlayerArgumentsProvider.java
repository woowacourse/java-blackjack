package blackjack.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class PlayerArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                arguments(List.of(new Player("stitch", CardsTestDataGenerator.generateCards()))),
                arguments(List.of(new Player("stitch", CardsTestDataGenerator.generateCards()),
                        new Player("sudal", CardsTestDataGenerator.generateCards())))
        );
    }
}
