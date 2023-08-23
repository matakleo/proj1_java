package capers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = join(CWD,".capers"); // TODO Hint: look at the `join`

    static File story = join(CAPERS_FOLDER,"story.txt");


                                            //      function in Utils

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {

        CAPERS_FOLDER.mkdir();
        Dog.DOG_FOLDER.mkdir();
        /** this is the way of making new files / directoreis */
        try {
            story.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // TODO
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        String temp_text;

        temp_text=readContentsAsString(story);
        Utils.writeContents(story, temp_text+text+"\n");
        temp_text=readContentsAsString(story);
        System.out.println(temp_text);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog temp_dog = new Dog(name,breed,age);
        temp_dog.saveDog();
        System.out.println(temp_dog.toString());

    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog d = Dog.fromFile(name);
        d.haveBirthday();
        d.saveDog();
        // TODO
    }
}
