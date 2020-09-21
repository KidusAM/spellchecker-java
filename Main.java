import java.util.List;

public class Main {

    public static void main(String[] args) {

        SpellChecker sc = new SpellChecker("words.txt");
        List<String> l = sc.getIncorrectWords("test.txt");
        for(String incorrect: l) {
            System.out.println("For incorrect \"" + incorrect + "\": " + sc.getSuggestions(incorrect));
        }

    }
}
