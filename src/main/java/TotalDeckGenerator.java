import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TotalDeckGenerator {
    public List<Card> generate(){
        List<Shape> shapes = Shape.giveShapes();
        List<Number> numbers = Number.giveNumbers();
        return shapes.stream()
                .flatMap(shape ->numbers.stream()
                        .map(number -> new Card(shape,number)))
                .collect(Collectors.toList());
    }
}
