package p7_Package;

public class Tester
{

   public static void main( String[] args )
   {
      StudentClass.setSortKey( 1 );
      StudentClass.setSortDirKey(4);
      GenericBSTClass<StudentClass> tester = new GenericBSTClass<StudentClass>();
      tester.insert( new StudentClass("Bowen", 12, 'm', 3.0114) );
      tester.insert( new StudentClass("Mike", 11, 'm', 2.9000) );
      tester.insert( new StudentClass("Alen", 14, 'f', 3.5004) );
      tester.insert( new StudentClass("Dlen", 15, 'f', 3.5004) );
      tester.insert( new StudentClass("Hlen", 16, 'f', 3.5004) );
      tester.displayTree( 102 );
      System.out.println( );
      tester.removeNode( new StudentClass("Bowen", 12, 'm', 3.0114) );
      tester.displayTree( 102);
   }

}
