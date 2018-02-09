package p8_Package;

/**
 * Binary Search Tree (BST) class with self-balancing attributes specifically
 * using the Red/Black Tree (RBT) strategy
 * 
 * @author Michael Leverington
 *
 */
public class RBTreeClass
{
   /**
    * Binary Search Tree node class for managing generic data
    * 
    * @author MichaelL
    *
    */
   private class RBT_Node
   {
      private char nodeData;

      private int nodeColor;

      private RBT_Node parentRef;

      private RBT_Node leftChildRef;

      private RBT_Node rightChildRef;

      /**
       * Initialization constructor for data
       * 
       * @param inData char quantity
       */
      public RBT_Node( char inData )
      {
         nodeData = inData;

         nodeColor = COLOR_RED;

         parentRef = null;

         leftChildRef = null;

         rightChildRef = null;
      }

      /**
       * Initialization constructor for data and child references
       * 
       * @param inData char quantity
       * 
       * @param leftRef reference for left child
       * 
       * @param rightRef reference for right child
       */
      /*
       * public RBT_Node( char inData, int inNodeColor, RBT_Node parRef,
       * RBT_Node leftRef, RBT_Node rightRef ) { nodeData = inData;
       * 
       * nodeColor = inNodeColor;
       * 
       * parentRef = parRef;
       * 
       * leftChildRef = leftRef;
       * 
       * rightChildRef = rightRef; }
       */
   }

   /**
    * Root of RBTree
    */
   private RBT_Node RBTree_Root;

   /**
    * Data storage for searched/found data
    */
   private char foundData;

   /**
    * Null character returned if data not available
    */
   public char NULL_CHAR = '\n';

   /**
    * Color black constant
    */
   private int COLOR_BLACK = 66;

   /**
    * Color red constant
    */
   private int COLOR_RED = 82;

   /**
    * Provides user choice to display node color in BST Tree display
    */
   public static int NODE_COLOR = 191;

   /**
    * Provides user choice to display node data in BST Tree display
    */
   public static int NODE_DATA = 192;

   /**
    * Variable provides user-selected choice between output of data or color,
    * initialized to NODE_DATA
    */
   private int treeCharDisplayCode = NODE_DATA;

   /**
    * Traverse code - preorder
    */
   public static final int PREORDER_TRAVERSE = 101;

   /**
    * Traverse code - inorder
    */
   public static final int INORDER_TRAVERSE = 102;

   /**
    * Traverse code - postorder
    */
   public static final int POSTORDER_TRAVERSE = 103;

   /**
    * Class global variable used to display tree structure
    */
   private boolean rowStartFlag;

   /**
    * Constant used to represent space
    * 
    */
   private char SPACE = ' ';

   /**
    * Constant used to represent dash
    * 
    */
   private char DASH = '-';

   /**
    * Default class constructor, no initialization actions
    */
   public RBTreeClass()
   {
      RBTree_Root = null;
   }

   /**
    * Insert method for RBTree
    * <p>
    * Note: uses insert helper method which returns the inserted node reference
    * to this method
    * <p>
    * Note: After value has been inserted as a Red node, this method calls the
    * resolveRBT_Issues method to rebalance and/or recolor the tree
    * 
    * @param inData char data to be added to RBTree
    * 
    * @return Boolean result of insertion action
    */
   public boolean insert( char inData )
   {
      System.out.println( "\nInserting " + inData + " and resolving" );

      RBT_Node insertedNode = insertHelper( RBTree_Root, inData );

      if (insertedNode != null)
      {
         resolveRBT_Issues( insertedNode );

         return true;
      }

      return false;
   }

   /**
    * Insert helper method for RBTree insert action
    * 
    * @param localRoot RBT_Node tree root reference at the current recursion
    *        level
    * 
    * @param inData char item to be added to RBTree
    * 
    * @return RBT_Node reference of inserted node
    */
   private RBT_Node insertHelper( RBT_Node localRoot, char inData )
   {
      RBT_Node add = new RBT_Node( inData );
      if (isEmpty())
      {
         return RBTree_Root = add;
      }
      if (inData < localRoot.nodeData)
      {
         if (localRoot.leftChildRef == null)
         {
            localRoot.leftChildRef = add;
            add.parentRef = localRoot;
            return localRoot.leftChildRef;
         }
         return insertHelper( localRoot.leftChildRef, inData );
      }
      if (inData > localRoot.nodeData)
      {
         if (localRoot.rightChildRef == null)
         {
            localRoot.rightChildRef = add;
            add.parentRef = localRoot;
            return localRoot.rightChildRef;
         }
         return insertHelper( localRoot.rightChildRef, inData );
      }
      return null; // temporary stub return
   }

   /**
    * Resets red-black conditions for tree
    * <p>
    * Note: restores balance of tree as well as appropriate Red/Black coloring
    * of each node
    * 
    * @param localRef node reference for the local tree being managed
    */
   private void resolveRBT_Issues( RBT_Node localRef )
   {
      if (localRef == RBTree_Root)
      {
         RBTree_Root.nodeColor = COLOR_BLACK;
      }

      if (localRef != RBTree_Root)
      {
         if (localRef.parentRef.nodeColor == COLOR_RED)
         {
            if (localRef.parentRef.nodeColor != COLOR_BLACK
                  || localRef.parentRef != RBTree_Root)
            {
               // find uncle
               RBT_Node uncleRef = new RBT_Node( ' ' );
               if (localRef.parentRef.parentRef.leftChildRef == localRef.parentRef)
               {
                  uncleRef = localRef.parentRef.parentRef.rightChildRef;
               }
               else
               {
                  uncleRef = localRef.parentRef.parentRef.leftChildRef;
               }

               // if uncle black
               if (uncleRef == null || uncleRef.nodeColor == COLOR_BLACK)
               {
                  if (localRef.parentRef == localRef.parentRef.parentRef.leftChildRef)
                  {
                     // left left case
                     if (localRef == localRef.parentRef.leftChildRef)
                     {
                        swapColors( localRef.parentRef,
                              localRef.parentRef.parentRef );
                        rotateRight( localRef, localRef.parentRef,
                              localRef.parentRef.parentRef );
                     }
                     else
                     {
                        // left right case
                        rotateLeft( localRef.leftChildRef, localRef,
                              localRef.parentRef );
                        resolveRBT_Issues( localRef.leftChildRef );
                     }
                  }
                  // right right case
                  else if (localRef.parentRef == localRef.parentRef.parentRef.rightChildRef)
                  {
                     // right right
                     if (localRef == localRef.parentRef.rightChildRef)
                     {
                        swapColors( localRef.parentRef,
                              localRef.parentRef.parentRef );
                        rotateLeft( localRef.parentRef,
                              localRef.parentRef.parentRef,
                              localRef.parentRef.parentRef.parentRef );
                     }

                     // right left
                     else
                     {
                        rotateRight( localRef.parentRef,
                              localRef.parentRef.parentRef,
                              localRef.parentRef.parentRef.parentRef );
                        resolveRBT_Issues( localRef.rightChildRef );
                     }
                  }
               }
               // if uncle red
               else if (uncleRef.nodeColor == COLOR_RED)
               {
                  uncleRef.nodeColor = COLOR_BLACK;
                  localRef.parentRef.nodeColor = COLOR_BLACK;
                  localRef.parentRef.parentRef.nodeColor = COLOR_RED;
                  resolveRBT_Issues( localRef.parentRef.parentRef );
               }
            }
         }
      }
   }

   /**
    * Rotates local tree left or CCW
    * 
    * @param localRef reference of current item
    * 
    * @param parRef reference of current item parent
    * 
    * @param grndParRef reference of current item grandparent
    */
   private void rotateLeft( RBT_Node localRef, RBT_Node parRef,
         RBT_Node grndParRef )
   {
      // Left
      if (parRef == grndParRef.leftChildRef)
      {
         grndParRef.leftChildRef = localRef;
         parRef.rightChildRef = localRef.leftChildRef;
         if (localRef.leftChildRef != null)
         {
            localRef.leftChildRef.parentRef = parRef;
         }
         localRef.leftChildRef = parRef;
         parRef.parentRef = localRef;
      }

      // Right
      if (parRef == grndParRef.rightChildRef)
      {
         if (grndParRef != RBTree_Root)
         {
            grndParRef.rightChildRef = parRef.leftChildRef;
            parRef.leftChildRef = grndParRef;
            parRef.parentRef = grndParRef.parentRef;
            grndParRef.parentRef = parRef;

            if (grndParRef.rightChildRef != null)
            {
               grndParRef.rightChildRef.parentRef = grndParRef;
            }

            if (parRef.parentRef.leftChildRef == grndParRef)
            {
               parRef.parentRef.leftChildRef = parRef;
            }

            if (parRef.parentRef.rightChildRef == grndParRef)
            {
               parRef.parentRef.rightChildRef = parRef;
            }
         }

         if (grndParRef == RBTree_Root)
         {
            grndParRef.rightChildRef = parRef.leftChildRef;
            parRef.leftChildRef = grndParRef;
            parRef.parentRef = grndParRef.parentRef;
            grndParRef.parentRef = parRef;

            if (grndParRef.rightChildRef != null)
            {
               grndParRef.rightChildRef.parentRef = grndParRef;
            }
            RBTree_Root = parRef;

         }
      }
   }

   /**
    * Rotates local tree right or CW
    * 
    * @param localRef reference of current item
    * 
    * @param parRef reference of current item parent
    * 
    * @param grndParRef reference of current item grandparent
    */
   private void rotateRight( RBT_Node localRef, RBT_Node parRef,
         RBT_Node grndParRef )
   {
      // left
      if (parRef == grndParRef.leftChildRef)
      {
         if (grndParRef != RBTree_Root)
         {
            grndParRef.leftChildRef = parRef.rightChildRef;
            parRef.rightChildRef = grndParRef;
            parRef.parentRef = grndParRef.parentRef;
            grndParRef.parentRef = parRef;
            if (grndParRef.leftChildRef != null)
            {
               grndParRef.leftChildRef.parentRef = grndParRef;
            }
            if (parRef.parentRef.leftChildRef == grndParRef)
            {
               parRef.parentRef.leftChildRef = parRef;
            }
            if (parRef.parentRef.rightChildRef == grndParRef)
            {
               parRef.parentRef.rightChildRef = parRef;
            }
         }
         if (grndParRef == RBTree_Root)
         {
            grndParRef.leftChildRef = parRef.rightChildRef;
            parRef.rightChildRef = grndParRef;
            parRef.parentRef = grndParRef.parentRef;
            grndParRef.parentRef = parRef;
            if (grndParRef.leftChildRef != null)
            {
               grndParRef.leftChildRef.parentRef = grndParRef;
            }
            RBTree_Root = parRef;
         }
      }
      // Right Left
      if (parRef == grndParRef.rightChildRef)
      {
         grndParRef.rightChildRef = localRef;
         parRef.leftChildRef = localRef.rightChildRef;
         if (localRef.rightChildRef != null)
         {
            localRef.rightChildRef.parentRef = parRef;
         }
         localRef.rightChildRef = parRef;
         parRef.parentRef = localRef;
      }
   }

   /**
    * Swaps colors between two RBT nodes
    * 
    * @param one one of the RBT nodes
    * 
    * @param other other of the RBT nodes
    */
   public void swapColors( RBT_Node one, RBT_Node other )
   {
      int tempColor = one.nodeColor;

      one.nodeColor = other.nodeColor;

      other.nodeColor = tempColor;
   }

   /**
    * Searches for data in RBTree given char with necessary key
    * 
    * @param searchData char item containing key
    * 
    * @return char reference to found data
    */
   public char search( char searchData )
   {
      if (searchHelper( RBTree_Root, searchData ))
      {
         return foundData;
      }

      return NULL_CHAR;
   }

   /**
    * Helper method for RBTree search action
    * 
    * @param localRoot RBT_Node tree root reference at the current recursion
    *        level
    * 
    * @param searchData char item containing key
    * 
    * @return Boolean result of search
    */
   private boolean searchHelper( RBT_Node localRoot, char searchData )
   {
      if (localRoot != null)
      {
         if (searchData == localRoot.nodeData)
         {
            foundData = localRoot.nodeData;
            return true;
         }
         if (searchData > localRoot.nodeData)
         {
            return searchHelper( localRoot.rightChildRef, searchData );
         }
         if (searchData < localRoot.nodeData)
         {
            return searchHelper( localRoot.leftChildRef, searchData );
         }
      }
      return false;
   }

   /**
    * Finds height of tree using helper method
    * 
    * @return height of tree
    */
   public int findTreeHeight()
   {
      return treeHeightHelper( RBTree_Root );
   }

   private int treeHeightHelper( RBT_Node localRoot )
   {
      if (localRoot != null)
      {
         int right = treeHeightHelper( localRoot.rightChildRef );
         int left = treeHeightHelper( localRoot.leftChildRef );
         return getMax( right, left ) + 1;
      }
      return 0; // temporary stub return
   }

   /**
    * Provides user with three ways to display RBTree data
    * 
    * @param traverseCode int code for selecting RBTree traversal method,
    *        accepts PREORDER_TRAVERSE, INORDER_TRAVERSE, POSTORDER_TRAVERSE
    */
   public void displayTree( int traverseCode )
   {
      switch (traverseCode)
      {
         case PREORDER_TRAVERSE:
            displayPreOrder( RBTree_Root );
            break;

         case POSTORDER_TRAVERSE:
            displayPostOrder( RBTree_Root );
            break;

         default:
            displayInOrder( RBTree_Root );
            break;
      }
   }

   /**
    * Provides preOrder traversal action
    * 
    * @param localRoot RBT_Node tree root reference at the current recursion
    *        level
    */
   private void displayPreOrder( RBT_Node localRoot )
   {
      if (localRoot != null)
      {
         System.out.println( localRoot.nodeData );
         displayPostOrder( localRoot.leftChildRef );
         displayPostOrder( localRoot.rightChildRef );
      }
   }

   /**
    * Provides postOrder traversal action
    * 
    * @param localRoot RBT_Node tree root reference at the current recursion
    *        level
    */
   private void displayPostOrder( RBT_Node localRoot )
   {
      if (localRoot != null)
      {
         displayPostOrder( localRoot.leftChildRef );
         displayPostOrder( localRoot.rightChildRef );
         System.out.println( localRoot.nodeData );
      }
   }

   /**
    * Provides inOrder traversal action
    * 
    * @param localRoot RBT_Node tree root reference at the current recursion
    *        level
    */
   private void displayInOrder( RBT_Node localRoot )
   {
      if (localRoot != null)
      {
         displayInOrder( localRoot.leftChildRef );
         System.out.println( localRoot.nodeData );
         displayInOrder( localRoot.rightChildRef );
      }
   }

   /**
    * Displays text-graphical representation of RBT tree
    * 
    */
   public void displayTreeStructure()
   {
      int displayLevel, nodeHeight = findTreeHeight() + 2;
      int workingLevel = 1;

      if (RBTree_Root != null)
      {
         for (displayLevel = 1; displayLevel <= nodeHeight; displayLevel++)
         {
            rowStartFlag = true;

            displayAtTreeLevel( RBTree_Root, nodeHeight, displayLevel,
                  workingLevel );

            System.out.println();
         }
      }

      else
      {
         System.out.println( "\nEmpty Tree - No Display" );
      }
   }

   /**
    * Displays text-graphical representation of one level/line of the RBT tree
    * <p>
    * Note: choice of color or letter data is made with setTreeDisplayCharacter
    * 
    * @param workingNode node reference at current recursive level
    * 
    * @param nodeHeight height of tree plus two for current height of nodes,
    *        including lowermost null children
    * 
    * @param displayLevel level of tree at which the current line of display is
    *        to be presented
    * 
    * @param workingLevel current level during recursive actions
    */
   private void displayAtTreeLevel( RBT_Node workingNode, int nodeHeight,
         int displayLevel, int workingLevel )
   {
      char charOut = workingNode.nodeData;

      if (treeCharDisplayCode == NODE_COLOR)
      {
         charOut = ( char ) workingNode.nodeColor;
      }

      if (workingLevel == displayLevel)
      {
         displayValue( charOut, nodeHeight, workingLevel );

         return;
      }

      if (workingNode.leftChildRef != null)
      {
         displayAtTreeLevel( workingNode.leftChildRef, nodeHeight, displayLevel,
               workingLevel + 1 );
      }

      else
      {
         displayEmptyNodeSpaces( nodeHeight, displayLevel, workingLevel + 1 );
      }

      if (workingNode.rightChildRef != null)
      {
         displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
               displayLevel, workingLevel + 1 );
      }

      else
      {
         displayEmptyNodeSpaces( nodeHeight, displayLevel, workingLevel + 1 );
      }
   }

   /**
    * Method used to display a character or color letter along with calculated
    * leading spaces
    * <p>
    * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
    * 
    * @param data data value to display, either letter or color data
    * 
    * @param nodeHeight height of tree plus two for current height of nodes,
    *        including lowermost null children
    * 
    * @param workingLevel current level during recursive actions
    */
   private void displayValue( char data, int nodeHeight, int workingLevel )
   {
      int leadingSpaces;

      if (rowStartFlag)
      {
         leadingSpaces = toPower( 2, nodeHeight - workingLevel );

         rowStartFlag = false;
      }

      else
      {
         leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
      }

      displayChars( leadingSpaces, SPACE );

      System.out.print( data );
   }

   /**
    * Method that displays null or blank nodes for a tree at null locations
    * <p>
    * Note: used by displayAtTreeLevel
    * 
    * @param nodeHeight height of tree plus two for current height of nodes,
    *        including lowermost null children
    * 
    * @param displayLevel level of the tree at which the display will be applied
    * 
    * @param workingLevel level of tree just below non-null node at which method
    *        is currently working
    */
   private void displayEmptyNodeSpaces( int nodeHeight, int displayLevel,
         int workingLevel )
   {
      int nodesToDisplay = toPower( 2, displayLevel - workingLevel );
      char charOut = SPACE;

      if (displayLevel == workingLevel)
      {
         charOut = DASH;

         if (treeCharDisplayCode == NODE_COLOR)
         {
            charOut = 'B';
         }
      }

      while (nodesToDisplay > 0)
      {
         displayValue( charOut, nodeHeight, displayLevel );

         nodesToDisplay--;
      }
   }

   /**
    * Test for empty tree
    * 
    * @return Boolean result of test
    */
   public boolean isEmpty()
   {
      return RBTree_Root == null;
   }

   /**
    * Allows user to set tree display character
    * 
    * @param displayCode specifies whether to set tree to display data character
    *        or color letter
    */
   public void setTreeDisplayCharacter( int displayCode )
   {
      treeCharDisplayCode = displayCode;
   }

   /**
    * Finds maximum of two given numbers
    * 
    * @param one one of two values to be tested
    * 
    * @param other other of two values to be tested
    * 
    * @return greater of the two values
    */
   private int getMax( int one, int other )
   {
      if (one > other)
      {
         return one;
      }
      return other;
   }

   /**
    * Local recursive method to display a specified number of a specified
    * character
    * 
    * @param numChars number of characters to display
    * 
    * @param outChar character to display
    */
   private void displayChars( int numChars, char outChar )
   {
      if (numChars == 0)
      {
         return;
      }
      System.out.print( outChar );
      displayChars( numChars - 1, outChar );
   }

   /**
    * Local recursive method to calculate exponentiation with integers
    * 
    * @param base base of exponentiation
    * 
    * @param exponent exponent of exponentiation
    * 
    * @return result of exponentiation calculation
    */
   private int toPower( int base, int exponent )
   {
      if (exponent == 0)
      {
         return 1;
      }
      else if (exponent > 0)
      {
         return ( base * toPower( base, exponent - 1 ) );
      }
      else if (exponent < 0)
      {
         return ( 1 / toPower( base, exponent + 1 ) );
      }
      return 0;
   }
}
