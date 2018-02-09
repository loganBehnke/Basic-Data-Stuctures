package p9_Package;

/**
 * Simple main class operation to test ArrayHeapClass
 * 
 * @author MichaelL
 *
 */
public class HeapClassMain
   {
      /** 
       * Main method tests heap addition and removal with simple
       * array input and output operations, then tests with class
       * display flag set to observe internal operations
       * 
       * @param args not used
       */
      public static void main(String[] args)
         {
          ArrayHeapClass testClass = new ArrayHeapClass();
          String testValue;
          int index;
          
          for( index = 0; index < 25; index++ )
             {
              testValue = testClass.createHeapDataItem();
              
              System.out.println( "Adding data Value: " 
                                    + testValue + ", Priority: " 
                                      + testClass.getPriority( testValue ) );
              
              testClass.addItem( testValue );
             }

          for( index = 0; index < 25; index++ )
             {
              testValue = testClass.removeItem();
              
              System.out.println( "Data value removed: " + testValue );
             }

          testClass.setDisplayFlag( true );
          
          for( index = 0; index < 25; index++ )
             {
              testValue = testClass.createHeapDataItem();
              
              testClass.addItem( testValue );
             }

          for( index = 0; index < 25; index++ )
             {
              testValue = testClass.removeItem();
             }
         }

   }
