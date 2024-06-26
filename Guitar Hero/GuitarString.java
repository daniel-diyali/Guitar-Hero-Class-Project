import java.util.*;
public class GuitarString {
   private Queue<Double> ringbuffer;
   private int size;
   private double decay;

   /**
    * Constructs a GuitarString of the given frequency.
    * Initializes the ring buffer with the correct size and sets it to zeros.
    * 
    * @param frequency the frequency of the string
    * @throws IllegalArgumentException if the frequency is less than or equal to 0,
    *                                  or if the calculated buffer size is less than 2
    */
   public GuitarString(double frequency) {
      ringbuffer = new LinkedList<>();
      if (frequency <= 0) {
         throw new IllegalArgumentException("Frequency must be greater than 0.");
      }
      
      size = Math.round((float)(StdAudio.SAMPLE_RATE / frequency));
      
      if (size < 2) {
         throw new IllegalArgumentException("Buffer size must be at least 2.");
      }
      
      for (int i = 0; i < size; i++) {
         ringbuffer.add(0.0);
      }
   }

   /**
    * Constructs a GuitarString with initial values provided in an array.
    * Initializes the ring buffer with the given values.
    * 
    * @param init an array of initial values
    * @throws IllegalArgumentException if the array length is less than 2
    */
   public GuitarString(double[] init) {
      ringbuffer = new LinkedList<>();
      if (init.length < 2) {
         throw new IllegalArgumentException("Array length must be at least 2.");
      }
      
      size = init.length;
      
      for (double value : init) {
         ringbuffer.add(value);
      }
   }

   /**
    * Plucks the guitar string by replacing the buffer with random values between -0.5 and 0.5.
    */
   public void pluck() {
      Random rand = new Random();
      for (int i = 0; i < size; i++) {
         ringbuffer.remove();
         ringbuffer.add(rand.nextDouble() - 0.5);
      }
   }

   /**
    * Advances the simulation one time step by performing one iteration
    * of the Karplus-Strong algorithm.
    */
   public void tic() {
      decay = 0.996;
      ringbuffer.add(((ringbuffer.remove() + ringbuffer.peek()) * 0.5) * decay);
   }

   /**
    * Returns the current sample (the value at the front of the ring buffer).
    * 
    * @return the current sample
    */
   public double sample() {
      return ringbuffer.peek();
   }
}
