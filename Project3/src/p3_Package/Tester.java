package p3_Package;

public class Tester
{

   public static void main( String[] args )
   {
      ArrayClass test = new ArrayClass();
      //test.resize(15 );
      test.loadUniqueRandoms( 60, 0, 60 );
      System.out.println("Unsorted");
      for(int i = 0; i < test.getCurrentSize(); i++) {
         System.out.print( test.accessItemAt( i ) + ", " );
      }
      System.out.print( "\n" );
      test.runQuickSort();
      System.out.println("Sorted");
      for(int i = 0; i < test.getCurrentSize(); i++) {
         System.out.print( test.accessItemAt( i ) +", " );
      }
      System.out.print( "\n" );
   }

}
