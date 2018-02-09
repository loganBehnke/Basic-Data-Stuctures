
package p10_Package;

public class SimpleBSTClassTester
{

   public static void main( String[] args )
   {
      SimpleBSTClass tester = new SimpleBSTClass();
      System.out.println( tester.getTreeHeight() );
      tester.insert( new SimpleStudentClass( "Bowen", 10, 'm', 3.0114 ) );
      System.out.println( tester.getTreeHeight() );
      tester.insert( new SimpleStudentClass( "Mike", 11, 'm', 2.9000 ) );
      System.out.println( tester.getTreeHeight() );
      tester.insert( new SimpleStudentClass( "Alen", 9, 'f', 3.5004 ) );
      System.out.println( tester.getTreeHeight() );
      tester.insert( new SimpleStudentClass( "Dylan", 12, 'f', 3.5004 ) );
      System.out.println( tester.getTreeHeight() );
      // tester.displayTree( 102 );
      // System.out.println( "\n" );
      tester.removeNode( 12 );
      // tester.displayTree( 102 );
      tester.search( 11 );
      System.out.println( tester.getTreeHeight() );
      System.out.println();
      HashClass hash = new HashClass(8, 5);
      hash.loadDataFromFile( "inData.txt" );
      hash.findItem( 488133 );
      hash.findItem( 419808 );
      hash.removeItem( 653875 );
      hash.removeItem( 193833 );
      hash.removeItem( 729716 );
      hash.clearHashTable();
   }

}
