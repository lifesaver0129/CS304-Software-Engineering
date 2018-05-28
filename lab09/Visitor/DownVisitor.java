


public class DownVisitor implements Visitor {
   public void visit( This e ) {
      System.out.println( "do Down on " + e.thiss() );
   }
   public void visit( That e ) {
      System.out.println( "do Down on " + e.that() );
   }
   public void visit( TheOther e ) {
      System.out.println( "do Down on " + e.theOther() );
   }
}
