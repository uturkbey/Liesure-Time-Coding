import java.io.*;
import java.util.*;

/*
 * This class is for calculating the maximum sum of the numbers in a triangle along a path according to given rules below;
 1. You will start from the top and move downwards to an adjacent number as in below.
 2. You are only allowed to walk downwards and diagonally.
 3. You can only walk over NON PRIME NUMBERS.
 4. You have to reach at the end of the triangle as much as possible.
 5. You have to treat your input as triangle.
 * @Author Utku Turkbey
 */
public class MaxPathSumOfTriangle 
{
   /*
    * Type the name of the data file and program will prompt the maximum sum
    */
   public static void main(String[] args) throws FileNotFoundException 
   {
      Scanner input = new Scanner(System.in);
      System.out.print("Enter data file name:"); //Asking for data file name 
      System.out.println();
      System.out.println("Max sum along the path according to specified rules = " + getMaxPathSum(getTriangle(input.next()))); //Reading/Calculting Max/printing Result
   }
   /*
    * This method read numbers from a data file and records them to a 2D array and returns it. If a prime number appears, it will be transferred as 0 to array.
    * @param dataFile is the name of the file to parse data from
    * @return the file data in a 2D array
    */
   public static int[][] getTriangle(String dataFile) throws FileNotFoundException
   {
      //initializng necessary variables
      Scanner input = new Scanner(new File(dataFile));
      ArrayList<Integer> numbers = new ArrayList<Integer>();
      //Reading numbers from text file 
      while (input.hasNextInt())
      {
         numbers.add(input.nextInt());
      }
      //initializing 2D array to store numbers of triangle
      int size = numbers.size();
      size = ((int)Math.sqrt(8 * numbers.size() + 1) - 1) / 2; //This is formula relating the number of elements and number of rows in a triangle
      int[][] triangle = new int[size][size];
      //transfreeing elements from ArrayList to 2D Array, setting prime numbers to 0
      for(int i = 0; i < size; i++)
      {
         for (int j = 0; j <= i ; j++)
         {
            int element = numbers.remove(0);
            if (isPrime(element))
            {
               triangle[i][j] = 0;
            }
            else
            {
               triangle[i][j] = element;
            }
         }
      }
      return triangle; 
   }
   
   /*
    * Thi method calculates max path sum of a triangle based on specified rules
    * @param triangle is 2D array of integers of triangle
    * @return max sum
    */
   public static int getMaxPathSum(int[][] triangle )
   {
      for (int i = triangle.length - 1; i > 0; i--) //Traversing 2D array upward since while traveling downward we don't know how our current choice will effect future choices
      {
         for (int j = 0; j < i; j++)
         {
            if (triangle[i - 1][j] == 0)
            {
               //Do nothing
            }   
            else
            {
               triangle[i - 1][j] = triangle[i - 1][j] + Math.max(triangle[i][j], triangle[i][j+1]);
            }
         }
      }
      return triangle[0][0];
   }
   
   /*
    * This is a very common and inefficient but 100% true prime detection algorithm
    * @param n is the number to be checked
    * @return true if prime, false otherwise
    */
   public static boolean isPrime(int n) 
   {
      if (n <= 1) 
      {
         return false;
      }
      if (n == 2) 
      {
         return true;
      }
      for (int i = 2; i <= Math.sqrt(n) + 1; i++)
      {
         if (n % i == 0) 
         {
            return false;
         }
      }
      return true;
   }
}