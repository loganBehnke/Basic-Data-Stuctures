package p4_Package;

import java.io.IOException;

/**
 * manages a container box into which smaller boxes are placed using recursive
 * backtracking
 * 
 * @author Logan Behnke
 *
 */
public class ContainerBoxClass
{
   private BoxClass[] boxList;
   private static int CLEAR_BOX = 102;
   private char[][] containerBoxField;
   private int containerBoxHeight;
   private int containerBoxWidth;
   private static char DEFAULT_FIELD_CHAR = 45;
   private boolean displayFlag;
   private static int FILL_BOX = 101;
   private static int MAX_NUM_BOXES = 26;
   private static int NO_BOXES_AVAILABLE = - 1;
   private int numBoxesAvailable;

   /**
    * Initialization constructor
    * 
    * @param initBoxWidth Initial width of container box
    * @param initBoxHeight Initial height of container box
    */
   public ContainerBoxClass( int initBoxWidth, int initBoxHeight )
   {
      containerBoxHeight = initBoxHeight;
      containerBoxWidth = initBoxWidth;
      containerBoxField = new char[ containerBoxWidth ][ containerBoxHeight ];
      int height,width;
      for (height = 0; height < containerBoxHeight; height++)
      {
         for (width = 0; width < containerBoxWidth; width++)
         {
            containerBoxField[ width ][ height ] = DEFAULT_FIELD_CHAR;
         }
      }
      boxList = new BoxClass[ MAX_NUM_BOXES ];
      numBoxesAvailable = 0;
   }

   /**
    * Adds a new box to the list of boxes to be placed into the container box
    * 
    * @param boxWidth width of box to be added
    * @param boxHeight height of box to be added
    * @return Boolean success, or failure if box array is full
    */
   public boolean addBoxToList( int boxWidth, int boxHeight )
   {
      if (numBoxesAvailable < MAX_NUM_BOXES)
      {
         BoxClass temp = new BoxClass( boxWidth, boxHeight );
         boxList[ numBoxesAvailable ] = temp;
         numBoxesAvailable++;
         return true;
      }
      return false;
   }
   

   /**
    * Checks to see if a box can be placed within the container starting at the
    * test location provided
    * <p>
    * Note: Tests from lower left corner across then up to check for available
    * area
    * 
    * @param testLocation lower left corner location to start testing for
    *        available space within the container box
    * @param testBox box to be checked for fit
    * @return Boolean result of test
    */
   private boolean checkForFitInField( PointClass testLocation,
         BoxClass testBox )
   {
      int startHeight = testLocation.getYPos();
      int startWidth = testLocation.getXPos();
      int boxWidth = testBox.getWidth();
      int boxHeight = testBox.getHeight();
      if (startHeight - boxHeight < containerBoxHeight
            && startWidth + boxWidth - 1  < containerBoxWidth)
      {
         int height, width;
         for (height = startHeight; height > startHeight - boxHeight && height >=0; height--)
         {
            for (width = startWidth; width < startWidth + boxWidth; width++)
            {
               if (containerBoxField[ width ][ height ] != DEFAULT_FIELD_CHAR)
               {
                  return false;
               }
            }
         }
         return true;
      }
      return false;
   }
   

   /**
    * Displays entire container box with any boxes currently within container
    */
   public void displayField()
   {
      if (displayFlag)
      {
         int height, width;
         for (height = 0; height < containerBoxHeight; height++)
         {
            for (width = 0; width < containerBoxWidth; width++)
            {
               System.out.print( containerBoxField[ width ][ height ] );
            }
            System.out.println();
         }
      }
   }

   /**
    * Fills container box at given location with box letter; if clear flag is
    * set, sets container box with default character
    * <p>
    * Note: Paints box from lower left corner across then up
    * 
    * @param boxLocation PointClass object location of fill starting point
    * @param fillBox BoxClass object containing width, height, and ID letter to
    *        be used for filling container box
    * @param clearFlag provides input on whether to use the box letter or to use
    *        the default character to clear box area
    */
   private void fillBoxLocation( PointClass boxLocation, BoxClass fillBox,
         int clearFlag )
   {
      int height, width;
      if (clearFlag == CLEAR_BOX)
      {
         int startHeight = boxLocation.getYPos();
         int startWidth = boxLocation.getXPos();
         int boxWidth = fillBox.getWidth();
         int boxHeight = fillBox.getHeight();
         for (height = startHeight; height > startHeight - boxHeight && height >= 0; height--)
         {
            for (width = startWidth; width < startWidth + boxWidth; width++)
            {
               containerBoxField[ width ][ height ] = DEFAULT_FIELD_CHAR;
            }
         }
      }
      else if (clearFlag == FILL_BOX)
      {
         int startHeight = boxLocation.getYPos();
         int startWidth = boxLocation.getXPos();
         int boxWidth = fillBox.getWidth();
         int boxHeight = fillBox.getHeight();
         char fillLetter = fillBox.getID();
         for (height = startHeight; height > startHeight - boxHeight && height >= 0; height--)
         {
            for (width = startWidth; width < startWidth + boxWidth; width++)
            {
               containerBoxField[ width ][ height ] = fillLetter;
            }
         }
      }
   }
   

   /**
    * Implements recursive backtracking to pack boxes into container box
    * 
    * @return Boolean success or failure
    */
   public boolean fillContainerBox()
   {
      // find an open location
      PointClass openLocation = findNextOpenLocation();
      if (openLocation != null)
      {
         int startingIndex = 0;
         while (findNextUnusedBoxIndex( startingIndex ) != NO_BOXES_AVAILABLE)
         {
            int index = findNextUnusedBoxIndex( startingIndex );
            BoxClass box = boxList[ index ];
            displayField();
            if (checkForFitInField( openLocation, box ))
            {
               box.setUsedState();
               fillBoxLocation( openLocation, box, FILL_BOX );
               if (fillContainerBox())
               {
                  return true;
               }
               box.unsetUsedState();
               fillBoxLocation( openLocation, box, CLEAR_BOX );
               box.rotate();
               if (checkForFitInField( openLocation, box ))
               {
                  box.setUsedState();
                  fillBoxLocation( openLocation, box, FILL_BOX );
                  if (fillContainerBox())
                  {
                     return true;
                  }
                  box.unsetUsedState();
                  fillBoxLocation( openLocation, box, CLEAR_BOX );
                  box.rotate();
               }
            }
            else
            {
               box.rotate();
               if (checkForFitInField( openLocation, box ))
               {
                  box.setUsedState();
                  fillBoxLocation( openLocation, box, FILL_BOX );
                  if (fillContainerBox())
                  {
                     return true;
                  }
                  box.unsetUsedState();
                  fillBoxLocation( openLocation, box, CLEAR_BOX );
                  box.rotate();
                  if (checkForFitInField( openLocation, box ))
                  {
                     box.setUsedState();
                     fillBoxLocation( openLocation, box, FILL_BOX );
                     if (fillContainerBox())
                     {
                        return true;
                     }
                     box.unsetUsedState();
                     fillBoxLocation( openLocation, box, CLEAR_BOX );
                     box.rotate();
                  }
               }
            }
            startingIndex++;
         }
      }
      // loop while i can find a box

      // check for box fit
      // put box in container
      // mark box as used
      // call a new method - recurse
      // chech for recurrsion success
      // return true
      // mark box as unused
      // clear box area in container
      // rotate box
      // check for fix
      // put box into container
      // mark box as used
      // call new method - recurse
      // chech for recurrsion success
      // return true
      // mark box as unused
      // clear box area in container
      // rotate box
      // update start index
      // end box search loop
      // report back if no open location and no boxes available
      if (findNextOpenLocation() == null
            && findNextUnusedBoxIndex( 0 ) == NO_BOXES_AVAILABLE)
      {
         return true;
      }
      return false;
   }
   

   /**
    * Finds next open location
    * <p>
    * Note: Starts search from left lower corner, moves right, then up
    * 
    * @return X, Y coordinate of open location as PointClass object
    */
   private PointClass findNextOpenLocation()
   {
      int height, width;
      for (height = containerBoxHeight - 1; height >= 0; height--)
      {
         for (width = 0; width < containerBoxWidth; width++)
         {
            if (containerBoxField[ width ][ height ] == DEFAULT_FIELD_CHAR)
            {
               PointClass temp = new PointClass( width, height );
               return temp;
            }
         }
      }
      return null;
   }
   

   /**
    * Searches for unused box from the given index of the list of boxes to be
    * packed
    * 
    * @param startAtIndex Index used to begin search of box list
    * @return integer index if a box is found, constant NO_BOXES_AVAILABLE if no
    *         boxes available
    */
   private int findNextUnusedBoxIndex( int startAtIndex )
   {
      int index;
      for (index = startAtIndex; index < numBoxesAvailable; index++)
      {
         if ( ! boxList[ index ].isUsed())
         {
            return index;
         }
      }
      return NO_BOXES_AVAILABLE;
   }
   

   /**
    * sets display flag to display container box during operations
    * 
    * @param setState Boolean state to set or unset display flag
    */
   public void setDisplayFlag( boolean setState )
   {
      displayFlag = setState;
   }
   

   /**
    * Waits for use to press any key
    * 
    * @param message optional message for operation
    * @param args Object parameter not used
    */
   private static void waitForEnter( String message, Object ... args )
   {
      System.out.println( "Press ENTER to continue..." );
      try
      {
         System.in.read( new byte[ 2 ] );
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
