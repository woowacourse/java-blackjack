import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Card {

    private final String value;
    private final String shape;

    public Card(final String value, final String shape) {
        validateValue(value);
        validateShape(shape);
        this.value = value;
        this.shape = shape;
    }

    private static void validateShape(final String shape) {
        List<String> shapes = new ArrayList<>(List.of("스페이드", "클로버", "다이아몬드", "하트"));
        if (!shapes.contains(shape)) {
            throw new NoSuchElementException();
        }
    }

    private static void validateValue(final String value) {
        List<String> values = new ArrayList<>(
                List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
        if (!values.contains(value)) {
            throw new NoSuchElementException();
        }
    }


}
