package p4_Package;

/**
 * Main driver method for testing recursive backtracking operations
 * 
 * @author Michael Leverington
 *
 */
public class ContainerBoxClassMain
   {

      /**
       * Driver main method
       * 
       * @param args String arguments unused for this operation
       * 
       */
      public static void main(String[] args)
         {
          ContainerBoxClass testContainer = new ContainerBoxClass( 15, 12 );
          
          testContainer.setDisplayFlag( false );
/*
        // Small data set for 10 x 10 container box
          testContainer.addBoxToList( 4, 4 );
          testContainer.addBoxToList( 6, 7 );
          testContainer.addBoxToList( 4, 6 );
          testContainer.addBoxToList( 3, 6 );         
*/
/*        // data set  
          testContainer.addBoxToList( 4, 5 );
          testContainer.addBoxToList( 2, 3 );
          testContainer.addBoxToList( 2, 4 );
          testContainer.addBoxToList( 5, 7 );
          testContainer.addBoxToList( 7, 6 );
          testContainer.addBoxToList( 5, 5 );
          testContainer.addBoxToList( 5, 3 );
          testContainer.addBoxToList( 3, 3 );
          testContainer.addBoxToList( 4, 3 );
          testContainer.addBoxToList( 4, 2 );
*/
          
/*        // data set
          testContainer.addBoxToList( 3, 3 );
          testContainer.addBoxToList( 4, 3 );
          testContainer.addBoxToList( 4, 2 );
          testContainer.addBoxToList( 4, 5 );
          testContainer.addBoxToList( 2, 3 );
          testContainer.addBoxToList( 2, 4 );
          testContainer.addBoxToList( 5, 7 );
          testContainer.addBoxToList( 7, 6 );
          testContainer.addBoxToList( 5, 5 );
          testContainer.addBoxToList( 5, 3 );
*/
          
          testContainer.addBoxToList( 7, 6 ); //b
          testContainer.addBoxToList( 5, 5 ); //c
          testContainer.addBoxToList( 5, 3 ); //d
          testContainer.addBoxToList( 3, 3 ); //e
          testContainer.addBoxToList( 4, 3 ); //f
          testContainer.addBoxToList( 4, 2 ); //g
          testContainer.addBoxToList( 4, 5 ); //h
          testContainer.addBoxToList( 2, 3 ); //i
          testContainer.addBoxToList( 2, 4 ); //j
          testContainer.addBoxToList( 5, 7 ); //k
          
          if( testContainer.fillContainerBox() )
             {
              System.out.println( "Packing solution found." );
             }
          
          else
             {
              System.out.println( "Packing solution not found" );
             }
          
          testContainer.setDisplayFlag( true );
          testContainer.displayField();

         }

   }
