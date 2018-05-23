


public class TheOther implements Element {
   public void   accept( Visitor v ) {
     v.visit( this );
   }
   public String theOther() {
     return "TheOther"; 
   }
}