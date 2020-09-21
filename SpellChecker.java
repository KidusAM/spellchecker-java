import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellChecker implements SpellCheckerInterface{

    private HashSet<String> dict = new HashSet<>();
    public SpellChecker(String fileName){

        try{
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            String currentWord = "";
            while(sc.hasNext()){
                currentWord = sc.nextLine().toLowerCase();
                dict.add(currentWord);

            }

        } catch (FileNotFoundException e){
            throw new RuntimeException("Invalid File");
        }


    }


    public List<String> getIncorrectWords(String filename){
        try{
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            ArrayList<String> incorrectWords = new ArrayList<>();
            String line;
            String word;

            while(sc.hasNext()){
                line = sc.nextLine();
                for(String s: line.trim().split("\\s+")){
                    word = clean(s);
                    if ( !dict.contains(word) && !word.equals("") ){
                        incorrectWords.add(word);
                    }
                }
            }
            return incorrectWords;

        } catch(FileNotFoundException e){

            e.printStackTrace();
            throw new RuntimeException("Invalid file to be checked");

        }
    }





    public Set<String> getSuggestions(String word) {
        Set<String> suggestions = new HashSet<>();
        StringBuilder sbWord = new StringBuilder(word);
        String temp = "";

        //Check by inserting a character at every point and deleting one
        for(int i = 0; i <= word.length(); i++){
            for(char c = 'a'; c <= 'z'; c++){
                sbWord = new StringBuilder(word);
                temp = sbWord.insert(i, c).toString();

                if (dict.contains(temp)){
                    suggestions.add(temp);
                }
                sbWord = new StringBuilder(word);
            }
            if(i < word.length()) {
                temp = sbWord.delete(i, i + 1).toString();
                if(dict.contains(temp)) suggestions.add(temp);
            }
        }

        //Check by swapping every value in the string
        char[] chword = word.toCharArray();
        for (int i = 0; i < chword.length-1; i++) {

            swap(chword, i, i+1);
            temp = new String(chword);
            if (dict.contains(temp)) {
                suggestions.add(temp);
            }
            swap(chword, i,i+1);
        }

        return suggestions;
    }





    private String clean(String s){
        return s.trim().replaceAll("[^a-zA-Z0-9]+", "").toLowerCase();
    }

    private void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }





    public String toString() {
        String line = "";
        for (String l : dict){
        line += l + "\n";
        }
        return line;
    }
}
