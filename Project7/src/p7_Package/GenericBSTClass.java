package p7_Package;

public class GenericBSTClass<GenericData extends Comparable<GenericData>>
{
   private BST_Node root;
   private GenericData foundData;
   private GenericData removedData;
   static int INORDER_TRAVERSE = 102;
   static int POSTORDER_TRAVERSE = 103;
   static int PREORDER_TRAVERSE = 101;

   /**
    * Provides inOrder traversal action
    * 
    * @param localRoot BST_Node tree root reference at the current recursion
    *        level
    */
   private void displayInOrder( BST_Node localRoot )
   {
      if (localRoot != null)
      {
         displayInOrder( localRoot.leftChildRef );
         System.out.println( localRoot.nodeData );
         displayInOrder( localRoot.rightChildRef );
      }
   }

   /**
    * Provides postOrder traversal action
    * 
    * @param localRoot BST_Node tree root reference at the current recursion
    *        level
    */
   private void displayPostOrder( BST_Node localRoot )
   {
      if (localRoot != null)
      {
         displayPostOrder( localRoot.leftChildRef );
         displayPostOrder( localRoot.rightChildRef );
         System.out.println( localRoot.nodeData );
      }
   }

   /**
    * Provides preOrder traversal action
    * 
    * @param localRoot BST_Node tree root reference at the current recursion
    *        level
    */
   private void displayPreOrder( BST_Node localRoot )
   {
      if (localRoot != null)
      {
         System.out.println( localRoot.nodeData );
         displayPostOrder( localRoot.leftChildRef );
         displayPostOrder( localRoot.rightChildRef );
      }
   }

   /**
    * Provides user with three ways to display BST data
    * 
    * @param traverseCode int code for selecting BST traversal method, accepts
    *        PREORDER_TRAVERSE, INORDER_TRAVERSE, POSTORDER_TRAVERSE
    */
   void displayTree( int traverseCode )
   {
      if (traverseCode == INORDER_TRAVERSE)
      {
         displayInOrder( root );
      }
      else if (traverseCode == POSTORDER_TRAVERSE)
      {
         displayPostOrder( root );
      }
      else if (traverseCode == PREORDER_TRAVERSE)
      {
         displayPreOrder( root );
      }
   }

   /**
    * Insert method for BST
    * <p>
    * Note: uses insert helper method
    * 
    * @param inData GenericData data to be added to BST
    * @return Boolean result of action
    */
   boolean insert( GenericData inData )
   {
      if (isEmpty() == false)
      {
         return insertHelper( root, inData );
      }
      root = new BST_Node( inData );
      return true;
   }

   /**
    * Insert helper method for BST insert action
    * 
    * @param localRoot BST_Node tree root reference at the current recursion
    *        level
    * @param inData GenericData item to be added to BST
    * @return Boolean result of insertion action
    */
   private boolean insertHelper( BST_Node localRoot, GenericData inData )
   {
      int compareResults = inData.compareTo( localRoot.nodeData );
      if (compareResults < 0)
      {
         if (localRoot.leftChildRef == null)
         {
            localRoot.leftChildRef = new BST_Node( inData );
            return true;
         }
         return insertHelper( localRoot.leftChildRef, inData );
      }
      if (compareResults > 0)
      {
         if (localRoot.rightChildRef == null)
         {
            localRoot.rightChildRef = new BST_Node( inData );
            return true;
         }
         return insertHelper( localRoot.rightChildRef, inData );
      }
      return false;
   }

   /**
    * Test for empty tree
    * 
    * @return Boolean result of test
    */
   boolean isEmpty()
   {
      if (root == null)
      {
         return true;
      }
      return false;
   }

   /**
    * Removes data node from tree using given key
    * <p>
    * Note: uses remove helper method
    * 
    * @param inData GenericData that includes the necessary key
    * @return GenericData result of remove action
    */
   GenericData removeNode( GenericData inData )
   {
      if ( ! isEmpty())
      {
         GenericData search = search( inData );
         if (search!=null)
         {
            if(inData.compareTo( root.nodeData ) == 0 && root.leftChildRef == null && root.rightChildRef ==null) {
               removedData = root.nodeData;
               root = null;
               return removedData;
               
            }
            removeNodeHelper( root, inData );
            return removedData;
         }
      }
      return null;
   }

   /**
    * Remove helper for BST remove action
    * <p>
    * Note: uses removeFromMin method
    * <p>
    * Note: this method sets the class removedData variable so the data can be
    * provided to the user
    * 
    * @param localRoot BST_Node tree root reference at the current recursion
    *        level
    * @param outData GenericData item that includes the necessary key
    * @return BST_Node reference result of remove helper action
    */
   private BST_Node removeNodeHelper( BST_Node localRoot, GenericData outData )
   {
      int compareResults = outData.compareTo( localRoot.nodeData );
      if (compareResults > 0)
      {
         localRoot.rightChildRef = removeNodeHelper( localRoot.rightChildRef,
               outData );
      }
      if (compareResults < 0)
      {
         localRoot.leftChildRef = removeNodeHelper( localRoot.leftChildRef,
               outData );
      }
      if (compareResults == 0)
      {

         removedData = localRoot.nodeData;
         if (localRoot.leftChildRef == null)
         {
            if(root.nodeData.compareTo( outData )==0) {
               root = localRoot.rightChildRef;
            }
            return localRoot.rightChildRef;
         }
         else if (localRoot.rightChildRef == null)
         {
            if(root.nodeData.compareTo( outData )==0) {
               root = localRoot.leftChildRef;
            }
            return localRoot.leftChildRef;
         }
         BST_Node temp = removeFromMin( localRoot.rightChildRef,
               localRoot.rightChildRef.leftChildRef );
         localRoot.nodeData = temp.nodeData;
         if (temp.nodeData.compareTo( localRoot.rightChildRef.nodeData ) == 0)
         {
            localRoot.rightChildRef = temp.rightChildRef;
         }
      }
      return localRoot;
   }

   /**
    * Searches tree from given node to minimum value
    * 
    * @param minParent BST_Node reference to current node
    * @param minLoc BST_Node reference to child node to be tested
    * @return BST_Node reference containing removed node
    */
   private BST_Node removeFromMin( BST_Node minParent, BST_Node minLoc )
   {
      if (minLoc != null)
      {
         if (minLoc.leftChildRef != null)
         {
            return removeFromMin( minLoc, minLoc.leftChildRef );
         }
         if (minLoc.rightChildRef != null)
         {
            minParent.leftChildRef = minLoc.rightChildRef;
            return minLoc;
         }
         minParent.leftChildRef = null;
         return minLoc;
      }
      return minParent;
   }

   /**
    * Searches for data in BST given GenericData with necessary key
    * 
    * @param searchData GenericData item containing key
    * @return GenericData reference to found data
    */
   GenericData search( GenericData searchData )
   {
      if (searchHelper( root, searchData ))
      {
         return foundData;
      }
      return null;
   }

   /**
    * Helper method for BST search action
    * <p>
    * Note: this function sets class variable foundData so the data is made
    * available, but then returns a Boolean success
    * 
    * @param localRoot BST_Node tree root reference at the current recursion
    *        level
    * @param searchData GenericData item containing key
    * @return Boolean result of search
    */
   private boolean searchHelper( BST_Node localRoot, GenericData searchData )
   {
      if (localRoot != null)
      {
         int compareResults = searchData.compareTo( localRoot.nodeData );
         if (compareResults == 0)
         {
            foundData = localRoot.nodeData;
            return true;
         }
         if (compareResults > 0)
         {
            return searchHelper( localRoot.rightChildRef, searchData );
         }
         if (compareResults < 0)
         {
            return searchHelper( localRoot.leftChildRef, searchData );
         }
      }
      return false;
   }

   private class BST_Node
   {
      BST_Node leftChildRef;
      BST_Node rightChildRef;
      protected GenericData nodeData;

      /**
       * Initialization constructor for data
       * 
       * @param indata GenericData quantity
       */
      BST_Node( GenericData indata )
      {
         nodeData = indata;
         leftChildRef = null;
         rightChildRef = null;
      }

      /**
       * Initialization constructor for data and child references
       * 
       * @param indata GenericData quantity
       * @param leftRef reference for left child
       * @param rightRef
       */
      BST_Node( GenericData indata, BST_Node leftRef, BST_Node rightRef )
      {
         nodeData = indata;
         leftChildRef = leftRef;
         rightChildRef = rightRef;
      }
   }
}
