package Ulpgc.Itzhak;

public interface Command {
    Output excute(Input input);
    interface Input{
        String get(String parameter);
    }
    interface Output{
        int responseCode();
        String result();
    }
}
