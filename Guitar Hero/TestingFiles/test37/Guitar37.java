import java.util.*;
public class Guitar37 implements Guitar {
   private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   private GuitarString[] strings;
   private int time;

   /**
    * Constructs a Guitar37 object.
    * Initializes the array of GuitarString objects with the appropriate frequencies.
    */
   public Guitar37() {
      strings = new GuitarString[37];
      for (int i = 0; i < KEYBOARD.length(); i++) {
         double frequency = 440.0 * Math.pow(2, (i - 24) / 12.0);
         strings[i] = new GuitarString(frequency);
      }
      time = 0;
   }

   /**
    * Plays a note with a given pitch.
    * The pitch is converted to the corresponding index in the strings array.
    * 
    * @param pitch the pitch of the note to be played
    */
   public void playNote(int pitch) {
      int index = pitch + 24;
      if (index >= 0 && index < strings.length) {
         strings[index].pluck();
      }
   }

   /**
    * Checks if the guitar has a string for the given key.
    * 
    * @param key the key to check
    * @return true if the guitar has a string for the key, false otherwise
    */
   public boolean hasString(char key) {
      return KEYBOARD.indexOf(key) >= 0;
   }

   /**
    * Plucks the string corresponding to the given key.
    * 
    * @param key the key of the string to be plucked
    * @throws IllegalArgumentException if the key is not valid
    */
   public void pluck(char key) {
      int index = KEYBOARD.indexOf(key);
      if (index == -1) {
         throw new IllegalArgumentException("Key not valid");
      }
      strings[index].pluck();
   }

   /**
    * Returns the current sample, which is the sum of all string samples.
    * 
    * @return the sum of all string samples
    */
   public double sample() {
      double sum = 0.0;
      for (GuitarString string : strings) {
         sum += string.sample();
      }
      return sum;
   }

   /**
    * Advances the simulation one time step by calling tic on each GuitarString.
    * Increments the time variable by one.
    */
   public void tic() {
      for (GuitarString string : strings) {
         string.tic();
      }
      time++;
   }

   /**
    * Returns the current time, which is the number of times tic has been called.
    * 
    * @return the current time
    */
   public int time() {
      return time;
   }
}
