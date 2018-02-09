package p8_Package;

public class tester
{

   public static void main( String[] args )
   {
      RBTreeClass RBTree = new RBTreeClass();
      RBTree.insert('f');
      RBTree.displayTreeStructure();
      RBTree.insert('c');
      RBTree.displayTreeStructure();
      RBTree.insert('r');
      RBTree.displayTreeStructure();
      RBTree.insert('q');
      RBTree.displayTreeStructure();
      RBTree.insert('d');
      //RBTree.setTreeDisplayCharacter( 191 );
      //System.out.println(RBTree.search('c'));
      System.out.println(RBTree.findTreeHeight());
      RBTree.setTreeDisplayCharacter( 191 );
      RBTree.displayTreeStructure();
     
   }

}
