package p5_Package;

public class UtilityClassTester
{

   public static void main( String[] args )
   {
      UtilityClass test = new UtilityClass( 3, 102 );
      test.qEnqueue( 1 );
      test.qEnqueue( 2 );
      test.qEnqueue( 3 );
      test.qEnqueue( 4 );
      System.out.println( test.qDequeue());
      System.out.println( test.qDequeue());
      System.out.println( test.qDequeue());
      System.out.println( test.qDequeue());
   }

}
