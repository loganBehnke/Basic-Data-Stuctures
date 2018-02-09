package p10_Package;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HashMainTest
{
   public static void main( String[] args )
   {

      ArrayList<String> lines = readFile( "src/nameData.txt" );
      ArrayList<String> names = new ArrayList<>();
      for (String line : lines)
      {
         String name = line.split( ";" )[ 0 ];
         names.add( name );
      }

      for (String name : names)
      {
         try
         {
            int hashID = hashName( name, 5 );
            outputData( name, hashID );
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }

      }

   }

   public static ArrayList<String> readFile( String fileName )
   {
      String lineStr;
      BufferedReader in;
      FileReader fileIn;
      // open the file

      ArrayList<String> list = new ArrayList<String>();

      try
      {
         fileIn = new FileReader( fileName );

         in = new BufferedReader( fileIn );

         try
         {
            while ( ( lineStr = in.readLine() ) != null)
            {
               list.add( lineStr );
            }
         }
         catch (IOException e)
         {
            System.out.println( "No lines to read" );
         }
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return list;
   }

   public static int hashName( String name, int num )
   {
      int hash = 0;
      int lastNameNum = num;
      int firstNameNum = 6 - num;
      int index;

      String temp[] = name.split( "," );

      String lastName = temp[ 0 ];
      String firstName = temp[ 1 ];

      if (lastName.length() < num)
      {
         lastNameNum = lastName.length();
         firstNameNum = 6 - num;
      }

      for (index = 0; index < lastNameNum; index++)
      {

         hash += ( int ) lastName.charAt( index );
      }

      for (index = 0; index < firstNameNum; index++)
      {
         hash += ( int ) firstName.charAt( index );
      }
      hash *= 789;
      System.out.println( hash );

      while (hash > 1000000)
      {
         hash -= 100000;
      }
      return hash;
   }

   public static void outputData( String name, int hash ) throws IOException
   {
      FileWriter writeTo = new FileWriter( "classData.txt", true );

      BufferedWriter buffer = new BufferedWriter( writeTo );

      double gpa = Math.random();

      while (gpa > 0.4)
      {
         gpa = Math.random();
      }
      gpa *= 10;

      double gender = Math.random();

      char genderC;

      if (gender > 0.5)
      {
         genderC = 'M';

      }

      else
      {
         genderC = 'F';
      }

      String string = name + ";\t\t\t" + hash + "; " + genderC + ";\t" + gpa
            + '\n';

      buffer.write( string );

      buffer.close();
   }

}
