package HW07;

import java.util.Random;

public class WordLibrary {

    private String[] library;
    private int seed;
    private Random random;

    public WordLibrary(String[] library, int seed) {
        this.library = library;
        this.seed = seed;
        this.random = new Random(seed);
        cleanLibrary();
    }

    public void cleanLibrary() {
        for (int i = 0; i < library.length; i++) {
            if (library[i].length() != 5) {
                for (int j = i; j < library.length; j++) {
                    if ((j + 1) < library.length){
                        library[j] = library[j + 1];
                    } else {
                        library[j] = "";
                    }
                }
            }
        }

    }

    public String chooseWord() {
        return library[random.nextInt(library.length)];
    }

    public String[] getLibrary(String[] wordsArray) {
        return library;
    }

    public void setLibrary(String[] library) {
        this.library = library;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}

