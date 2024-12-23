package newpackage;

//hadeel alharthi 2210794
import java.util.Arrays;
import java.util.Random;

public class loadfactor {

    //close hashing function
    public static int[] loadfactor(int[] input_keys, int hash_table_size) {

        // Initialize the hash table with -1
        int[] hash_table = new int[hash_table_size];
        Arrays.fill(hash_table, -1);

        //hash function mod(key,M)
        for (int key : input_keys) {
            int index = key % hash_table_size; //calculate hash index

            // Resolve collisions using linear probing
            while (hash_table[index] != -1) {
                index = (index + 1) % hash_table_size; // Move to the next slot
            }

            hash_table[index] = key; // put the key in the hash table
        }

        return hash_table; // Return the final hash table

    }

    // Search for a key in the hash table and count comparisons
    public static int searchKey(int[] hash_table, int key) {
        int index = key % hash_table.length;
        int comparisons = 0;

        // Search using linear probing
        while (hash_table[index] != -1) {
            comparisons++;
            if (hash_table[index] == key) {
                return comparisons; // Key found
            }
            index = (index + 1) % hash_table.length;
        }
        return comparisons; // Key not found

    }

    public static void main(String[] args) {

        int N = 10; // Number of input keys
        int M = 2 * N; // Hash table size for alpha = 0.5
        int[] input_keys = new Random().ints(N, 1, 100).toArray();

        // Create the hash table
        int[] hash_table = loadfactor(input_keys, M);

        System.out.println("\nIteration #           # of Comparisons");
        System.out.println("-------------------------------------");
        int totalComparisons = 0;

        // Search random keys and count comparisons
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            int randomKey = random.nextInt(100);
            int comparisons = searchKey(hash_table, randomKey);
            System.out.printf("%10d %20d\n", i, comparisons);
            totalComparisons += comparisons;
        }

    }

}
