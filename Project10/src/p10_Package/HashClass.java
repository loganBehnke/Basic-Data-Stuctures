/**
 * 
 */
package p10_Package;

import java.io.*;

/**
 * Simple hash class that uses an array of BST objects
 * 
 * @author Logan Behnke
 *
 */
public class HashClass
{
   private static final char CARRIAGE_RETURN_CHAR = 13;
   private final int DEFAULT_NUM_HASH_DIGITS = 6;
   private final int DEFAULT_TABLE_SIZE = 10;
   private static final int END_OF_FILE_MARKER = - 1;
   private static final char NEWLINE_CHAR = 10;
   private static final char SEMICOLON = 59;
   private static final char SPACE = 32;
   private static final char TAB_CHAR = 9;
   private static FileReader fileIn;
   private int numHashDigits;
   private SimpleBSTClass[] tableArray;
   private int tableSize;

   /**
    * Default constructor
    * <p>
    * Initializes to default table size and number of hash digits
    */
   public HashClass()
   {
      numHashDigits = DEFAULT_NUM_HASH_DIGITS;
      tableSize = DEFAULT_TABLE_SIZE;
      tableArray = new SimpleBSTClass[ tableSize ];
   }

   /**
    * Initialization constructor
    * 
    * @param inTableSize sets table size
    * 
    * @param inHashDigits sets number of student ID digits to use for hashing
    */
   public HashClass( int inTableSize, int inHashDigits )
   {
      if (inHashDigits > 0 && inHashDigits <= 6)
      {
         numHashDigits = inHashDigits;
      }
      else
      {
         numHashDigits = DEFAULT_NUM_HASH_DIGITS;
      }
      tableSize = inTableSize;
      tableArray = new SimpleBSTClass[ tableSize ];
   }

   /**
    * Adds item to hash table
    * <p>
    * Uses overloaded addItem with object
    * 
    * @param inName name of student
    * @param inStudentID student ID
    * @param inGender gender of student
    * @param inGPA gpa of student
    * @return Boolean success of operation
    */
   public boolean addItem( String inName, int inStudentID, char inGender,
         double inGPA )
   {
      SimpleStudentClass newNode = new SimpleStudentClass( inName, inStudentID,
            inGender, inGPA );
      return addItem( newNode );
   }

   /**
    * Adds item to hash table
    * <p>
    * Overloaded method that accepts SimpleStudentClass object
    * 
    * @param newItem student class object
    * @return Boolean success of operation
    * @throws IndexOutOfBoundsException if hash calculation failure
    */
   public boolean addItem( SimpleStudentClass newItem )
         throws IndexOutOfBoundsException
   {
      int index = generateHash( newItem.studentID );
      if (tableArray[ index ] == null)
      {
         tableArray[ index ] = new SimpleBSTClass();
      }
      return tableArray[ index ].insert( newItem );
   }

   /**
    * Method converts student ID within data value to hash value for use in hash
    * table
    * <p>
    * Note: Method is overloaded, this one can be used with a student class
    * string value that holds the student ID
    * 
    * @param dataString String value contains student data within which the
    *        student ID will be converted to hash value
    * @return integer hash value
    */
   public int generateHash( String dataString )
   {
      int id = getStudentID( dataString );
      return generateHash( id );
   }

   /**
    * Method converts student ID to hash value for use in hash table
    * <p>
    * Note: Method is overloaded, this one can be used with a student ID number
    * 
    * @param studentIDcontains student ID number to be converted to hash value
    *        uses class attribute numHashDigits to define number of student ID
    *        digits used for hash calculation
    * @returninteger hash value
    */
   public int generateHash( int studentID )
   {
      String data = "" + studentID;
      int hash = 0;
      int digits;
      for (digits = data.length()-1; digits >= data.length()
            - numHashDigits; digits--)
      {
         hash += Integer.parseInt( "" + data.charAt( digits ) );
      }
      hash = hash % tableSize;
      return hash;
   }

   /**
    * Traverses through string, finds student ID, returns
    * 
    * @param dataString string through which process traverses
    * @return extracted student ID
    */
   private int getStudentID( String dataString )
   {
      String[] array = dataString.split( "/" );
      String idString = array[ 1 ];
      int idVal = Integer.parseInt( idString );
      return idVal;
   }

   /**
    * Removes item from hash table
    * 
    * @param studentID used for requesting data
    * @return SimpleStudentClass object removed, or null if not found
    * @throws IndexOutOfBoundsException
    */
   public SimpleStudentClass removeItem( int studentID )
         throws IndexOutOfBoundsException
   {
      int index = generateHash( studentID );
      if (index < tableSize)
      {
         System.out.println(
               "Data Removed: " + tableArray[ index ].removeNode( studentID ) );
         return tableArray[ index ].removeNode( studentID );
      }
      return null;
   }

   /**
    * Searches for item in hash table
    * 
    * @param studentID used for requesting data
    * @return SimpleStudentClass object removed, or null if not found
    */
   public SimpleStudentClass findItem( int studentID )
   {
      int hash = generateHash( studentID );
      if (hash < tableSize)
      {
         if (tableArray[ hash ].search( studentID ) != null)
         {
            System.out.println(
                  "Data Found: " + tableArray[ hash ].search( studentID ) );
            return tableArray[ hash ].search( studentID );
         }
         System.out.println( "Data Not Found for ID: " + studentID );
         return null;
      }
      System.out.println( "Hash Error for ID: " + studentID );
      return null;
   }

   /**
    * traverses through all array bins, finds heights of each tree, then
    * displays as table
    * <p>
    * Shows table of tree heights, then shows table size and number of digits of
    * the student ID used for hashing, then shows the number of empty bins and
    * the tallest tree height of the set
    */
   public void showHashTableStatus()
   {
      int index;
      int emptyBins = 0;
      int tallestTree = 0;

      System.out.print( "Index:" + TAB_CHAR );
      for (index = 0; index < tableSize; index++)
      {
         System.out.print( "  " + index + "  " + TAB_CHAR );
      }
      System.out.println();
      System.out.print( "" + TAB_CHAR );
      for (index = 0; index < tableSize; index++)
      {
         System.out.print( "-----" + TAB_CHAR );
      }
      System.out.println();
      System.out.print( "" + TAB_CHAR );
      for (index = 0; index < tableSize; index++)
      {
         if (tableArray[ index ] == null
               || tableArray[ index ].getTreeHeight() == - 1)
         {
            System.out.print( "   " + "*" + " " + TAB_CHAR );
            emptyBins++;
         }
         else
         {
            System.out.print( "   " + tableArray[ index ].getTreeHeight() + " "
                  + TAB_CHAR );
            if (tableArray[ index ].getTreeHeight() > tallestTree)
            {
               tallestTree = tableArray[ index ].getTreeHeight();
            }
         }
      }
      System.out.println( "\n" );
      System.out.println( "With table size of " + tableSize + ", and "
            + numHashDigits
            + " student ID digits used,\nThe number of empty bins was "
            + emptyBins + ", and the tallest tree height was " + tallestTree );

   }

   /**
    * Local method uploads data character by character, parses characters, and
    * loads into hash data structure
    * <p>
    * Exception: If there is a file failure such as file not found, method will
    * return false
    * 
    * @param fileName name of file in local directory required for upload
    * @return returns Boolean evidence of success
    */
   public boolean loadDataFromFile( String fileName )
   {
      String name, idStr, gpaStr, dataString, genderStr;
      char gender;
      double gpa;
      int id;
      try
      {
         System.out.println( "Loading Data from File\n" );
         fileIn = new FileReader( fileName );
         name = getStringFromFile( SEMICOLON );
         while (name != "")
         {

            idStr = getStringFromFile( SEMICOLON );
            genderStr = getStringFromFile( SEMICOLON );
            gpaStr = getStringFromFile( NEWLINE_CHAR );

            id = Integer.parseInt( idStr );
            gpa = Double.parseDouble( gpaStr );
            gender = genderStr.charAt( 0 );
            addItem( name, id, gender, gpa );
            name = getStringFromFile( SEMICOLON );
         }
         return true;

      }
      catch (IOException ioe)
      {
         return false;
      }

   }

   /**
    * Clears hash table by clearing all trees
    */
   public void clearHashTable()
   {
      int index;
      System.out.println( "\nTree Report before clearing" );
      showHashTableStatus();
      for (index = 0; index < tableSize; index++)
      {
         tableArray[ index ].clearTree();
      }
      System.out.println( "\nTree Report after clearing" );
      showHashTableStatus();
   }

   /**
    * Local method for getting a string with specified end characters, ignoring
    * most white space
    * 
    * @param endChar flag character to end input
    * @return integer character for use in input process
    * @throws IOException
    */
   private String getStringFromFile( char endChar ) throws IOException
   {
      String data = "";
      int value;
      value = fileIn.read();

      while (value != endChar && value != END_OF_FILE_MARKER)
      {
         if (value != TAB_CHAR)
         {
            data += ( char ) value;
         }
         value = fileIn.read();
      }
      return data;
   }

   /**
    * Indicates whether a given character is found in a given string
    * 
    * @param testChar character to be tested in the string
    * @param str string given for the character search
    * @return
    */
   private boolean charInString( char testChar, String str )
   {
      int index;
      for (index = 0; index < str.length(); index++)
      {
         if (str.charAt( index ) == testChar)
         {
            return true;
         }
      }
      return false;
   }

}
