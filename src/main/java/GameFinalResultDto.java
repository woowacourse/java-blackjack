public class GameFinalResultDto {
    String name;
    Result result;

    public GameFinalResultDto(String name) {
        this(name, null);
    }

    public GameFinalResultDto(String name, Result result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result.getName();
    }

    @Override
    public String toString() {
        return "GameFinalResultDto{" +
                "name='" + name + '\'' +
                ", result=" + result +
                '}';
    }
}
