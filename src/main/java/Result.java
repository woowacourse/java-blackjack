public enum Result {
    WIN("승"), DRAW("무"), LOSE("패");

    private String name;

    Result(String name) {
        this.name = name;
    }

    public String getName() {
        return name + " ";
    }
}
